package misl.spring.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import misl.config.ConfigLoader;
import misl.dao.DriverDAO;
import misl.dao.OrderRouteStatusDAO;
import misl.dao.OrdersRouteDAO;
import misl.dao.StatusSessionDAO;
import misl.dao.VehicleJobSessionDAO;
import misl.dao.VehicleRouteLogDAO;
import misl.spring.model.DriverModel;
import misl.spring.model.OrderRouteStatusModel;
import misl.spring.model.OrdersModel;
import misl.spring.model.OrdersRouteModel;
import misl.spring.model.StatusSessionModel;
import misl.spring.model.VehicleJobSessionModel;
import misl.spring.model.VehicleRouteLogModel;
import misl.spring.model.extra.JsonResponse;
import misl.spring.model.json.DriverJson;
import misl.spring.model.json.DriverWorkJson;
import misl.spring.model.json.OrdersJson;
import misl.spring.model.json.OrdersVehicleJson;
import misl.spring.model.json.ResLocationJson;
import misl.spring.model.json.VehicleJobSessionJson;
import misl.spring.model.json.WayPointVehicleJson;
import misl.spring.model.payload.AddLocationPayload;
import misl.testcode.OSRMPayload;
import misl.testcode.RunHttp;

@Controller
@RequestMapping("/driver")
public class DriverController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView model = new ModelAndView("Scen1/tracking/index");
		ModelAndView model = new ModelAndView("driverlatlng");
		return model;
	}

	@RequestMapping(value = "/testdrive", method = RequestMethod.GET)
	public ModelAndView testdrive(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView model = new ModelAndView("Scen1/tracking/index");
		ModelAndView model = new ModelAndView("testdrive");
		return model;
	}

	@RequestMapping(value = { "/temp" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> temp(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GET :provider/findorder");
		JsonResponse res = new JsonResponse();
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> login(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody HashMap<String, String> body) throws Exception {
		System.out.println("POST :driver/Login");
		//System.out.println(body.get("username")+","+body.get("password"));
		JsonResponse res = new JsonResponse();
		DriverJson driver = new DriverJson();
		DriverModel login = new DriverDAO().FindByUsername(body.get("username"));
		if(login.getDriverId() > 0) {
			if(login.getPassword().equals(body.get("password"))) {
				driver.setDriverId(login.getDriverId());
				driver.setAddress(login.getAddress());
				driver.setFirstname(login.getFirstname());
				driver.setLastname(login.getLastname());
				driver.setBirthday(login.getBirthday());
				driver.setPhonenumber(login.getPhonenumber());
				driver.setImg(login.getImg());
				driver.setUsername(login.getUsername());
				
				res.setResult(driver);
				res.setStatus("ok");
				res.setMessage("Success");
			}else {
				res.setStatus("error");
				res.setMessage("Invalid Password");
			}
		}else {
			res.setStatus("error");
			res.setMessage("Username not Found!");
		}
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = {
			"/addLocation" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> addLocation(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody AddLocationPayload addLocationPayload) throws Exception {
		JsonResponse res = new JsonResponse();
		System.out.println("===========form driver============");
		System.out.println("POST :driver/addLocation");
		VehicleRouteLogDAO vehicleRouteLogDAO = new VehicleRouteLogDAO();
		VehicleRouteLogModel bean = new VehicleRouteLogModel();
		bean.setLatitude(addLocationPayload.getLat());
		bean.setLongitude(addLocationPayload.getLng());
		bean.setDatetimeRecord(addLocationPayload.getDatetimeRecord());
		OrdersRouteModel ordersRouteModel = new OrdersRouteDAO().FindByID(addLocationPayload.getOrderRouteId());
		bean.setOrderRouteId(ordersRouteModel);
		System.out.println(bean);
		int result = vehicleRouteLogDAO.Add(bean);
		if (result != -1) {
			RunHttp runHttp = new RunHttp();
			ConfigLoader cl = new ConfigLoader();
			String osrm = cl.getProp("osrm.path") + cl.getProp("osrm.route");
			String configs = "?overview=full&geometries=geojson";
			String url = ordersRouteModel.getOrderId().getLongitude() + ","
					+ ordersRouteModel.getOrderId().getLatitude() + ";" + addLocationPayload.getLng() + ","
					+ addLocationPayload.getLat();
			String httpres = runHttp.run(osrm + url + configs);
			System.out.println(osrm + url + configs);
			ResLocationJson LocationJson = getDistanceDuration(httpres);
			res.setResult(LocationJson);
			res.setMessage("ok");
		} else {
			res.setResult("failed");
			res.setMessage("cannot");
		}

		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	private OSRMPayload getindex(String httpres) {
		OSRMPayload osrmJson = new OSRMPayload();
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(httpres, JsonObject.class);

		JsonArray waypoints = json.getAsJsonArray("waypoints");
		int index[] = new int[waypoints.size()];

		for (int i = 0; i < waypoints.size(); i++) {
			JsonObject objJson = waypoints.get(i).getAsJsonObject();
			index[i] = objJson.get("waypoint_index").getAsInt();
		}
		JsonArray trips = json.getAsJsonArray("trips");
		JsonObject geometry = trips.get(0).getAsJsonObject();
		JsonObject geo = geometry.get("geometry").getAsJsonObject();
		JsonArray coordinates = geo.get("coordinates").getAsJsonArray();

		String[][] TSP = new String[coordinates.size()][2];

		for (int i = 0; i < coordinates.size(); i++) {
			TSP[i][0] = coordinates.get(i).getAsJsonArray().get(0).getAsString();
			TSP[i][1] = coordinates.get(i).getAsJsonArray().get(1).getAsString();
		}
		osrmJson.setIndex(index);
		osrmJson.setTSPLocation(TSP);
		return osrmJson;
	}

	private ResLocationJson getDistanceDuration(String httpres) {
		ResLocationJson resLocationJson = new ResLocationJson();
		double[] res = new double[2];
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(httpres, JsonObject.class);
		JsonArray routes = json.getAsJsonArray("routes");
		JsonObject objJson = routes.get(0).getAsJsonObject();
		JsonObject geometry = routes.get(0).getAsJsonObject();
		JsonObject geo = geometry.get("geometry").getAsJsonObject();
		JsonArray coordinates = geo.get("coordinates").getAsJsonArray();

		String[][] TSP = new String[coordinates.size()][2];

		for (int i = 0; i < coordinates.size(); i++) {
			TSP[i][0] = coordinates.get(i).getAsJsonArray().get(0).getAsString();
			TSP[i][1] = coordinates.get(i).getAsJsonArray().get(1).getAsString();
		}
		resLocationJson.setTSP(TSP);
		resLocationJson.setDuration(getTimeFromsec(objJson.get("duration").getAsDouble()));
		resLocationJson.setDistance(objJson.get("distance").getAsDouble() / 1000 + "");

		return resLocationJson;
	}

	private String getTimeFromsec(double sec) {
		int hours, minutes, seconds;
		hours = (int) sec / 3600;
		minutes = (int) (sec % 3600) / 60;
		seconds = (int) sec % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	@RequestMapping(value = {
			"/startwork/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> startwork(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) throws Exception {
		System.out.println("GET :driver/startwork");
		JsonResponse res = new JsonResponse();
		VehicleJobSessionModel vehicleJobSessionModel = new VehicleJobSessionDAO().FindByID(id);
		StatusSessionModel statusSessionModel = new StatusSessionDAO().FindByID(2);
		vehicleJobSessionModel.setStatusId(statusSessionModel);
		int result = new VehicleJobSessionDAO().Update(vehicleJobSessionModel);
		if (result != -1) {
			res.setResult("success");
			res.setMessage("ok");
		} else {
			res.setResult("failed");
			res.setMessage("cannot");
		}
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/endwork/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> endwork(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) throws Exception {
		System.out.println("GET :driver/startwork");
		JsonResponse res = new JsonResponse();
		VehicleJobSessionModel vehicleJobSessionModel = new VehicleJobSessionDAO().FindByID(id);
		StatusSessionModel statusSessionModel = new StatusSessionDAO().FindByID(1);
		vehicleJobSessionModel.setStatusId(statusSessionModel);
		int result = new VehicleJobSessionDAO().Update(vehicleJobSessionModel);
		if (result != -1) {
			res.setResult("success");
			res.setMessage("ok");
		} else {
			res.setResult("failed");
			res.setMessage("cannot");
		}
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(value = { "/getwork" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<DriverWorkJson>> getwork(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GET :driver/getwork");
		ArrayList<VehicleJobSessionModel> jobsession = new VehicleJobSessionDAO().FindByUser(1);
		ArrayList<DriverWorkJson> DriverWorkJson = new ArrayList<DriverWorkJson>();
		for (VehicleJobSessionModel vehicleJobSessionModel : jobsession) {
			DriverWorkJson driverWorkJson = new DriverWorkJson();
			driverWorkJson.setDate(vehicleJobSessionModel.getDateRoute());
			driverWorkJson.setVehicleJobSessionId(vehicleJobSessionModel.getVehicleJobSessionId());
			driverWorkJson.setStatusId(vehicleJobSessionModel.getStatusId().getStatusName());
			ArrayList<OrdersRouteModel> orderRoute = new OrdersRouteDAO()
					.FindByJobSessionId(vehicleJobSessionModel.getVehicleJobSessionId());
			ArrayList<OrdersJson> orderJsons = new ArrayList<OrdersJson>();
			for (OrdersRouteModel vehicleJob : orderRoute) {
				OrdersJson ordersJson = new OrdersJson();
				ordersJson.setOrderId(vehicleJob.getOrderId().getOrderId());
				ordersJson.setAddress(vehicleJob.getOrderId().getAddress());
				ordersJson.setAmphur(vehicleJob.getOrderId().getAmphur());
				ordersJson.setTambon(vehicleJob.getOrderId().getTambon());
				ordersJson.setProvince(vehicleJob.getOrderId().getProvince());
				ordersJson.setFirstname(vehicleJob.getOrderId().getFirstname());
				ordersJson.setLastname(vehicleJob.getOrderId().getLastname());
				ordersJson.setOrderNumber(vehicleJob.getOrderId().getOrderNumber());
				ordersJson.setPhoneNumber(vehicleJob.getOrderId().getPhoneNumber());
				ordersJson.setLat(vehicleJob.getOrderId().getLatitude());
				ordersJson.setLng(vehicleJob.getOrderId().getLongitude());
				ordersJson.setIndex(vehicleJob.getIndex());
				orderJsons.add(ordersJson);
			}
			driverWorkJson.setWorks(orderJsons);
			DriverWorkJson.add(driverWorkJson);

		}
		return new ResponseEntity<ArrayList<DriverWorkJson>>(DriverWorkJson, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/findordermoc" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> findordermoc(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GET :provider/findordermoc");
		RunHttp runHttp = new RunHttp();
		ConfigLoader cl = new ConfigLoader();
		int jobsectionId = 112;
		WayPointVehicleJson wayPointVehicleJson = new WayPointVehicleJson();
		ArrayList<OrdersRouteModel> ordersRouteList = new OrdersRouteDAO().FindByJobSessionId(jobsectionId);

// Driver info
		DriverJson driverJson = new DriverJson();
		driverJson.setFirstname(ordersRouteList.get(0).getVehicleRouteId().getDriverId().getFirstname());
		driverJson.setLastname(ordersRouteList.get(0).getVehicleRouteId().getDriverId().getLastname());
		driverJson.setAddress(ordersRouteList.get(0).getVehicleRouteId().getDriverId().getAddress());
		driverJson.setBirthday(ordersRouteList.get(0).getVehicleRouteId().getDriverId().getBirthday());
		driverJson.setPhonenumber(ordersRouteList.get(0).getVehicleRouteId().getDriverId().getPhonenumber());
		driverJson.setImg(ordersRouteList.get(0).getVehicleRouteId().getDriverId().getImg());
		driverJson.setDriverId(ordersRouteList.get(0).getVehicleRouteId().getDriverId().getDriverId());
		wayPointVehicleJson.setDriver(driverJson);

// Vehicle info
		wayPointVehicleJson.setVehicle(ordersRouteList.get(0).getVehicleRouteId().getVehicleId());

// Order info
		ArrayList<OrdersVehicleJson> OrdersVehicleList = new ArrayList<OrdersVehicleJson>();
		for (OrdersRouteModel ordersRouteModel : ordersRouteList) {
			OrdersVehicleJson ordersVehicleJson = new OrdersVehicleJson();
			ordersVehicleJson.setOrderId(ordersRouteModel.getOrderId().getOrderId());
			ordersVehicleJson.setAddress(ordersRouteModel.getOrderId().getAddress());
			ordersVehicleJson.setAmphur(ordersRouteModel.getOrderId().getAmphur());
			ordersVehicleJson.setTambon(ordersRouteModel.getOrderId().getTambon());
			ordersVehicleJson.setProvince(ordersRouteModel.getOrderId().getProvince());
			ordersVehicleJson.setFirstname(ordersRouteModel.getOrderId().getFirstname());
			ordersVehicleJson.setLastname(ordersRouteModel.getOrderId().getLastname());
			ordersVehicleJson.setOrderNumber(ordersRouteModel.getOrderId().getOrderNumber());
			ordersVehicleJson.setPhoneNumber(ordersRouteModel.getOrderId().getPhoneNumber());
			ordersVehicleJson.setLat(ordersRouteModel.getOrderId().getLatitude());
			ordersVehicleJson.setLng(ordersRouteModel.getOrderId().getLongitude());
			ordersVehicleJson.setIndex(ordersRouteModel.getIndex());
			ordersVehicleJson.setDate(ordersRouteModel.getVehicleRouteId().getDateRoute());
			OrdersVehicleList.add(ordersVehicleJson);
		}
// add provider here
		OrdersVehicleJson provider = new OrdersVehicleJson();
		provider.setLat(16.254832);
		provider.setLng(103.235047);
		provider.setFirstname("BestExpress(�ѹ���Ԫ��)");
		OrdersVehicleList.add(provider);
		
		
		for (int i = 0; i < OrdersVehicleList.size() - 1; i++) {
			String latlng = OrdersVehicleList.get(i).getLng() + "," + OrdersVehicleList.get(i).getLat() + ";"
					+ OrdersVehicleList.get(i + 1).getLng() + "," + OrdersVehicleList.get(i + 1).getLat();
			
			String head = "http://127.0.0.1:5000/route/v1/driving/";
			String config = "?steps=true&geometries=geojson";
			String httpres = runHttp.run(head + latlng + config);
			double[][] waypoint = getwaypoint(httpres);
			OrdersVehicleList.get(i + 1).setLocation(waypoint);

		}
		String latlng = OrdersVehicleList.get(0).getLng() + "," + OrdersVehicleList.get(0).getLat() + ";"
				+ OrdersVehicleList.get(OrdersVehicleList.size() - 1).getLng() + ","
				+ OrdersVehicleList.get(OrdersVehicleList.size() - 1).getLat();
		String head = cl.getProp("osrm.path")+"/route/v1/driving/";
		String config = "?steps=true&geometries=geojson";
		String httpres = runHttp.run(head + latlng + config);
		double[][] waypoint = getwaypoint(httpres);
		OrdersVehicleList.get(0).setLocation(waypoint);
		JsonResponse res = new JsonResponse();
		wayPointVehicleJson.setOrder(OrdersVehicleList);
		res.setResult(wayPointVehicleJson);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	private double[][] getwaypoint(String httpres) {
		OSRMPayload osrmJson = new OSRMPayload();
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(httpres, JsonObject.class);
		JsonArray trips = json.getAsJsonArray("routes");
		JsonObject geometry = trips.get(0).getAsJsonObject();
		JsonObject geo = geometry.get("geometry").getAsJsonObject();
		JsonArray coordinates = geo.get("coordinates").getAsJsonArray();

		double[][] TSP = new double[coordinates.size()][2];

		for (int i = 0; i < coordinates.size(); i++) {
			TSP[i][0] = coordinates.get(i).getAsJsonArray().get(0).getAsDouble();
			TSP[i][1] = coordinates.get(i).getAsJsonArray().get(1).getAsDouble();
		}
		return TSP;
	}
	@RequestMapping(value = "/post-location", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> postLocation(HttpSession session,HttpServletRequest request, HttpServletResponse response, @RequestBody String reqbody) throws Exception {
		JsonResponse res = new JsonResponse();
		try {
			System.out.println("===========form driver============");
			System.out.println("Post: driver/post-location");
			Gson gson = new Gson();
			JsonObject body = gson.fromJson(reqbody, JsonObject.class);
			JsonObject location = body.get("location").getAsJsonObject();			
			JsonObject coords = location.get("coords").getAsJsonObject();
			String time = location.get("timestamp").getAsString();
			double lat = coords.get("latitude").getAsDouble();
			double lng = coords.get("longitude").getAsDouble();
			int job_id = body.get("job_id").getAsInt();
			
			
			
			DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			Date date = utcFormat.parse(time);

			DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			pstFormat.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
			
			VehicleRouteLogModel log = new VehicleRouteLogModel();
			VehicleRouteLogDAO logdao = new VehicleRouteLogDAO();	
			OrdersRouteModel order = new OrdersRouteDAO().FindByOrderId(job_id);
			System.out.println(lat);
			System.out.println(lng);
			System.out.println(order); 
			System.out.println(pstFormat.format(date));
			
			log.setLatitude(lat);
			log.setLongitude(lng);
			log.setOrderRouteId(order);
			log.setDatetimeRecord(pstFormat.format(date));
			
			System.out.println(log);
			
			logdao.Add(log);
			System.out.println("check5");
//			int result = driver.Add(payload);
			res.setResult("200");
			 
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("post-location error");
		}
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = "/comfirm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> comfirm(HttpSession session,HttpServletRequest request, HttpServletResponse response, @RequestBody OrdersModel order) throws Exception {
		System.out.println("POST: /driver/comfirm");
		JsonResponse res = new JsonResponse();

		OrdersRouteModel orderrouteModel = new OrdersRouteDAO().FindByOrderID(order.getOrderId());
		OrdersRouteDAO orderRouteDAO = new OrdersRouteDAO();
		OrderRouteStatusModel orderRouteStatusModel = new OrderRouteStatusDAO().FindByID(2);
		VehicleJobSessionModel job = new VehicleJobSessionDAO().FindByID(orderrouteModel.getVehicleRouteId().getVehicleJobSessionId());
		VehicleJobSessionDAO updateJob = new VehicleJobSessionDAO();
		StatusSessionModel statusJob = new StatusSessionDAO().FindByID(1);
		
		System.out.println(orderrouteModel.getOrderId().getOrderNumber());
		System.out.println(order.getOrderNumber());
		System.out.println(orderrouteModel.getOrderId().getOrderNumber().equals(order.getOrderNumber()));
		if (orderrouteModel.getOrderId().getOrderNumber().equals(order.getOrderNumber())) {
			orderrouteModel.setOrderRouteStatusId(orderRouteStatusModel);
			if(orderRouteDAO.Update(orderrouteModel)!=-1) {
				ArrayList<OrdersRouteModel> checkLastJob = new OrdersRouteDAO().FindByJobSessionIdWhereStatus(orderrouteModel.getVehicleRouteId().getVehicleJobSessionId());
				System.out.println("    :" + checkLastJob);
				if(checkLastJob.isEmpty()) {
					job.setStatusId(statusJob);
					updateJob.Update(job);
					res.setStatus("no-order");
				}
				else {
					res.setStatus("success");
				}
				
			}else
			{
				res.setStatus("error");
			}
//			int result = orderRouteDAO.Update(orderrouteModel);
//			res.setResult(orderRouteDAO);
		} else {
			res.setResult(orderrouteModel.getOrderId().getOrderNumber());
			res.setStatus("NotMatch");
		}
		
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/findJob/{id}/{date}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> findJob(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id, @PathVariable String date) throws Exception {
		System.out.println("GET :driver/findJob/"+id);
		JsonResponse res = new JsonResponse();
//		int driverId = 1;
//		String date = "2021-07-03";
		VehicleJobSessionModel jobsession = new VehicleJobSessionDAO().FindByDriverIdandDate(id, date);
		VehicleJobSessionJson jobSession = new VehicleJobSessionJson();
		
		jobSession.setVehicleId(jobsession.getVehicleId());
		jobSession.setVehicleJobSessionId(jobsession.getVehicleJobSessionId());
		jobSession.setDateRoute(jobsession.getDateRoute());
		res.setResult(jobSession);
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	
// ===================================================== Get Order and route =============================================================================
	
	@RequestMapping(value = { "/getorderlist/{status}/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> getorderlist(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id, @PathVariable int status) throws Exception {
		System.out.println("GET :provider/findorder");
		JsonResponse res = new JsonResponse();
		OrdersRouteDAO ordersRouteDAO = new OrdersRouteDAO();
		VehicleJobSessionDAO vehicleJobSessionDAO = new VehicleJobSessionDAO();
		WayPointVehicleJson wayPointVehicleJson = new WayPointVehicleJson();
		ArrayList<OrdersRouteModel> orderList = ordersRouteDAO.FindOrder(id, status);
		if (!orderList.isEmpty()) {
			DriverJson driverJson = new DriverJson();
			driverJson.setFirstname(orderList.get(0).getVehicleRouteId().getDriverId().getFirstname());
			driverJson.setLastname(orderList.get(0).getVehicleRouteId().getDriverId().getLastname());
			driverJson.setAddress(orderList.get(0).getVehicleRouteId().getDriverId().getAddress());
			driverJson.setBirthday(orderList.get(0).getVehicleRouteId().getDriverId().getBirthday());
			driverJson.setPhonenumber(orderList.get(0).getVehicleRouteId().getDriverId().getPhonenumber());
			driverJson.setImg(orderList.get(0).getVehicleRouteId().getDriverId().getImg());
			driverJson.setDriverId(orderList.get(0).getVehicleRouteId().getDriverId().getDriverId());
			wayPointVehicleJson.setDriver(driverJson);
			wayPointVehicleJson.setVehicle(orderList.get(0).getVehicleRouteId().getVehicleId());
			
			ArrayList<OrdersVehicleJson> OrdersVehicleList = new ArrayList<OrdersVehicleJson>();
			for (OrdersRouteModel ordersRouteModel : orderList) {
				OrdersVehicleJson ordersVehicleJson = new OrdersVehicleJson();
				ordersVehicleJson.setOrderId(ordersRouteModel.getOrderId().getOrderId());
				ordersVehicleJson.setAddress(ordersRouteModel.getOrderId().getAddress());
				ordersVehicleJson.setAmphur(ordersRouteModel.getOrderId().getAmphur());
				ordersVehicleJson.setTambon(ordersRouteModel.getOrderId().getTambon());
				ordersVehicleJson.setProvince(ordersRouteModel.getOrderId().getProvince());
				ordersVehicleJson.setFirstname(ordersRouteModel.getOrderId().getFirstname());
				ordersVehicleJson.setLastname(ordersRouteModel.getOrderId().getLastname());
				ordersVehicleJson.setOrderNumber(ordersRouteModel.getOrderId().getOrderNumber());
				ordersVehicleJson.setPhoneNumber(ordersRouteModel.getOrderId().getPhoneNumber());
				ordersVehicleJson.setRecipientPhoneNumber(ordersRouteModel.getOrderId().getRecipientPhoneNumber());
				ordersVehicleJson.setRecipientFirstname(ordersRouteModel.getOrderId().getRecipientFirstname());
				ordersVehicleJson.setRecipientLastname(ordersRouteModel.getOrderId().getRecipientLastname());
				ordersVehicleJson.setLat(ordersRouteModel.getOrderId().getLatitude());
				ordersVehicleJson.setLng(ordersRouteModel.getOrderId().getLongitude());
				ordersVehicleJson.setIndex(ordersRouteModel.getIndex());
				ordersVehicleJson.setDate(ordersRouteModel.getVehicleRouteId().getDateRoute());
				OrderRouteStatusModel orderstatus = new OrderRouteStatusDAO().FindByID(ordersRouteModel.getOrderRouteStatusId());
				ordersVehicleJson.setStatus(orderstatus.getStatusName());
				OrdersVehicleList.add(ordersVehicleJson);
			}
			wayPointVehicleJson.setOrder(OrdersVehicleList);
			res.setStatus("ok");
			res.setMessage("ok");
			res.setResult(wayPointVehicleJson);
			
		}else {
			res.setStatus("error");
			res.setMessage("don't have job session");
		}
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/setdriverdevice" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> setdriverdevice(HttpSession session, HttpServletRequest request,
			HttpServletResponse response,@RequestBody String json) throws Exception {
		System.out.println("GET :provider/findorder");
		System.out.println(json);
		JsonResponse res = new JsonResponse();
		Gson gson = new Gson();
		JsonObject body = gson.fromJson(json, JsonObject.class);
		String device = body.get("device").getAsString();
		int driverId = body.get("driverid").getAsInt();
		DriverDAO driverDAO = new DriverDAO();
		DriverModel bean = driverDAO.FindByID(driverId);
		if(bean.getDriverDevice()!=device) {
			bean.setDriverDevice(device);
			driverDAO.Update(bean);
		}
		
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
}

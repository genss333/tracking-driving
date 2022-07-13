package misl.spring.controller;

import java.util.ArrayList;

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
import misl.dao.ProviderDAO;
import misl.dao.StatusSessionDAO;
import misl.dao.VehicleDAO;
import misl.dao.VehicleJobSessionDAO;
import misl.dao.ZoneDAO;
import misl.spring.model.DriverModel;
import misl.spring.model.OrderRouteStatusModel;
import misl.spring.model.OrdersRouteModel;
import misl.spring.model.ProviderModel;
import misl.spring.model.StatusSessionModel;
import misl.spring.model.VehicleJobSessionModel;
import misl.spring.model.VehicleModel;
import misl.spring.model.ZoneModel;
import misl.spring.model.extra.JsonResponse;
import misl.spring.model.extra.ResultMap;
import misl.spring.model.json.DriverJson;
import misl.spring.model.json.OrdersJson;
import misl.spring.model.json.OrdersVehicleJson;
import misl.spring.model.json.OsrmJson;
import misl.spring.model.json.ProviderLogisticjson;
import misl.spring.model.json.RouteJson;
import misl.spring.model.json.Sender;
import misl.spring.model.json.WayPointVehicleJson;
import misl.spring.model.payload.ProviderPayload;
import misl.testcode.OSRMPayload;
import misl.testcode.RunHttp;
import osrm.service;

@Controller
@RequestMapping("/provider")
public class ProviderController {
	@RequestMapping(value = { "/temp" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> temp(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GET :provider/findorder");
		JsonResponse res = new JsonResponse();
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("Scen1/logistics/index");
		return model;
	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("Scen1/logistics/login");
		return model;
	}
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView register(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("Scen1/logistics/register");
		
		return model;
	}
	@RequestMapping(value = { "/register/add" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> add(HttpSession session, HttpServletRequest request,
			HttpServletResponse response,@RequestBody ProviderPayload providerPayload) throws Exception {
		System.out.println("GET :provider/register Company");
		JsonResponse res = new JsonResponse();
		System.out.println(providerPayload);
		ProviderDAO providerDAO = new ProviderDAO();
		ProviderModel bean = new ProviderModel();
		bean.setAddress(providerPayload.getAddress());
		bean.setAmphur(providerPayload.getAmphur());
		bean.setTambon(providerPayload.getTambon());
		bean.setProvince(providerPayload.getProvince());
		bean.setName(providerPayload.getProvidername());
		bean.setLatitude(providerPayload.getLatitude());
		bean.setLongitude(providerPayload.getLongitude());
		bean.setPhone(providerPayload.getPhone());
		bean.setEmail(providerPayload.getEmail());
		bean.setProviderStatus(0);
		int result = providerDAO.Add(bean);
		if(result > 0) {
			res.setStatus("ok");
		}else {
			res.setStatus("err");
		}
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/{providerId}/{date}", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@PathVariable int providerId, @PathVariable String date) {
		ModelAndView model = new ModelAndView("Scen1/logistics/index");
		model.addObject("date", date);
		model.addObject("providerId", providerId);
		System.out.println(model);
		return model;
	}

	@RequestMapping(value = "/vehiclejob/{jobsectionId}", method = RequestMethod.GET)
	public ModelAndView vehiclejobdetails(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@PathVariable int jobsectionId) {
		System.out.println("============vehiclejob.jsp==================");
		ModelAndView model = new ModelAndView("Scen1/logistics/vehiclejob");
		model.addObject("jobsectionId", jobsectionId);
		return model;
	}

	@RequestMapping(value = "/driver", method = RequestMethod.GET)
	public ModelAndView driver(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("Scen1/driver/index");
		return model;
	}

	@RequestMapping(value = "/vehicle", method = RequestMethod.GET)
	public ModelAndView vehicle(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("Scen1/vehicle/index");
		return model;
	}

	@RequestMapping(value = { "/findorder" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<OrdersJson>> findproduct(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GET :provider/findorder");
//		JsonResponse res = new JsonResponse();
		ArrayList<OrdersRouteModel> listOrders = new OrdersRouteDAO().FindOrder();
		ArrayList<OrdersJson> ordersJson = new ArrayList<OrdersJson>();
		for (OrdersRouteModel ordersRouteModel : listOrders) {
			ProviderModel provider = new ProviderDAO()
					.FindByVehicle(ordersRouteModel.getVehicleRouteId().getVehicleId().getVehicleId());
			OrdersJson bean = new OrdersJson();
			Sender sender = new Sender();
			DriverJson driver = new DriverJson();
			bean.setOrderId(ordersRouteModel.getOrderId().getOrderId());
			bean.setAddress(ordersRouteModel.getOrderId().getAddress());
			bean.setAmphur(ordersRouteModel.getOrderId().getAmphur());
			bean.setTambon(ordersRouteModel.getOrderId().getTambon());
			bean.setProvince(ordersRouteModel.getOrderId().getProvince());
			bean.setFirstname(ordersRouteModel.getOrderId().getFirstname());
			bean.setLastname(ordersRouteModel.getOrderId().getLastname());
			bean.setOrderNumber(ordersRouteModel.getOrderId().getOrderNumber());
			bean.setPhoneNumber(ordersRouteModel.getOrderId().getPhoneNumber());
			driver.setDriverId(ordersRouteModel.getVehicleRouteId().getDriverId().getDriverId());
			driver.setFirstname(ordersRouteModel.getVehicleRouteId().getDriverId().getFirstname());
			driver.setLastname(ordersRouteModel.getVehicleRouteId().getDriverId().getLastname());
			driver.setPhonenumber(ordersRouteModel.getVehicleRouteId().getDriverId().getPhonenumber());
			driver.setAddress(ordersRouteModel.getVehicleRouteId().getDriverId().getAddress());
			driver.setImg(ordersRouteModel.getVehicleRouteId().getDriverId().getImg());
			sender.setProviderModel(provider);
			sender.setDriverJson(driver);
			sender.setVehicleModel(ordersRouteModel.getVehicleRouteId().getVehicleId());
			bean.setSender(sender);
			bean.setLat(ordersRouteModel.getOrderId().getLatitude());
			bean.setLng(ordersRouteModel.getOrderId().getLongitude());
			bean.setDate(ordersRouteModel.getVehicleRouteId().getDateRoute());
			ordersJson.add(bean);
		}
		return new ResponseEntity<ArrayList<OrdersJson>>(ordersJson, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/findroute/{jobsectionId}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<RouteJson>> findroute(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int jobsectionId) throws Exception {
		System.out.println("GET :provider/findroute");
		JsonResponse res = new JsonResponse();

		ArrayList<RouteJson> RouteList = new ArrayList<RouteJson>();
		ArrayList<VehicleJobSessionModel> sessionList = new VehicleJobSessionDAO().FindAllJob(jobsectionId);
		System.out.println("vehicle job session  :" + sessionList);
		int countjob = 0;
		int countsuccessjob = 0;
		double distance = 0;
		for (VehicleJobSessionModel jobsession : sessionList) {
			ArrayList<ZoneModel> zone = new ZoneDAO().FindByVehicle(jobsession.getVehicleId().getVehicleId());

			for (ZoneModel zoneModel : zone) {
				RouteJson routeJson = new RouteJson();
				routeJson.setVehicle(zoneModel.getVehicleId());
				routeJson.setZone(zoneModel);
				routeJson.setData(jobsession.getDateRoute());
				ArrayList<OrdersRouteModel> vehicleJobs = new OrdersRouteDAO()
						.FindByJobSessionId(jobsession.getVehicleJobSessionId());
				ArrayList<OrdersJson> order = new ArrayList<OrdersJson>();

				for (OrdersRouteModel vehicleJob : vehicleJobs) {
					countjob = countjob + 1;

					distance = distance + vehicleJob.getDistance();
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

					OrderRouteStatusModel status = new OrderRouteStatusDAO()
							.FindByID(vehicleJob.getOrderRouteStatusId().getOrderRouteStatusId());
					if (status.getOrderRouteStatusId() == 2) {
						countsuccessjob++;
					}
					ordersJson.setStatus(status.getStatusName());
					order.add(ordersJson);
				}

				OrdersJson ordersJson = new OrdersJson();
				ordersJson.setFirstname(zoneModel.getProviderId().getName());
				ordersJson.setAddress(zoneModel.getProviderId().getAddress());
				ordersJson.setAmphur(zoneModel.getProviderId().getAmphur());
				ordersJson.setTambon(zoneModel.getProviderId().getTambon());
				ordersJson.setProvince(zoneModel.getProviderId().getProvince());
				ordersJson.setLat(zoneModel.getProviderId().getLatitude());
				ordersJson.setLng(zoneModel.getProviderId().getLongitude());
				order.add(ordersJson);
				routeJson.setOrder(order);
				routeJson.setCountjob(countjob);
				routeJson.setCountsussessjob(countsuccessjob);
				routeJson.setDistance(distance / 1000);
				RouteList.add(routeJson);
			}
		}
		System.out.println("check" + RouteList);
		return new ResponseEntity<ArrayList<RouteJson>>(RouteList, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/findvehiclemoc" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<ProviderLogisticjson>> findproductmocall(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GET :Provider/findvehiclemoc");
		ArrayList<ZoneModel> vehicle = new ZoneDAO().FindByProvider(1);
		ArrayList<ProviderLogisticjson> logistic = new ArrayList<ProviderLogisticjson>();
		int vehicleId;
		for (ZoneModel zoneModel : vehicle) {
			vehicleId = zoneModel.getVehicleId().getVehicleId();
			ArrayList<VehicleJobSessionModel> job = new VehicleJobSessionDAO().FindByVehicle(vehicleId);
			for (VehicleJobSessionModel joblist : job) {
				ProviderLogisticjson logisticlist = new ProviderLogisticjson();

				logisticlist.setJobsectionId(joblist.getVehicleJobSessionId());
				logisticlist.setDate(joblist.getDateRoute());
				logisticlist.setVehicle(joblist.getVehicleId().getName());
				logisticlist
						.setDriver(joblist.getDriverId().getFirstname() + " " + joblist.getDriverId().getLastname());
				logisticlist.setJobSuccessful(4);
				logisticlist.setJobAll(10);
				logisticlist.setJobFault(6);
				logisticlist.setDistanceSuccessful(36.01);
				logisticlist.setDistanceAll(60.2);
				logisticlist.setDistanceFault(0);

				logistic.add(logisticlist);
			}
		}

		return new ResponseEntity<ArrayList<ProviderLogisticjson>>(logistic, HttpStatus.OK);
	}
	@RequestMapping(value = {"/findAllvehicle" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<ProviderLogisticjson>> findAllvehicle(HttpSession session,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println("GET :Provider/findAllvehicle");
	ArrayList<ProviderLogisticjson> logistic = new ArrayList<ProviderLogisticjson>();
	ArrayList<ResultMap> job = new VehicleJobSessionDAO().FindVehiclesByProvider(1);
	for (ResultMap map : job) {
		ProviderLogisticjson model = new ProviderLogisticjson();
	 	StatusSessionDAO statusSessionDAO = new StatusSessionDAO(); 
		DriverDAO driverDAO = new DriverDAO(); 
		VehicleDAO vehicleDAO = new VehicleDAO(); 
		
		StatusSessionModel statusSessionModel = statusSessionDAO.FindByID(map.get("status_id") != null ? Integer.parseInt(map.get("status_id")) : Integer.MIN_VALUE);
		DriverModel driverModel = driverDAO.FindByID(map.get("driver_id") != null ? Integer.parseInt(map.get("driver_id")) : Integer.MIN_VALUE); 
		VehicleModel vehicleModel = vehicleDAO.FindByID(map.get("vehicle_id") != null ? Integer.parseInt(map.get("vehicle_id")) : Integer.MIN_VALUE); 
		
		model.setDate(map.get("date_route"));
		model.setJobsectionId(map.get("Vehicle_Job_Session_id") != null ? Integer.parseInt(map.get("Vehicle_Job_Session_id")) : Integer.MIN_VALUE);
		model.setVehicle(vehicleModel.getName());
		model.setDriver(driverModel.getFirstname()+ " "+ driverModel.getLastname());
		model.setJobAll(map.get("alljob") != null ? Integer.parseInt(map.get("alljob")) : Integer.MIN_VALUE);
		model.setJobSuccessful(map.get("successjob") != null ? Integer.parseInt(map.get("successjob")) : Integer.MIN_VALUE);
		model.setJobFault(map.get("leftjob") != null ? Integer.parseInt(map.get("leftjob")) : Integer.MIN_VALUE);
		model.setStatus(statusSessionModel.getStatusName());
		model.setDistanceSuccessful(0);
		model.setDistanceAll(map.get("distanceall") != null ? Double.parseDouble(map.get("distanceall")) : 0.00);
		model.setDistanceFault(0);
		logistic.add(model);
	}

return new ResponseEntity<ArrayList<ProviderLogisticjson>>(logistic, HttpStatus.OK);
}

	@RequestMapping(value = {
			"/findvehiclemoc/{providerId}/{date}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<ProviderLogisticjson>> findproductmoc(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, @PathVariable int providerId,
			@PathVariable String date) throws Exception {
		System.out.println("GET :seller/findvehiclemoc");
		ArrayList<ZoneModel> vehicle = new ZoneDAO().FindByProvider(providerId);
		ArrayList<ProviderLogisticjson> logistic = new ArrayList<ProviderLogisticjson>();
		ConfigLoader cl = new ConfigLoader();
		String head = cl.getProp("osrm.path")+"/trip/v1/driving/";
		String config = "?steps=true&geometries=geojson";
		RunHttp runHttp = new RunHttp();

		int vehicleId;

		for (ZoneModel zoneModel : vehicle) {
			int countjob = 0;
			int countsessionjob = 0;
			double countdistance = 0;
			vehicleId = zoneModel.getVehicleId().getVehicleId();
			ArrayList<VehicleJobSessionModel> job = new VehicleJobSessionDAO().FindAllJob(vehicleId, date);
			for (VehicleJobSessionModel joblist : job) {
				ArrayList<OrdersRouteModel> orderRoute = new OrdersRouteDAO()
						.FindByJobSessionId(joblist.getVehicleJobSessionId());
				String latlng = "";

				for (OrdersRouteModel orderRoutelist : orderRoute) {
					countjob++;

					latlng += orderRoutelist.getOrderId().getLongitude() + ","
							+ orderRoutelist.getOrderId().getLatitude() + ";";
					if (orderRoutelist.getOrderRouteStatusId().getOrderRouteStatusId() == 2) {
						countsessionjob++;
					}
				}
				ProviderLogisticjson logisticlist = new ProviderLogisticjson();
				System.out.println(latlng);
				latlng = latlng.substring(0, latlng.length() - 1);
				System.out.println(latlng);
				String httpres2 = runHttp.run(head + zoneModel.getProviderId().getLongitude() + ","
						+ zoneModel.getProviderId().getLatitude() + ";" + latlng + config);

				OsrmJson osrmJsondash = new service().getindex(httpres2);
				for (double distance : osrmJsondash.getDistance()) {
					countdistance = countdistance + distance / 1000;
					System.out.println("distance :" + distance);
				}
				System.out.println("    :" + countdistance);
				logisticlist.setJobsectionId(joblist.getVehicleJobSessionId());
				logisticlist.setDate(joblist.getDateRoute());
				logisticlist.setVehicle(joblist.getVehicleId().getName());
				logisticlist
						.setDriver(joblist.getDriverId().getFirstname() + " " + joblist.getDriverId().getLastname());
				logisticlist.setJobSuccessful(countsessionjob);
				logisticlist.setJobAll(countjob);
				logisticlist.setJobFault(countjob - countsessionjob);
				logisticlist.setDistanceSuccessful(36.01);
				logisticlist.setDistanceAll(countdistance);
				logisticlist.setDistanceFault(0);

				logistic.add(logisticlist);
			}
		}

		return new ResponseEntity<ArrayList<ProviderLogisticjson>>(logistic, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/findordermoc/{jobsectionId}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> findordermoc(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int jobsectionId) throws Exception {
		System.out.println("GET :provider/findordermoc");
		RunHttp runHttp = new RunHttp();
		ConfigLoader cl = new ConfigLoader();
		WayPointVehicleJson wayPointVehicleJson = new WayPointVehicleJson();
		ArrayList<OrdersRouteModel> ordersRouteList = new OrdersRouteDAO().FindByJobSessionId(jobsectionId);

		double distanceAll = 0;

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
			ordersVehicleJson.setRecipientPhoneNumber(ordersRouteModel.getOrderId().getRecipientPhoneNumber());
			ordersVehicleJson.setRecipientFirstname(ordersRouteModel.getOrderId().getRecipientFirstname());
			ordersVehicleJson.setRecipientLastname(ordersRouteModel.getOrderId().getRecipientLastname());
			ordersVehicleJson.setLat(ordersRouteModel.getOrderId().getLatitude());
			ordersVehicleJson.setLng(ordersRouteModel.getOrderId().getLongitude());
			ordersVehicleJson.setIndex(ordersRouteModel.getIndex());
			ordersVehicleJson.setDate(ordersRouteModel.getVehicleRouteId().getDateRoute());
			OrderRouteStatusModel status = new OrderRouteStatusDAO().FindByID(ordersRouteModel.getOrderRouteStatusId());
			ordersVehicleJson.setStatus(status.getStatusName());
			OrdersVehicleList.add(ordersVehicleJson);
		}
		// add provider here
		OrdersVehicleJson provider = new OrdersVehicleJson();
		provider.setLat(16.254832);
		provider.setLng(103.235047);
		provider.setFirstname("BestExpress(กันทรวิชัย)");
		OrdersVehicleList.add(provider);

		for (int i = 0; i < OrdersVehicleList.size() - 1; i++) {
			String latlng = OrdersVehicleList.get(i).getLng() + "," + OrdersVehicleList.get(i).getLat() + ";"
					+ OrdersVehicleList.get(i + 1).getLng() + "," + OrdersVehicleList.get(i + 1).getLat();
			String head = cl.getProp("osrm.path")+"/route/v1/driving/";
			String config = "?steps=true&geometries=geojson";
			System.out.println("check:"+head + latlng + config);
			String httpres = runHttp.run(head + latlng + config);
			double[][] waypoint = getwaypoint(httpres);
			double distance = getdistance(httpres);
			distanceAll = distanceAll + distance;
			OrdersVehicleList.get(i + 1).setLocation(waypoint);

		}
		String latlng = OrdersVehicleList.get(0).getLng() + "," + OrdersVehicleList.get(0).getLat() + ";"
				+ OrdersVehicleList.get(OrdersVehicleList.size() - 1).getLng() + ","
				+ OrdersVehicleList.get(OrdersVehicleList.size() - 1).getLat();
		String head = cl.getProp("osrm.path")+"/route/v1/driving/";
		String config = "?steps=true&geometries=geojson";
		System.out.println(head + latlng + config);
		String httpres = runHttp.run(head + latlng + config);
		System.out.println("test" + head + latlng + config);
		double[][] waypoint = getwaypoint(httpres);
		double distance = getdistance(httpres);
		distanceAll = distanceAll + distance;
		OrdersVehicleList.get(0).setLocation(waypoint);
		JsonResponse res = new JsonResponse();
		wayPointVehicleJson.setOrder(OrdersVehicleList);
		wayPointVehicleJson.setDistance(distanceAll);
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

	private double getdistance(String httpres) {

		OSRMPayload osrmJson = new OSRMPayload();
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(httpres, JsonObject.class);
		JsonArray trips = json.getAsJsonArray("routes");
		JsonObject geometry = trips.get(0).getAsJsonObject();
		double distance = geometry.get("distance").getAsDouble();
		System.out.println("check1" + httpres);
		return distance / 1000;
	}

	@RequestMapping(value = {
			"/test/{id}/{date}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> test(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id, @PathVariable String date) throws Exception {
		System.out.println("GET :provider/findorder");
		JsonResponse res = new JsonResponse();
		System.out.println(id + " -> " + date);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

}

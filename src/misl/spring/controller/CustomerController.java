package misl.spring.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import misl.config.ConfigLoader;
import misl.dao.DriverDAO;
import misl.dao.OrdersDAO;
import misl.dao.OrdersRouteDAO;
import misl.dao.OwnerDAO;
import misl.dao.UsersDAO;
import misl.dao.UsersLocationDAO;
import misl.dao.UsersPhoneDAO;
import misl.dao.VehicleJobSessionDAO;
import misl.dao.VehicleRouteLogDAO;
import misl.spring.model.DriverModel;
import misl.spring.model.OrderRouteStatusModel;
import misl.spring.model.OrdersModel;
import misl.spring.model.OrdersRouteModel;
import misl.spring.model.OwnerModel;
import misl.spring.model.UsersLocationModel;
import misl.spring.model.UsersModel;
import misl.spring.model.UsersPhoneModel;
import misl.spring.model.VehicleJobSessionModel;
import misl.spring.model.VehicleRouteLogModel;
import misl.spring.model.extra.JsonResponse;
import misl.spring.model.json.CustomerOrderJson;
import misl.spring.model.json.CustomerRouteJson;
import misl.spring.model.json.OrderProductJson;
import misl.spring.model.json.ResLocationJson;
import misl.spring.model.json.UserJson;
import misl.spring.model.payload.AddRecipientPayload;
import misl.spring.model.payload.CustomerLocation;
import misl.spring.model.payload.OrdersRouteJson;
import misl.spring.model.payload.RegisterPayload;
import misl.testcode.RunHttp;
import osrm.service;

@Controller
@RequestMapping("/customer")
public class CustomerController {
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
		System.out.println("GET :customer/login");
		JsonResponse res = new JsonResponse();
		UserJson userJson = new UserJson();
		UsersPhoneModel usersPhoneModel = new UsersPhoneDAO().FindByPhoneNumber(body.get("username"));
		if(usersPhoneModel.getUserphoneid() > 0) {
			if(usersPhoneModel.getUserid().getPassword().equals(body.get("password"))) {
				userJson.setFirstname(usersPhoneModel.getUserid().getFirstname());
				userJson.setLastname(usersPhoneModel.getUserid().getLastname());
				userJson.setUserid(usersPhoneModel.getUserid().getUserid());
				userJson.setPhoneNumber(usersPhoneModel.getPhoneNumber());
				res.setResult(userJson);
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
//	@RequestMapping(value = { "/order" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<JsonResponse> order(HttpSession session, HttpServletRequest request,
//			HttpServletResponse response, @RequestBody OrdersModel ordermodel) throws Exception {
//		JsonResponse res = new JsonResponse();
//
//		ArrayList<OwnerModel> Owner = new OwnerDAO().FindByOrderPhonenumber(ordermodel.getPhoneNumber());
//		ArrayList<CustomerOrderPayload> customerorder = new ArrayList<CustomerOrderPayload>();
//		System.out.println(Owner);
//		for (OwnerModel ownerlist : Owner) {
//
//			OrdersRouteModel orderroutemodel = new OrdersRouteDAO().FindByOrderID(ownerlist.getOrderId().getOrderId());
//			VehicleJobSessionModel vjsmodel = new VehicleJobSessionDAO()
//					.FindByID(orderroutemodel.getVehicleRouteId().getVehicleJobSessionId());
//			CustomerOrderPayload customerorderlist = new CustomerOrderPayload();
//
//			customerorderlist.setOrderId(ownerlist.getOrderId().getOrderId());
//			customerorderlist.setOrderDate(ownerlist.getDate());
//			customerorderlist.setOrderNumber(ownerlist.getOrderId().getOrderNumber());
//			customerorderlist.setDriver(
//					"๏ฟฝุณ " + vjsmodel.getDriverId().getFirstname() + " " + vjsmodel.getDriverId().getLastname());
//			customerorderlist.setCustomer(
//					"๏ฟฝุณ " + ownerlist.getOrderId().getFirstname() + " " + ownerlist.getOrderId().getLastname());
//			customerorderlist.setOrderStatus(vjsmodel.getStatusId().getStatusName());
//			customerorderlist.setOrderProvider(ownerlist.getProviderId().getName());
//			customerorder.add(customerorderlist);
//
//		}
//		res.setResult(customerorder);
//		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
//	}

	@RequestMapping(value = {
			"/updateRoute" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> updateRoute(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody OrdersModel ordermodel) throws Exception {
		
		JsonResponse res = new JsonResponse();
		OrdersRouteModel orderroute = new OrdersRouteDAO().FindByOrderId(ordermodel.getOrderId());
		CustomerLocation data_location = new CustomerLocation();

		VehicleRouteLogModel vehicleroutelog = new VehicleRouteLogDAO()
				.FindAllByOrderRouteId(orderroute.getOrderRouteId());

		ResLocationJson LocationJson = new ResLocationJson();
		ConfigLoader cl = new ConfigLoader();
		RunHttp runHttp = new RunHttp();
		String osrm = cl.getProp("osrm.path") + cl.getProp("osrm.route");
		String configs = "?overview=full&geometries=geojson";
		try {
			data_location.setOrder_id(orderroute.getOrderId().getOrderId());
			data_location.setCustomer_lat(orderroute.getOrderId().getLatitude());
			data_location.setCustomer_lng(orderroute.getOrderId().getLongitude());
			data_location.setDriver_lat(vehicleroutelog.getLatitude());
			data_location.setDriver_lng(vehicleroutelog.getLongitude());

			String url = data_location.getCustomer_lng() + "," + data_location.getCustomer_lat() + ";"
					+ data_location.getDriver_lng() + "," + data_location.getDriver_lat();
			String httpres = runHttp.run(osrm + url + configs);
			System.out.println(osrm + url + configs);
			LocationJson = getDistanceDuration(httpres);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		res.setResult(LocationJson);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
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

	@RequestMapping(value = { "/getDriver" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> getDriver(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody OrdersModel ordermodel) throws Exception {
		JsonResponse res = new JsonResponse();
		OrdersRouteModel orderroute = new OrdersRouteDAO().FindByOrderId(ordermodel.getOrderId());
		VehicleRouteLogModel vehicleroutelog = new VehicleRouteLogDAO()
				.FindAllByOrderRouteId(orderroute.getOrderRouteId());
		try {
			DriverModel driver = new DriverDAO()
					.FindByID(vehicleroutelog.getOrderRouteId().getVehicleRouteId().getDriverId());
			res.setResult(driver);

		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
			res.setResult("");
		}

		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/ordered_products"},method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> orderedProducts(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody OrdersModel ordermodel) throws Exception {
		JsonResponse res = new JsonResponse();
		ArrayList<OrderProductJson> orderProduct = new ArrayList<OrderProductJson>();
		
		LocalDate getDate = LocalDate.now(ZoneId.of("Asia/Bangkok"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = getDate.format(formatter);
		
		ArrayList<OwnerModel> owner = new OwnerDAO().FindByOrderByPhonenumberMore(date, ordermodel.getPhoneNumber());
		for (OwnerModel ownerModel : owner) {
			OrderProductJson orderProductlist = new OrderProductJson();
			OrdersRouteModel orderRoute = new OrdersRouteDAO().FindByOrderID(ownerModel.getOrderId().getOrderId());
			orderProductlist.setParcelNumber(ownerModel.getOrderId().getOrderNumber());
			orderProductlist.setDriverName("คุณ"+orderRoute.getVehicleRouteId().getDriverId().getFirstname() + " " +orderRoute.getVehicleRouteId().getDriverId().getLastname());
			orderProductlist.setOwner("คุณ"+ownerModel.getOrderId().getFirstname() + " " + ownerModel.getOrderId().getLastname());
			orderProductlist.setProvider(ownerModel.getProviderId().getName());
			orderProductlist.setSendDate(ownerModel.getDate());
			orderProductlist.setStatus(orderRoute.getOrderRouteStatusId().getStatusName());
			orderProduct.add(orderProductlist);
		}
		
			res.setResult(orderProduct);
		return new  ResponseEntity<JsonResponse>(res,HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/sendorder" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> sendorder(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody OrdersModel ordermodel) throws Exception {
		JsonResponse res = new JsonResponse();

		LocalDate getDate = LocalDate.now(ZoneId.of("Asia/Bangkok"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = getDate.format(formatter);
		
		OwnerModel Owner = new OwnerDAO().FindByOrderPhonenumber(ordermodel.getPhoneNumber());
		ArrayList<OwnerModel> arraylistowner = new OwnerDAO().FindByOrderByPhonenumber(date,ordermodel.getPhoneNumber());
		ArrayList<OrdersRouteJson> orderroute = new ArrayList<OrdersRouteJson>();
		int count = 0;
		for (OwnerModel ownerlist : arraylistowner) {
			count++;
			OrdersRouteModel orderroutmodel = new OrdersRouteDAO()
					.FindJobSecsionWithOrderRouteByOrderId(ownerlist.getOrderId().getOrderId());
			if (count == arraylistowner.size() + 1) {
				break;
			}
			if (orderroutmodel.getOrderRouteId() == 0) {
				continue;
			}
			OrdersRouteJson orderroutelist = new OrdersRouteJson();
			orderroutelist.setOrderId(ownerlist.getOrderId().getOrderId());
			orderroutelist.setIndex(orderroutmodel.getIndex());
			orderroutelist.setLat(orderroutmodel.getOrderId().getLatitude());
			orderroutelist.setLng(orderroutmodel.getOrderId().getLongitude());
			orderroutelist.setOrderRouteId(orderroutmodel.getOrderRouteId());
			orderroutelist.setVehicleJobSessionId(orderroutmodel.getVehicleRouteId().getVehicleJobSessionId());
			orderroutelist.setParcelNumbe(orderroutmodel.getOrderId().getOrderNumber());
			orderroute.add(orderroutelist);
		}

		try {
			CustomerOrderJson customerjson = new CustomerOrderJson();

			customerjson.setAddress(Owner.getOrderId().getAddress() + " " + Owner.getOrderId().getTambon() + " "
					+ Owner.getOrderId().getAmphur() + " " + Owner.getOrderId().getProvince());
			customerjson.setCustomerLat(Owner.getOrderId().getLatitude());
			customerjson.setCustomerLng(Owner.getOrderId().getLongitude());
			customerjson.setFirstName(Owner.getOrderId().getFirstname());
			customerjson.setLastName(Owner.getOrderId().getLastname());
			customerjson.setPhoneNumber(Owner.getOrderId().getPhoneNumber());
			customerjson.setStatus("false");
			customerjson.setOrderRouteId(orderroute);
			res.setResult(customerjson);
		} catch (Exception e) {
			res.setResult("find not order");
		}

		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(value = { "/routeorder" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> routeorder(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody String phonenumber) throws Exception {
		System.out.println("POST: customer/routeorder");
		JsonResponse res = new JsonResponse();

		Gson gSon = new Gson();
		JsonObject json = gSon.fromJson(phonenumber, JsonObject.class);
		String phoneNumber = json.get("phoneNumber").getAsString();

		OrdersModel order = new OrdersModel();
		order.setPhoneNumber(phoneNumber);
		ResponseEntity<JsonResponse> sendorder = sendorder(session, request, response, order);
		CustomerOrderJson customerjson = new CustomerOrderJson();

		try {
			customerjson = (CustomerOrderJson) sendorder.getBody().get("result");
		} catch (Exception e) {
			res.setResult("find not order");
			customerjson.setOrderRouteId(null);
			customerjson.setStatus("false");
			System.out.println(customerjson.getOrderRouteId());
		}
		ConfigLoader cl = new ConfigLoader();
		String httpres2 = "";
		String latlng = "";
		String latlnglast = "";
		String head = cl.getProp("osrm.path")+cl.getProp("osrm.trip");
		String config = "?steps=true&geometries=geojson&roundtrip=false&source=first&destination=last";
		RunHttp runHttp = new RunHttp();

		int i = 0;

		ArrayList<CustomerRouteJson> arrayOsrmJsondash = new ArrayList<CustomerRouteJson>();
		if (customerjson.getOrderRouteId() != null) {

			for (OrdersRouteJson orderRoute : customerjson.getOrderRouteId()) {

				VehicleJobSessionModel Job = new VehicleJobSessionDAO().FindByID(orderRoute.getVehicleJobSessionId());
				VehicleRouteLogModel log = new VehicleRouteLogDAO()
						.FindAllByJobSectionAndOrderRoute(orderRoute.getVehicleJobSessionId());

				System.out.println(i + " ==:== " + orderRoute.getVehicleJobSessionId());

				String latlng_between = "";
				ArrayList<OrdersRouteModel> jobid = new OrdersRouteDAO().FindAllByjobsessionIdBetween(
						orderRoute.getVehicleJobSessionId(), orderRoute.getIndex(), orderRoute.getIndex());
				for (OrdersRouteModel indexOrderRoute : jobid) {
					latlng_between += indexOrderRoute.getOrderId().getLongitude() + ","
							+ indexOrderRoute.getOrderId().getLatitude() + ";";

				}
				latlnglast = log.getLongitude() + "," + log.getLatitude();
				latlng = orderRoute.getLng() + "," + orderRoute.getLat() + ";" + latlng_between + latlnglast;
				httpres2 = runHttp.run(head + latlng + config);

				CustomerRouteJson osrmJsondash = new CustomerRouteJson();

				osrmJsondash = new service().getRouteCustomer(httpres2);

				if (customerjson.getStatus().equals("false")) {
					if (osrmJsondash.getDistance() < 300) {
						customerjson.setStatus("true");
						System.out.println("if");
					} else {
						break;
					}
				}

				res.setResult("OPEN OSRM SERVICE");

				osrmJsondash.setOrderId(customerjson.getOrderRouteId().get(i).getOrderId());
				osrmJsondash.setCustomerLat(customerjson.getOrderRouteId().get(i).getLat());
				osrmJsondash.setCustomerLng(customerjson.getOrderRouteId().get(i).getLng());
				osrmJsondash.setLat(log.getLatitude());
				osrmJsondash.setLng(log.getLongitude());
				osrmJsondash.setPhoneNumber(Job.getDriverId().getPhonenumber());
				osrmJsondash.setParcelNumber(customerjson.getOrderRouteId().get(i).getParcelNumbe());
				osrmJsondash.setDriverName(Job.getDriverId().getFirstname() + " " + Job.getDriverId().getLastname());
				osrmJsondash.setDriverId(Job.getDriverId().getDriverId());
				osrmJsondash.setDurationForm(getTimeFromsec(osrmJsondash.getDuration()));

				res.setResult("Order Succuss");

				i++;
				arrayOsrmJsondash.add(osrmJsondash);
			}
		}
		if (customerjson.getStatus().equals("true")) {
			res.setResult(arrayOsrmJsondash);
		} else {
			ResponseEntity<JsonResponse> orderedProducts = orderedProducts(session, request, response, order);
			res.setResult(orderedProducts.getBody().get("result"));
			res.setStatus("no");
		}

		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/addrecipient" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> addrecipient(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody AddRecipientPayload addRecipientPayload) throws Exception {
		System.out.println("POST :customer/addrecipient");
		JsonResponse res = new JsonResponse();
		OrdersDAO ordersDAO = new OrdersDAO();

		// Find order By OrderId
		OrdersModel ordersModel = ordersDAO.FindByID(addRecipientPayload.getOrderId());
		if (ordersModel.getOrderId() > 0) {
			// Find Phone By recipient PhoneNumber
			UsersPhoneModel usersPhoneModel = new UsersPhoneDAO()
					.FindByPhoneNumber(addRecipientPayload.getPhoneNumber());

			// Set Recipient Info
			if (usersPhoneModel.getUserphoneid() > 0) {
				ordersModel.setRecipientPhoneNumber(usersPhoneModel.getPhoneNumber());
				ordersModel.setRecipientFirstname(usersPhoneModel.getUserid().getFirstname());
				ordersModel.setRecipientLastname(usersPhoneModel.getUserid().getLastname());
				res.setMessage("PhoneNumber, FirstName, LastName has been Updated!");
				// Update
				ordersDAO.Update(ordersModel);
				res.setStatus("ok");
			} else {
//				ordersModel.setRecipientPhoneNumber(addRecipientPayload.getPhoneNumber());
//				res.setMessage("PhoneNumber has been Updated!");
				res.setStatus("Error");
				res.setMessage("Don't Have PhoneNumber");
			}

		} else {
			res.setStatus("Error");
			res.setMessage("Don't Have Order Id");
		}

		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/updateorderlocation" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> updateorderlocation(HttpSession session, HttpServletRequest request,
			HttpServletResponse respones, @RequestBody String locaation) throws Exception {
		return null;

	}
	@RequestMapping(value = {
	"/register" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> register(HttpSession session, HttpServletRequest request,
	HttpServletResponse respones, @RequestBody RegisterPayload payload) throws Exception {
		JsonResponse res = new JsonResponse();
		UsersDAO usersDAO = new UsersDAO();
		UsersPhoneDAO usersPhoneDAO = new UsersPhoneDAO();
		UsersLocationDAO usersLocationDAO = new UsersLocationDAO();
		
		
		UsersModel usersModel = new UsersModel();
		UsersPhoneModel usersPhoneModel = new UsersPhoneModel();
		UsersLocationModel usersLocationModel = new UsersLocationModel();
		
		usersModel.setFirstname(payload.getFirstname());
		usersModel.setLastname(payload.getLastname());
		usersModel.setEmail(payload.getEmail());
		usersModel.setPassword(payload.getPassword());
		usersModel.setRoleid(1);
		UsersModel userId = usersDAO.FindByID(usersDAO.Add(usersModel));
		
		usersPhoneModel.setPhoneNumber(payload.getPhoneNumber());
		usersPhoneModel.setUserid(userId);
		usersPhoneDAO.Add(usersPhoneModel);
		
		res.setResult(userId);
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);

	}
	@RequestMapping(value = {
	"/chkphone/{phone}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> chkphone(HttpSession session, HttpServletRequest request,
	HttpServletResponse respones, @PathVariable String phone) throws Exception {
		JsonResponse res = new JsonResponse();
		UsersPhoneDAO usersPhoneDAO = new UsersPhoneDAO();
		UsersPhoneModel usersPhoneModel = usersPhoneDAO.FindByPhoneNumber(phone);
		if(usersPhoneModel.getUserphoneid() > 0) {
			res.setStatus("error");
			res.setMessage("มีเบอร์นี้ในระบบแล้ว");
		}else {
			res.setStatus("ok");
			res.setMessage("สามารถใช้เบอร์นี้ได้");
		}
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);

	}
	@RequestMapping(value = {
	"/gethome/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> gethome(HttpSession session, HttpServletRequest request,
	HttpServletResponse respones, @PathVariable int id) throws Exception {
		JsonResponse res = new JsonResponse();
		UsersLocationDAO usersLocationDAO = new UsersLocationDAO();
		ArrayList<UsersLocationModel> result = usersLocationDAO.FindAllByUserId(id);
		res.setResult(result);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);

	}
	
	
	@RequestMapping(value = {
	"/setlocation" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> setlocation(HttpSession session, HttpServletRequest request,
	HttpServletResponse respones, @RequestBody RegisterPayload payload) throws Exception {
		System.out.println("POST: customer/setlocation");
		JsonResponse res = new JsonResponse();
		UsersDAO usersDAO = new UsersDAO();
		UsersLocationDAO usersLocationDAO = new UsersLocationDAO();
		
		
		UsersLocationModel usersLocationModel = new UsersLocationModel();
		UsersModel usersModel = usersDAO.FindByID(payload.getUserId());
		usersLocationModel.setLat(payload.getLat());
		usersLocationModel.setLng(payload.getLng());
		usersLocationModel.setProvince(payload.getProvince());
		usersLocationModel.setAmphur(payload.getAmphur());
		usersLocationModel.setTambon(payload.getTambon());
		usersLocationModel.setZipcode(payload.getZipcode());
		usersLocationModel.setAddress("-");
		usersLocationModel.setUserid(usersModel);
		
		int result = usersLocationDAO.Add(usersLocationModel);
		if(result > 0) {
			res.setStatus("ok");
			res.setResult(usersModel);
		}else {
			res.setStatus("error");
		}
		
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);

	}

}

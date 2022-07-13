package misl.spring.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

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
import misl.dao.OrderRouteStatusDAO;
import misl.dao.OrdersDAO;
import misl.dao.OrdersRouteDAO;
import misl.dao.OwnerDAO;
import misl.dao.ProviderDAO;
import misl.dao.SellerDAO;
import misl.dao.ThmapDAO;
import misl.dao.UsersLocationDAO;
import misl.dao.UsersPhoneDAO;
import misl.dao.VehicleDAO;
import misl.dao.VehicleJobSessionDAO;
import misl.dao.ZoneDAO;
import misl.spring.model.OrderRouteStatusModel;
import misl.spring.model.OrdersModel;
import misl.spring.model.OrdersRouteModel;
import misl.spring.model.OwnerModel;
import misl.spring.model.ProviderModel;
import misl.spring.model.SellerModel;
import misl.spring.model.ThmapModel;
import misl.spring.model.UsersLocationModel;
import misl.spring.model.UsersPhoneModel;
import misl.spring.model.VehicleJobSessionModel;
import misl.spring.model.ZoneModel;
import misl.spring.model.extra.JsonResponse;
import misl.spring.model.json.OsrmJson;
import misl.spring.model.json.UserJson;
import misl.spring.model.payload.IndexOrderRoute;
import misl.spring.model.payload.VechicleJobSessionPayload;
import misl.spring.model.payload.ZonePayload;
import misl.spring.model.payload.orderpayload;
import misl.spring.model.payload.vehiclepayload;
import misl.spring.model.payload.vehiclesession_id;
import misl.testcode.RunHttp;

@Controller
@RequestMapping("/order")
public class OrderController {
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ModelAndView ShowOrder(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrdersDAO OrderDAO = new OrdersDAO();
		ModelAndView model = new ModelAndView("Order");
		try {
			ArrayList<OrdersModel> result = OrderDAO.FindAll();
			model.addObject("playbackdata", result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = { "/order" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> order(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody orderpayload ordermodel) throws Exception {
		ConfigLoader cl = new ConfigLoader();
		JsonResponse res = new JsonResponse();
		System.out.println(
				"orderNumber: " + ordermodel.getOrderNumber() + "\n" + "latitude: " + ordermodel.getLatitude() + "\n"
						+ "longtitude: " + ordermodel.getLongitude() + "\n" + "TAMBON: " + ordermodel.getTambon());
		
		try {

			String latlng = "";
			String head = cl.getProp("osrm.path")+"/trip/v1/driving/";
			String config = "?steps=true&geometries=geojson";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			
//=========================== Edit Time lib =============================
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			int maxcap = 10;

			RunHttp runHttp = new RunHttp();
			boolean found = false;
			boolean check_session = true;
			int orderroute_jobsessionID;

//			System.out.println("check");

			// job_session
			VehicleJobSessionDAO jobsessionDAO = new VehicleJobSessionDAO();
			VechicleJobSessionPayload jobsessionpayload = new VechicleJobSessionPayload();

			OrdersDAO orderDAO = new OrdersDAO();
			OrdersModel orderModel = new OrdersModel();
			SellerModel sellerModel = new SellerDAO().FindByID(ordermodel.getSellerId());
			// Set order data
			orderModel.setOrderNumber(ordermodel.getOrderNumber());
			orderModel.setLatitude(ordermodel.getLatitude());
			orderModel.setLongitude(ordermodel.getLongitude());
			orderModel.setTambon(ordermodel.getTambon());
			orderModel.setSellerId(sellerModel);
			orderModel.setAddress(ordermodel.getAddress());
			orderModel.setFirstname(ordermodel.getFirstname());
			orderModel.setLastname(ordermodel.getLastname());
			orderModel.setPhoneNumber(ordermodel.getPhoneNumber());
			orderModel.setRecipientPhoneNumber(ordermodel.getPhoneNumber());
			orderModel.setRecipientFirstname(ordermodel.getFirstname());
			orderModel.setRecipientLastname(ordermodel.getLastname());
			orderModel.setProvince(ordermodel.getProvince());
			orderModel.setAmphur(ordermodel.getAmphur());
			orderModel.setZipcode(ordermodel.getZipcode());

			// Add order
			int result = orderDAO.Add(orderModel);
//			System.out.println("order id:" + result);
			orderModel = new OrdersDAO().FindByID(result);

			// create owner
			OwnerDAO ownerDAO = new OwnerDAO();
			OwnerModel ownerModel = new OwnerModel();

			// create order_route
			OrdersRouteDAO orderroute = new OrdersRouteDAO();
			OrdersRouteModel orderrouteModel = new OrdersRouteModel();

			// zone
			ArrayList<ZoneModel> zoneModel = new ZoneDAO().FindAll();
//			System.out.println(zoneModel);

//			index 
//			for (int zoneModel2 : osrmJson.getIndex()) {
//				System.out.println(zoneModel2);
//			}

			ArrayList<ZonePayload> ZonePayloads = new ArrayList<ZonePayload>();

			for (ZoneModel zone : zoneModel) {
				ZonePayload zonepayload = new ZonePayload();
				zonepayload.setZoneId(zone.getZoneId());
				zonepayload.setVehicleId(zone.getVehicleId().getVehicleId());
				zonepayload.setProviderId(zone.getProviderId().getProviderId());
				zonepayload.setTambon(zone.getTambon());
				zonepayload.setAmphur(zone.getAmphur());
				zonepayload.setProvince(zone.getProvince());
				ZonePayloads.add(zonepayload);
			}
			
			// set vehicle
			ArrayList<vehiclepayload> oderroute = new VehicleDAO().FindVehicle(ordermodel.getAmphur());
			ArrayList<vehiclesession_id> vehiclesession_id = new ArrayList<vehiclesession_id>();


			int index_vehicle = 0;

			calendar.add(Calendar.DAY_OF_MONTH, 1);
//=============== EDIT ===================================
			now.plusDays(0);
			String date = dtf.format(now);
			
			while (check_session) {
		
				for (vehiclepayload vehicleId : oderroute) {
					
					int order_amount = 0;
					ArrayList<OrdersRouteModel> orderroute_all = new ArrayList<OrdersRouteModel>();
					vehiclesession_id vehicle_rount = new vehiclesession_id();
					System.out.println(vehicleId + " : " + date);
					VehicleJobSessionModel jobsession = jobsessionDAO.FindByVehicleIDandDATE(vehicleId.getVehicle_id(),
							date);
				
					orderroute_all = new OrdersRouteDAO().FindAllbysessionId(jobsession.getVehicleJobSessionId());
					for (OrdersRouteModel orderroute_branch : orderroute_all) {
						if (orderroute_branch.getVehicleRouteId().getVehicleJobSessionId() == jobsession
								.getVehicleJobSessionId()) {
							order_amount += 1;
						}
					}
			
					vehicle_rount.setIndex(index_vehicle);
					vehicle_rount.setDate(jobsession.getDateRoute());
					vehicle_rount.setProvider_name(vehicleId.getName_provider());
					vehicle_rount.setOrder_amount(order_amount);
					vehicle_rount.setJob_session_id(jobsession.getVehicleJobSessionId());
					vehicle_rount.setProvider_id(vehicleId.getProvider_id());
					vehicle_rount.setVehicleId(vehicleId.getVehicle_id());
					vehiclesession_id.add(vehicle_rount);
	
					index_vehicle = index_vehicle + 1;
				}

				// end set vehicle

				// set min value
		
				int Q_length = Integer.MAX_VALUE;
				int index = 0;

				for (vehiclesession_id vehiclesession_id2 : vehiclesession_id) {
					if (vehiclesession_id2.getOrder_amount() < Q_length) {
						Q_length = vehiclesession_id2.getOrder_amount();
						index = vehiclesession_id2.getIndex();
					}
				}
//			System.out.println(vehiclesession_id.get(index).getJob_session_id());
//			System.out.println(vehiclesession_id.get(index).getProvider_id());

				int jobId = 0;

				if (vehiclesession_id.get(index).getJob_session_id() == 0) {
					//====================================== Add Driver Here ==================================================
					jobsessionpayload.setDriverId(vehiclesession_id.get(index).getVehicleId());
					jobsessionpayload.setVehicleId(vehiclesession_id.get(index).getVehicleId());
					jobsessionpayload.setStatusSessionId(3);
					jobsessionpayload.setDateRoute(dtf.format(now));
					jobsessionpayload.setStartTime(time.format(calendar.getTime()));
					jobsessionpayload.setEndTime(time.format(calendar.getTime()));
					jobId = orderroute_jobsessionID = jobsessionDAO.Addjob(jobsessionpayload);
					vehiclesession_id.get(index).setJob_session_id(jobId);
					
				}
	
				if (Q_length < maxcap) {
		
					VehicleJobSessionModel jobsession_id_order_route = new VehicleJobSessionModel();

					jobsession_id_order_route = jobsessionDAO
							.FindByID(vehiclesession_id.get(index).getJob_session_id());

					orderrouteModel.setOrderId(orderModel);

					orderrouteModel.setVehicleRouteId(jobsession_id_order_route);

					OrderRouteStatusModel status = new OrderRouteStatusDAO().FindByID(1);
					orderrouteModel.setOrderRouteStatusId(status);
					orderroute.Add(orderrouteModel);
				
					// create order owner
					ProviderModel providerModel = new ProviderDAO()
							.FindByID(vehiclesession_id.get(index).getProvider_id());
					ownerModel.setOrderId(orderModel);
					ownerModel.setProviderId(providerModel);
					ownerModel.setDate(dtf.format(now));
				
					ownerDAO.Add(ownerModel);
					
					found = true;
					check_session = false;
				} else {
				
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					now.plusDays(0);
					//====================================== Add Driver Here ==================================================
					jobsessionpayload.setDriverId(vehiclesession_id.get(index).getVehicleId());

					jobsessionpayload.setVehicleId(vehiclesession_id.get(index).getVehicleId());

					jobsessionpayload.setStatusSessionId(3);
					
					//jobsessionpayload.setDateRoute(dtf.format(now));
					
					jobsessionpayload.setDateRoute(dtf.format(now));
					
					jobsessionpayload.setStartTime(time.format(calendar.getTime()));

					jobsessionpayload.setEndTime(time.format(calendar.getTime()));

					jobId = orderroute_jobsessionID = jobsessionDAO.Addjob(jobsessionpayload);

					vehiclesession_id.get(index).setJob_session_id(jobId);
					// ------------------------------------------------------------------------------------------
					VehicleJobSessionModel jobsession_id_order_route = new VehicleJobSessionModel();

					jobsession_id_order_route = jobsessionDAO
							.FindByID(vehiclesession_id.get(index).getJob_session_id());

					orderrouteModel.setOrderId(orderModel);

					orderrouteModel.setVehicleRouteId(jobsession_id_order_route);

					orderroute.Add(orderrouteModel);
					// create order owner
					ProviderModel providerModel = new ProviderDAO()
							.FindByID(vehiclesession_id.get(index).getProvider_id());
					ownerModel.setOrderId(orderModel);
					ownerModel.setProviderId(providerModel);
					ownerModel.setDate(dtf.format(now));
					ownerDAO.Add(ownerModel);
					found = true;
					check_session = false;
				}
				
			}
			
			
//			System.out.println(ZonePayloads);
//			for (ZonePayload zonepay : ZonePayloads ) {
//				String tambon = zonepay.getTambon();
//				System.out.println(tambon);
//				
//				 if(tambon.equals(ordermodel.getTambon())){
//				    	VehicleJobSessionModel jsid =  jobsessionDAO.FindByVehicleIDandDATE(zonepay.getVehicleId(),dtf.format(now));
//				    	VehicleJobSessionModel jsid2 = new VehicleJobSessionModel(); 
//					 	if(1 > 10 ) {
//					 		continue;
//					 	}
//					 	else {
//					 		if(jsid.getVehicleJobSessionId() != 0)
//					 		{
//					 			orderroute_jobsessionID = jsid.getVehicleJobSessionId();
//					 			jsid2 =  jobsessionDAO.FindByID(orderroute_jobsessionID);
//					 			System.out.println("testttset");
//					 		}
//					 		else {  
//					 			System.out.println(jsid.getVehicleJobSessionId());
//					 			//create vehiclejobsession
//						 		jobsessionpayload.setDriverId(1);
//							 	jobsessionpayload.setVehicleId(zonepay.getVehicleId());
//							 	jobsessionpayload.setStatusSessionId(3);
//							 	jobsessionpayload.setDateRoute(dtf.format(now));
//							 	jobsessionpayload.setStartTime(time.format(calendar.getTime()));
//							 	jobsessionpayload.setEndTime(time.format(calendar.getTime()));
//							 	orderroute_jobsessionID = jobsessionDAO.Addjob(jobsessionpayload);
//								jsid2 =  jobsessionDAO.FindByID(orderroute_jobsessionID);
//					 		}
//					 		
//					 	}
//					 	orderrouteModel.setOrderId(orderModel);
//					 	orderrouteModel.setVehicleRouteId(jsid2);
//					 	orderroute.Add(orderrouteModel);
//					 	//create order owner
//					 	ProviderModel providerModel = new ProviderDAO().FindByID(zonepay.getProviderId());
//					 	ownerModel.setOrderId(orderModel);
//					 	ownerModel.setProviderId(providerModel);
//					 	ownerModel.setDate(dtf.format(now));
//					 	ownerDAO.Add(ownerModel);
//					 	found = true;
//				        break;
//				    }
//				 else {
//					 calendar.add(Calendar.DAY_OF_MONTH, 1);
//					 System.out.println("Date : " + dtf.format(now));
//					 
//					
//				 }
//			}
//			

//			// find ID Last Product
//			ArrayList<ProductModel> userProduct = new ProductDAO().FindByUser(userModel.getUserid());
//			ProductModel productid = new ProductDAO().FindByID(userProduct.get(0).getProductid());
//			// Add Product Price By ProductId
//			for (Rate rate : productRateJson.getRate()) {
//				ProductPriceModel productRate = new ProductPriceModel();
//				productRate.setPrice(rate.getPrice());
//				productRate.setQty(rate.getQty());
//				productRate.setProductid(productid);
//				productPriceDAO.Add(productRate);
//			}
//			// Add Product PriceSell By ProductId
//			for (Rate rate : productRateJson.getRatesell()) {
//				ProductPriceSellModel productRateSell = new ProductPriceSellModel();
//				productRateSell.setPrice(rate.getPrice());
//				productRateSell.setQty(rate.getQty());
//				productRateSell.setProductid(productid);
//				productPriceSellDAO.Add(productRateSell);
//			}
			if (found) {
				res.setResult(orderModel.getOrderId());
				res.setStatus("success");
				res.setMessage("ok");
			} else {
				res.setStatus("failed");
				res.setMessage("cannot add");
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
			System.out.println("error");
		}

		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}

	private OsrmJson getindex(String httpres) {
//		System.out.println(httpres);
		OsrmJson osrmJson = new OsrmJson();
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(httpres, JsonObject.class);

		JsonArray waypoints = json.getAsJsonArray("waypoints");
		JsonArray loaction = json.getAsJsonArray("waypoints");
		int index[] = new int[waypoints.size()];
		String location[][] = new String[waypoints.size()][2];
//		System.out.println(loaction);
		for (int i = 0; i < waypoints.size(); i++) {
			JsonObject objJson = waypoints.get(i).getAsJsonObject();
			JsonObject loaction2 = loaction.get(i).getAsJsonObject();
			index[i] = objJson.get("waypoint_index").getAsInt();
			location[i][0] = loaction2.get("location").getAsJsonArray().get(0).getAsString();
			location[i][1] = loaction2.get("location").getAsJsonArray().get(1).getAsString();
		}
		JsonArray trips = json.getAsJsonArray("trips");

		JsonObject geometry = trips.get(0).getAsJsonObject();
		JsonObject geo = geometry.get("geometry").getAsJsonObject();
		JsonArray coordinates = geo.get("coordinates").getAsJsonArray();

		JsonArray legs = geometry.get("legs").getAsJsonArray();
		double lag = legs.get(1).getAsJsonObject().get("distance").getAsDouble();
		double distance[] = new double[legs.size()];
		double duration[] = new double[legs.size()];
//		System.out.println("legs :"+legs.size());
		for (int i = 0; i < legs.size(); i++) {
			distance[i] = legs.get(i).getAsJsonObject().get("distance").getAsDouble();
			duration[i] = legs.get(i).getAsJsonObject().get("duration").getAsDouble();
		}

//		for (int i = 0; i < duration.length; i++) {
//			System.out.println("Distance:"+distance[i] +
//					" Duration:"+duration[i]);
//		}

		String[][] TSP = new String[coordinates.size()][2];

		for (int i = 0; i < coordinates.size(); i++) {
			TSP[i][0] = coordinates.get(i).getAsJsonArray().get(0).getAsString();
			TSP[i][1] = coordinates.get(i).getAsJsonArray().get(1).getAsString();
		}

//		for (String[] strings : TSP) {
//			System.out.println("Lng:"+strings[0]+" Lat:"+strings[1]);
//		}

//		System.out.println(coordinates.size());
		osrmJson.setIndex(index);
		osrmJson.setTSPLocation(TSP);
		osrmJson.setLocation(location);
		osrmJson.setDistance(distance);
		osrmJson.setDuration(duration);
		return osrmJson;
	}

	@RequestMapping(value = { "/setindex" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> setindex(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody orderpayload ordermodel) throws Exception {
		JsonResponse res = new JsonResponse();
		ConfigLoader cl = new ConfigLoader();
		ResponseEntity<JsonResponse> order = order(session, request, response, ordermodel);
		String latlng = "";
		String head = cl.getProp("osrm.path")+"/trip/v1/driving/";
		String config = "?steps=true&geometries=geojson";
		RunHttp runHttp = new RunHttp();

//		try {
//			String httpres = runHttp.run(head+latlng+config);
//			OsrmJson osrmJson = getindex(httpres);
			Object order_id = order.getBody().get("result");
			String s;
			s = order_id.toString();
			int t = Integer.parseInt(s);
			int orderid = Integer.parseInt(s);
			ArrayList<OrdersRouteModel> orderrouteModel = new OrdersRouteDAO().FindAllByOrderID(t);

			OrdersRouteDAO orderrouteDAO = new OrdersRouteDAO();

			t = orderrouteModel.get(0).getVehicleRouteId().getVehicleJobSessionId();
			ArrayList<OrdersRouteModel> jobid = new OrdersRouteDAO().FindAllByjobsessionID(t);
			ArrayList<IndexOrderRoute> ori = new ArrayList<IndexOrderRoute>();
			String latlng_order = "";

			for (OrdersRouteModel indexOrderRoute : jobid) {
				IndexOrderRoute index = new IndexOrderRoute();
				index.setOrderRouteId(indexOrderRoute.getOrderRouteId());
				index.setLatitude(indexOrderRoute.getOrderId().getLatitude());
				index.setLongitude(indexOrderRoute.getOrderId().getLongitude());
				ori.add(index);
				latlng_order += indexOrderRoute.getOrderId().getLongitude() + ","
						+ indexOrderRoute.getOrderId().getLatitude() + ";";

			}
//			System.out.println("t :"+orderid);
			OwnerModel ownerModel = new OwnerDAO().FindByorderID(orderid);
//			System.out.println(ownerModel);
			double latProvi = ownerModel.getProviderId().getLatitude();
			double lngProvi = ownerModel.getProviderId().getLongitude();

			String.valueOf(lngProvi);
			String.valueOf(latProvi);

			latlng_order = latlng_order.substring(0, latlng_order.length() - 1);
//			System.out.println("HTTP: "+head+String.valueOf(lngProvi)+","+String.valueOf(latProvi)+";"+latlng_order+config);
			String httpres = runHttp.run(
					head + String.valueOf(lngProvi) + "," + String.valueOf(latProvi) + ";" + latlng_order + config);

			OsrmJson osrmJson = getindex(httpres);
			int index[] = osrmJson.getIndex();
//			System.out.println(jobid.size());
//			System.out.println(index.length);
			for (int i = 1; i < index.length; i++) {
				jobid.get(i - 1).setIndex(index[i]);
				jobid.get(i - 1).setDistance(osrmJson.getDistance()[i]);
				jobid.get(i - 1).setDuration(osrmJson.getDuration()[i]);
				orderrouteDAO.Update(jobid.get(i - 1));
			}
			res.setResult(orderrouteModel);
//		} catch (Exception e) {
//			System.out.println(e.getStackTrace().toString());
//			System.out.println("error3");
//		}
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/getthmap"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> getthmap(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("GET :order/getamphur");
		JsonResponse res = new JsonResponse();
		ThmapDAO thmapDAO = new ThmapDAO();
		ArrayList<ThmapModel> result = thmapDAO.FindAll();
		res.setResult(result);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/getprovince"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> getprovince(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("GET :order/getamphur");
		JsonResponse res = new JsonResponse();
		ThmapDAO thmapDAO = new ThmapDAO();
		ArrayList<ThmapModel> result = thmapDAO.FindAllProvince();
		res.setResult(result);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/getamphur/{amphur}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> getamphur(HttpSession session,HttpServletRequest request, HttpServletResponse response, @PathVariable String amphur)
			throws Exception {
		System.out.println("GET :order/getamphur");
		JsonResponse res = new JsonResponse();
		ThmapDAO thmapDAO = new ThmapDAO();
		ArrayList<ThmapModel> result = thmapDAO.FindAllAmphur(amphur);
		res.setResult(result);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/gettambon/{tambon}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> gettambon(HttpSession session,HttpServletRequest request, HttpServletResponse response, @PathVariable String tambon)
			throws Exception {
		System.out.println("GET :order/gettambon");
		JsonResponse res = new JsonResponse();
		ThmapDAO thmapDAO = new ThmapDAO();
		ArrayList<ThmapModel> result = thmapDAO.FindAllTambon(tambon);
		res.setResult(result);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/findUser/{phone}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> findUser(HttpSession session,HttpServletRequest request, HttpServletResponse response, @PathVariable String phone)
			throws Exception {
		System.out.println("GET :order/gettambon");
		JsonResponse res = new JsonResponse();
		UserJson userJson = new UserJson();
		UsersPhoneDAO usersPhoneDAO = new UsersPhoneDAO();
		UsersLocationDAO usersLocationDAO = new UsersLocationDAO();
		UsersPhoneModel usersPhoneModel = usersPhoneDAO.FindByPhoneNumber(phone);
		if(usersPhoneModel.getUserphoneid() > 0) {
			UsersLocationModel usersLocationModel = usersLocationDAO.FindByUserID(usersPhoneModel.getUserid().getUserid());
			userJson.setUserid(usersPhoneModel.getUserid().getUserid());
			userJson.setFirstname(usersPhoneModel.getUserid().getFirstname());
			userJson.setLastname(usersPhoneModel.getUserid().getLastname());
			userJson.setEmail(usersPhoneModel.getUserid().getEmail());
			userJson.setPhoneNumber(usersPhoneModel.getPhoneNumber());
			userJson.setAddress(usersLocationModel.getAddress());
			userJson.setAmphur(usersLocationModel.getAmphur());
			userJson.setTambon(usersLocationModel.getTambon());
			userJson.setProvince(usersLocationModel.getProvince());
			userJson.setZipcode(usersLocationModel.getZipcode());
			userJson.setLat(usersLocationModel.getLat());
			userJson.setLng(usersLocationModel.getLng());
			res.setStatus("ok");
			res.setMessage("ok");
			res.setResult(userJson);
		}else {
			res.setStatus("error");
			res.setMessage("Invalid Phone Number");
			res.setResult(userJson);
		}
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
}

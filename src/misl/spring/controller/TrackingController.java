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

import misl.config.ConfigLoader;
import misl.dao.OrdersRouteDAO;
import misl.dao.VehicleJobSessionDAO;
import misl.dao.VehicleRouteLogDAO;
import misl.dao.ZoneDAO;
import misl.spring.model.OrdersRouteModel;
import misl.spring.model.VehicleJobSessionModel;
import misl.spring.model.VehicleRouteLogModel;
import misl.spring.model.ZoneModel;
import misl.spring.model.extra.JsonResponse;
import misl.spring.model.json.DriverJson;
import misl.spring.model.json.Location;
import misl.spring.model.json.MapTrackingJson;
import misl.spring.model.json.OsrmJson;
import misl.spring.model.json.TrackingJson;
import misl.spring.model.json.VehicleJson;
import misl.spring.model.payload.TimeTrackingPayload;
import misl.spring.model.payload.latlngpayload;
import misl.spring.model.payload.linepayload;
import misl.testcode.RunHttp;
import osrm.service;

@Controller
@RequestMapping("/tracking")
public class TrackingController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView model = new ModelAndView("Scen1/tracking/index");
		ModelAndView model = new ModelAndView("Scen1/tracking/testtracking");
		return model;
	}

	@RequestMapping(value = "/trackingdetail", method = RequestMethod.POST)
	public ModelAndView trackingdeatil(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("trackingdeatil()");
		ModelAndView model = new ModelAndView("Scen1/tracking/trackingDetail");
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		return model;
	}

	@RequestMapping(value = {
			"/maptracking" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<TrackingJson>> maptracking(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody TimeTrackingPayload payload) throws Exception {
		System.out.println("GET :tracking/maptracking");
		/*
		 * Example status: 1 = Done, 2 = on Work, 3 = not started 
		 * Example Date: 2021-06-25 15:40:29
		 * 
		 */
		System.out.println(payload.getStarttime() + "->" + payload.getEndtime());
		System.out.println("payload status:"+payload.getStatus());

		String start = "2021-06-25 15:40:29";
		String end = "2021-6-28 16:25:5";
		int id = 1;
		int status = 3;

		ArrayList<TrackingJson> tracking = new ArrayList<TrackingJson>();

		ArrayList<VehicleJobSessionModel> findstatus = new VehicleJobSessionDAO().FindByStatus(Integer.parseInt(payload.getStatus()));
		for (VehicleJobSessionModel vehicleJobSessionModel : findstatus) {
			TrackingJson trackingJson = new TrackingJson();
			DriverJson driverJson = new DriverJson();
			VehicleJson vehicleJson = new VehicleJson();

			// set data to Driver
			driverJson.setDriverId(vehicleJobSessionModel.getDriverId().getDriverId());
			driverJson.setFirstname(vehicleJobSessionModel.getDriverId().getFirstname());
			driverJson.setLastname(vehicleJobSessionModel.getDriverId().getLastname());
			driverJson.setPhonenumber(vehicleJobSessionModel.getDriverId().getPhonenumber());
			driverJson.setImg(vehicleJobSessionModel.getDriverId().getImg());
			driverJson.setAddress(vehicleJobSessionModel.getDriverId().getAddress());
			driverJson.setBirthday(vehicleJobSessionModel.getDriverId().getBirthday());
			trackingJson.setDriver(driverJson);

			// set data to vehicle
			vehicleJson.setName(vehicleJobSessionModel.getVehicleId().getName());
			vehicleJson.setBrand(vehicleJobSessionModel.getVehicleId().getBrand());
			vehicleJson.setType(vehicleJobSessionModel.getVehicleId().getType());
			vehicleJson.setFuel(vehicleJobSessionModel.getVehicleId().getFuel());
			trackingJson.setVehicle(vehicleJson);
			
			System.out.printf("VehicleJobSessionId:"+vehicleJobSessionModel.getVehicleJobSessionId()+"\n");

			ArrayList<VehicleRouteLogModel> VehicleRouteLogModel = new VehicleRouteLogDAO().FindAlllog(
					payload.getStarttime(), payload.getEndtime(), vehicleJobSessionModel.getVehicleJobSessionId());
			ArrayList<Location> Locations = new ArrayList<Location>();
			for (VehicleRouteLogModel vehicleRouteLogModel : VehicleRouteLogModel) {
				Location location = new Location();
				location.setDatetimeRecord(vehicleRouteLogModel.getDatetimeRecord());
				location.setLatitude(vehicleRouteLogModel.getLatitude());
				location.setLongitude(vehicleRouteLogModel.getLongitude());
				Locations.add(location);
			}
			trackingJson.setLocations(Locations);
			tracking.add(trackingJson);
		}

		return new ResponseEntity<ArrayList<TrackingJson>>(tracking, HttpStatus.OK);
	}

	@RequestMapping(value = "/tracking/{orderrouteId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> tracking(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int orderrouteId) throws Exception {
		System.out.println("GET:tracking/"+orderrouteId);
		ConfigLoader cl = new ConfigLoader();
		JsonResponse res = new JsonResponse();
//		int orderRoute = 333;
		ArrayList<OsrmJson> list = new ArrayList<OsrmJson>();
		OrdersRouteModel orderroute = new OrdersRouteDAO().FindByOrderID(orderrouteId);
		
		linepayload line = new linepayload();
		
		String latlng = "";
		String latlnglast = "";
		String head = cl.getProp("osrm.path")+"/trip/v1/driving/";
		String config = "?steps=true&geometries=geojson&roundtrip=false&source=first&destination=last";
		String config2 = "?steps=true&geometries=geojson";
		RunHttp runHttp = new RunHttp();
		System.out.println("Job SessionID: "+orderroute.getVehicleRouteId().getVehicleJobSessionId());
		
		
		
		ArrayList<VehicleRouteLogModel> log = new VehicleRouteLogDAO().FindAllByJobSection(orderroute.getVehicleRouteId().getVehicleJobSessionId());
		latlnglast =  log.get(0).getLongitude() + "," + log.get(0).getLatitude() ;

		
		double[][] TSP = new double[log.size()][2];
		double[][] arrpoly = null ;
		ArrayList<double[]> poly = new ArrayList<double[]>() ;
//		for (int i = 0; i < log.size()-1; i++) {
//			TSP[i][0] = log.get(i).getLongitude();
//			TSP[i][1] = log.get(i).getLatitude();
//			TSP[i+1][0] = log.get(i+1).getLongitude();
//			TSP[i+1][1] = log.get(i+1).getLatitude();
//			latlng = TSP[i][0] + "," + TSP[i][1] + ";" + TSP[i+1][0] + "," + TSP[i+1][1];
//			String httpres = runHttp.run(head + latlng + config);
//			double[][] arrpoly2 = new service().getwaypoint(httpres);
//			for (int j = 0; j < arrpoly2.length; i++) {
//				//poly.add(arrpoly2[j]);
//				System.out.println(arrpoly2[j][0]);
//			}
//		}
		
		for (int i = 1; i < log.size(); i++) {
			latlng = "";
			latlng += log.get(i-1).getLongitude()+","+log.get(i-1).getLatitude()+";"+log.get(i).getLongitude()+","+log.get(i).getLatitude();
			String httpres = runHttp.run(head + latlng + config);
			double[][] arrpoly2 = new service().getwaypoint(httpres);
			for (int j = 0; j < arrpoly2.length; j++) {
				poly.add(arrpoly2[j]);
			}
		}
		latlng = orderroute.getOrderId().getLongitude() +","+orderroute.getOrderId().getLatitude()+";"+latlnglast;
		String httpres2 = runHttp.run(head + latlng + config2);

		OsrmJson osrmJsondash = new service().getindex(httpres2);
		
		
		line.setDashline(osrmJsondash.getTSPLocation());
		line.setPolyline(poly);
		
		
		
		try {
			res.setResult(line);

		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
			System.out.println("error3");
		}
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
// ========================================================= New Map Tracking 0.0.3 ==========================================================
	@RequestMapping(value = { "/getoldline/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> getoldline(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) throws Exception {
		JsonResponse res = new JsonResponse();
		System.out.println("============form vehiclejob.jsp==============");
		System.out.println("GET :provider/getoldline");
		try {

			ConfigLoader cl = new ConfigLoader();
			int jobsessionId = 125;
			String lineStr = "";
			String head = cl.getProp("osrm.path")+"/trip/v1/driving/";
			String config = "?steps=true&geometries=geojson";
			String config2 = "?steps=true&geometries=geojson&roundtrip=false&source=first&destination=last";
			RunHttp runHttp = new RunHttp();
			
			VehicleRouteLogDAO vehicleRouteLogDAO = new VehicleRouteLogDAO();
			OrdersRouteDAO ordersRouteDAO = new OrdersRouteDAO();
			MapTrackingJson mapTrackingJson = new MapTrackingJson();
			ZoneDAO zoneDAO = new ZoneDAO();
			ArrayList<double[]> polyLines = new ArrayList<double[]>();
			ArrayList<double[][]> dashedLines = new ArrayList<double[][]>();
			ArrayList<OrdersRouteModel> allJob = ordersRouteDAO.FindByJobSessionIdWhereStatus(id);
			 ArrayList<VehicleRouteLogModel> allLog = vehicleRouteLogDAO.FindAllByJobSection(id);
			 ZoneModel zone = zoneDAO.FindByVehicleJobSessionID(id);
			 for (int i = 1; i < allLog.size(); i++) {
			 	lineStr = "";
			 	lineStr = allLog.get(i-1).getLongitude()+","+allLog.get(i-1).getLatitude()+";"+
			 			allLog.get(i).getLongitude()+","+allLog.get(i).getLatitude();
			 	String httpres = runHttp.run(head + lineStr + config2);
			 	double[][] arrpolyLine = new service().getwaypoint(httpres);
			 	for (int j = 0; j < arrpolyLine.length; j++) {
			 		polyLines.add(arrpolyLine[j]);
			 	}
			 }
			
			
//			lineStr="";
//			 lineStr = allLog.get(allLog.size()-1).getLongitude()+","+allLog.get(allLog.size()-1).getLatitude()+";";
//			for (OrdersRouteModel OrdersRouteModel : allJob) {
//				lineStr += OrdersRouteModel.getOrderId().getLongitude()+","+OrdersRouteModel.getOrderId().getLatitude()+";";
//			}
//			lineStr += zone.getProviderId().getLongitude()+","+zone.getProviderId().getLatitude();
			
			
//			lineStr = allLog.get(allLog.size()-1).getLongitude()+","+allLog.get(allLog.size()-1).getLatitude()+";"+allJob.get(0).getOrderId().getLongitude()+","+allJob.get(0).getOrderId().getLatitude();
			lineStr = allLog.get(0).getLongitude()+","+allLog.get(0).getLatitude()+";"+allJob.get(0).getOrderId().getLongitude()+","+allJob.get(0).getOrderId().getLatitude();
			String httpres = runHttp.run(head + lineStr + config2);
			double[][] arrpolyLine1 = new service().getwaypoint(httpres);
			dashedLines.add(arrpolyLine1);
			System.out.println("Size0:"+arrpolyLine1.length);
			
			for (int i = 1; i < allJob.size(); i++) {
				lineStr = "";
				lineStr = allJob.get(i-1).getOrderId().getLongitude()+","+allJob.get(i-1).getOrderId().getLatitude()+";"+allJob.get(i).getOrderId().getLongitude()+","+allJob.get(i).getOrderId().getLatitude();
				httpres = runHttp.run(head + lineStr + config2);
				double[][] arrpolyLine2 = new service().getwaypoint(httpres);
				dashedLines.add(arrpolyLine2);
				System.out.println("Size"+i+":"+arrpolyLine2.length);
			}
			
			lineStr = allJob.get(allJob.size()-1).getOrderId().getLongitude()+","+allJob.get(allJob.size()-1).getOrderId().getLatitude()+";"+zone.getProviderId().getLongitude()+","+zone.getProviderId().getLatitude();
			httpres = runHttp.run(head + lineStr + config2);
			double[][] arrpolyLine3 = new service().getwaypoint(httpres);
			dashedLines.add(arrpolyLine3);
			System.out.println("SizeC:"+arrpolyLine3.length);
			
//			String httpres = runHttp.run(head + lineStr + config2);
//			double[][] arrpolyLine = new service().getwaypoint(httpres);
//			dashedLines.add(arrpolyLine);
//			for (int j = 0; j < arrpolyLine.length; j++) {
//				dashedLines.add(arrpolyLine);
//			}
			mapTrackingJson.setPolyLine(polyLines);
			mapTrackingJson.setDashedLine(dashedLines);
			mapTrackingJson.setEndTime(allLog.get(0).getDatetimeRecord());
			res.setResult(mapTrackingJson);
		} catch (Exception e) {
			res.setStatus("Error");
			res.setMessage(e.getStackTrace().toString());
		}
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = {
	"/maptracking2" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ArrayList<TrackingJson>> findproduct2(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody TimeTrackingPayload payload) throws Exception {
		System.out.println("GET :tracking/findalllog");
		/*
		 * Example status: 1 = Done, 2 = on Work, 3 = not started 
		 * Example Date: 2021-06-25 15:40:29
		 * 
		 */
		System.out.println(payload.getStarttime() + "->" + payload.getEndtime());
		
		String start = "2021-06-25 15:40:29";
		String end = "2021-6-28 16:25:5";
		int id = 1;
		int status = 3;
		
		ArrayList<TrackingJson> tracking = new ArrayList<TrackingJson>();
		
		ArrayList<VehicleJobSessionModel> findstatus = new VehicleJobSessionDAO()
				.FindByStatus(Integer.parseInt(payload.getStatus()));
		for (VehicleJobSessionModel vehicleJobSessionModel : findstatus) {
			TrackingJson trackingJson = new TrackingJson();
			DriverJson driverJson = new DriverJson();
			VehicleJson vehicleJson = new VehicleJson();
		
			// set data to Driver
			driverJson.setDriverId(vehicleJobSessionModel.getDriverId().getDriverId());
			driverJson.setFirstname(vehicleJobSessionModel.getDriverId().getFirstname());
			driverJson.setLastname(vehicleJobSessionModel.getDriverId().getLastname());
			driverJson.setPhonenumber(vehicleJobSessionModel.getDriverId().getPhonenumber());
			driverJson.setImg(vehicleJobSessionModel.getDriverId().getImg());
			driverJson.setAddress(vehicleJobSessionModel.getDriverId().getAddress());
			driverJson.setBirthday(vehicleJobSessionModel.getDriverId().getBirthday());
			trackingJson.setDriver(driverJson);
		
			// set data to vehicle
			vehicleJson.setName(vehicleJobSessionModel.getVehicleId().getName());
			vehicleJson.setBrand(vehicleJobSessionModel.getVehicleId().getBrand());
			vehicleJson.setType(vehicleJobSessionModel.getVehicleId().getType());
			vehicleJson.setFuel(vehicleJobSessionModel.getVehicleId().getFuel());
			trackingJson.setVehicle(vehicleJson);
		
			ArrayList<VehicleRouteLogModel> VehicleRouteLogModel = new VehicleRouteLogDAO().FindAlllog(
					payload.getStarttime(), payload.getEndtime(), vehicleJobSessionModel.getVehicleJobSessionId());
			ArrayList<Location> Locations = new ArrayList<Location>();
			for (VehicleRouteLogModel vehicleRouteLogModel : VehicleRouteLogModel) {
				Location location = new Location();
				location.setDatetimeRecord(vehicleRouteLogModel.getDatetimeRecord());
				location.setLatitude(vehicleRouteLogModel.getLatitude());
				location.setLongitude(vehicleRouteLogModel.getLongitude());
				Locations.add(location);
			}
			trackingJson.setLocations(Locations);
			tracking.add(trackingJson);
		}
		
		return new ResponseEntity<ArrayList<TrackingJson>>(tracking, HttpStatus.OK);
		}
	// ========================================================= New Map Tracking 0.0.4 ==========================================================
	@RequestMapping(value = { "/getplan/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> getplan(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) throws Exception {
		System.out.println("============form vehiclejob.jsp==============");
		System.out.println("GET :provider/findorder");
		JsonResponse res = new JsonResponse();
		MapTrackingJson mapTrackingJson = new MapTrackingJson();
		ArrayList<double[]> polyLines = new ArrayList<double[]>();
		ArrayList<double[][]> dashedLines = new ArrayList<double[][]>();
		ZoneDAO zoneDAO = new ZoneDAO();
		OrdersRouteDAO ordersRouteDAO = new OrdersRouteDAO();
		VehicleRouteLogDAO vehicleRouteLogDAO = new VehicleRouteLogDAO();
		ArrayList<OrdersRouteModel> allJob = ordersRouteDAO.FindByJobSessionIdWhereStatus(id);
		System.out.println("run alljob("+id+")");
		ArrayList<VehicleRouteLogModel> allLog = vehicleRouteLogDAO.FindAllByJobSection(id);
		System.out.println("run alllog("+id+")");
		ZoneModel zone = zoneDAO.FindByVehicleJobSessionID(id);
		System.out.println("run zone("+id+")");
		ConfigLoader cl = new ConfigLoader();
		String planLine = "";
		String head = cl.getProp("osrm.path")+"/route/v1/driving/";
		String config = "?steps=true&geometries=geojson";
		RunHttp runHttp = new RunHttp();
		/*
		 * ===[Job data]===
		 * Center
		 * 1
		 * 2
		 * 3
		 * ================
		 * Create Plan
		 * Center 	=> 	1
		 * 1 		=> 	2
		 * 2 		=> 	3
		 * 3 		=> 	Center
		 * */
		System.out.println("0, "+zone.getProviderId().getName());
		for (OrdersRouteModel OrdersRouteModel : allJob) {
			System.out.println(OrdersRouteModel.getIndex()+", "+OrdersRouteModel.getOrderId().getFirstname());
		}
		System.out.println("C, "+zone.getProviderId().getName());
		// Center => First location
		planLine = zone.getProviderId().getLongitude()+","+zone.getProviderId().getLatitude()+";"+allJob.get(0).getOrderId().getLongitude()+","+allJob.get(0).getOrderId().getLatitude();
		System.out.println(head + planLine + config);
		String httpres = runHttp.run(head + planLine + config);
	 	double[][] arrplan = new service().getroutewaypoint(httpres);
	 	dashedLines.add(arrplan);
	 	
	 	// First location => Next location
		for (int i = 1; i < allJob.size(); i++) {
			planLine = allJob.get(i-1).getOrderId().getLongitude()+","+allJob.get(i-1).getOrderId().getLatitude()+";"+allJob.get(i).getOrderId().getLongitude()+","+allJob.get(i).getOrderId().getLatitude();
			String httpres1 = runHttp.run(head + planLine + config);
		 	double[][] arrplan1 = new service().getroutewaypoint(httpres1);
		 	dashedLines.add(arrplan1);
		}
		
		// Last location => Center
		planLine = allJob.get(allJob.size()-1).getOrderId().getLongitude()+","+allJob.get(allJob.size()-1).getOrderId().getLatitude()+";"+zone.getProviderId().getLongitude()+","+zone.getProviderId().getLatitude();
		String httpres1 = runHttp.run(head + planLine + config);
	 	double[][] arrplan1 = new service().getroutewaypoint(httpres1);
	 	dashedLines.add(arrplan1);
	 	mapTrackingJson.setDashedLine(dashedLines);
	 	
	 	// Route Log
	 	if(allLog.size() > 1) {
	 		for (int i = 1; i < allLog.size(); i++) {
		 		planLine = allLog.get(i-1).getLongitude()+","+allLog.get(i-1).getLatitude()+";"+allLog.get(i).getLongitude()+","+allLog.get(i).getLatitude();
		 		String httpres2 = runHttp.run(head + planLine + config);
		 		double[][] arrpoly = new service().getroutewaypoint(httpres2);
		 		for (double[] ds : arrpoly) {
		 			polyLines.add(ds);
				}	
			}
		 	mapTrackingJson.setPolyLine(polyLines);
		 	mapTrackingJson.setEndTime(allLog.get(0).getDatetimeRecord());
	 	}
	 	
	 	
	 	
		res.setResult(mapTrackingJson);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/getnextline" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> getnextline(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestBody TimeTrackingPayload payload) throws Exception {
		System.out.println("========from vehiclejob.jsp=========");
		System.out.println("POST :tracking/getnextline");
		System.out.println("payloadId: "+payload.getId());
		JsonResponse res = new JsonResponse();
		ConfigLoader cl = new ConfigLoader();
		String planLine = "";
		String head = cl.getProp("osrm.path")+"/route/v1/driving/";
		String config = "?steps=true&geometries=geojson";
		RunHttp runHttp = new RunHttp();
		MapTrackingJson mapTrackingJson = new MapTrackingJson();
		ArrayList<double[]> polyLines = new ArrayList<double[]>();
		VehicleRouteLogDAO vehicleRouteLogDAO = new VehicleRouteLogDAO();
		ArrayList<VehicleRouteLogModel> result = vehicleRouteLogDAO.FindAlllog(payload.getStarttime(), payload.getEndtime(), payload.getId());
		
		if(result.size() > 1) {
			for (int i = 1; i < result.size(); i++) {
				planLine = result.get(i-1).getLongitude()+","+result.get(i-1).getLatitude()+";"+result.get(i).getLongitude()+","+result.get(i).getLatitude();
		 		String httpres2 = runHttp.run(head + planLine + config);
		 		double[][] arrpoly = new service().getroutewaypoint(httpres2);
		 		for (double[] ds : arrpoly) {
		 			polyLines.add(ds);
				}
			}
			mapTrackingJson.setEndTime(result.get(result.size()-1).getDatetimeRecord());
			mapTrackingJson.setPolyLine(polyLines);
			res.setResult(mapTrackingJson);
			res.setStatus("ok");
		}else {
			res.setStatus("err");
		}

		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
}

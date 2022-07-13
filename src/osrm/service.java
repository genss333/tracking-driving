package osrm;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import misl.dao.OrdersRouteDAO;
import misl.dao.VehicleJobSessionDAO;
import misl.dao.ZoneDAO;
import misl.spring.model.OrdersRouteModel;
import misl.spring.model.VehicleJobSessionModel;
import misl.spring.model.ZoneModel;
import misl.spring.model.json.CustomerRouteJson;
import misl.spring.model.json.OsrmJson;
import misl.spring.model.payload.JobAndDistance;
import misl.testcode.OSRMPayload;
import misl.testcode.RunHttp;

public class service {
	
	public OsrmJson getindex(String httpres) {
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
	public double[][] getwaypoint(String httpres) {
		OSRMPayload osrmJson = new OSRMPayload();
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(httpres, JsonObject.class);
		JsonArray trips = json.getAsJsonArray("trips");
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
	public double[][] getroutewaypoint(String httpres) {
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
	public CustomerRouteJson getRouteCustomer(String httpres) {
		CustomerRouteJson osrmJson = new CustomerRouteJson();
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(httpres, JsonObject.class);

		double[][] TSP =  getwaypoint(httpres);
		
		JsonArray trips = json.getAsJsonArray("trips");

		JsonObject geometry = trips.get(0).getAsJsonObject();
		JsonObject geo = geometry.get("geometry").getAsJsonObject();

		double distance = trips.get(0).getAsJsonObject().get("distance").getAsDouble();
		double duration = trips.get(0).getAsJsonObject().get("duration").getAsDouble();
		

//		for (int i = 0; i < duration.length; i++) {
//			System.out.println("Distance:"+distance[i] +
//					" Duration:"+duration[i]);
//		}


//		for (String[] strings : TSP) {
//			System.out.println("Lng:"+strings[0]+" Lat:"+strings[1]);
//		}

//		System.out.println(coordinates.size());
		
		BigDecimal bd = new BigDecimal(distance/1000.0).setScale(2, RoundingMode.HALF_UP);
        double newInput = bd.doubleValue();
		osrmJson.setTSPLocation(TSP);
		osrmJson.setDistance(newInput);
		osrmJson.setDuration(duration);
		return osrmJson;
	}
	
	public JobAndDistance GetJobAndDistance(int jobsessionId) throws SQLException, IOException {
		String latlng = "";
		String config = "?steps=true&geometries=geojson";
		String head = "http://127.0.0.1:5000/trip/v1/driving/";
		RunHttp runHttp = new  RunHttp();
		
		JobAndDistance jobSessionDistance = new JobAndDistance();
		
		ArrayList<OrdersRouteModel>  job = new OrdersRouteDAO().FindByJobSessionId(jobsessionId);
	    ZoneModel zone = new  ZoneDAO().FindByVehicleJobSessionID(jobsessionId);
		for (OrdersRouteModel ordersRouteModel : job) {
			latlng += ordersRouteModel.getOrderId().getLongitude() + "," +  ordersRouteModel.getOrderId().getLatitude() + ";";
		}
		latlng = latlng.substring(0,(latlng.length() - 1));
		String httpres2 = runHttp.run(head +zone.getProviderId().getLongitude() + "," +zone.getProviderId().getLatitude() + " ; " + latlng + config);

		OsrmJson osrmJsondash = new service().getindex(httpres2);
		
		return null;
	}
	
}

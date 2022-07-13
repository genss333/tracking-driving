package misl.testcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.print.DocFlavor.STRING;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Route {
	public static void main(String[] args) throws IOException {
		Route route = new Route();
		route.route();
		String.valueOf(12);
		
	}
	private void route() throws IOException {
	/*
	 * STEP !!
	 * 1. Select order_route.
	 * 2. throw it to OSRM for find Index.
	 * 3. Sort order By Index.
	 * 4. Find Distance and Duration.
	 * */
		ArrayList<OrdersRouteModel> orderList = new ArrayList<OrdersRouteModel>();
		double[][] latlngs = {
				{16.245967404321693, 103.24402774199788}, //depot
				{16.247139494323022, 103.25525897913037}, //D1
				{16.242363661206365, 103.24483699722977}, //D2
 				{16.250643428582271, 103.24671592775736}, 
				{16.244102700786517, 103.25102800601586}};
		
		// 1. Select order_route.
		for (int i = 0; i < latlngs.length; i++) {
			OrdersRouteModel order = new OrdersRouteModel();
			order.setIndex(0);
			order.setDistance(0);
			order.setDuration(0);
			order.setLat(String.valueOf(latlngs[i][0]));
			order.setLng(String.valueOf(latlngs[i][1]));
			order.setOrderRouteId(i);
			orderList.add(order);
		}
		
		// 2. throw it to OSRM for find Index.
		for (OrdersRouteModel order : orderList) {
			System.out.println("Id:"+order.getOrderRouteId()+" Lat:"+order.getLat()+" Lng:"+order.getLng()+" Index:"+order.getIndex());
		}
		
		String latlng = "";
		String head = "http://127.0.0.1:5000/trip/v1/driving/";
		String config = "?steps=true&geometries=geojson";
		RunHttp runHttp = new RunHttp();
		
		for (OrdersRouteModel order : orderList) {
			latlng += order.getLng()+","+order.getLat()+";";
		}
		latlng = latlng.substring(0, latlng.length()-1);
		System.out.println("HTTP: "+head+latlng+config);
		String httpres = runHttp.run(head+latlng+config);
		
		OSRMPayload osrmJson = getindex(httpres);
		int index[] = osrmJson.getIndex();
		for (int i = 0; i < index.length; i++) {
			orderList.get(i).setIndex(index[i]);
		}
		// 3. Sort order By Index.
		Collections.sort(orderList);
		
		// 4. Find Distance and Duration.
		String osrm = "http://127.0.0.1:5000/route/v1/car/";
		String configs = "?overview=full&geometries=polyline&steps=true&annotations=true";
		String url = "";
		
		for (int i = 1; i < orderList.size(); i++) {
			url = osrm+orderList.get(i-1).getLng()+","+orderList.get(i-1).getLat()+";"+
					orderList.get(i).getLng()+","+orderList.get(i).getLat()+configs;
			httpres = runHttp.run(url);
			double arrres[] = getDistanceDuration(httpres);
			orderList.get(i).setDistance(arrres[0]);
			orderList.get(i).setDuration(arrres[1]);
		}
		
		url = osrm + orderList.get(orderList.size()-1).getLng()+","+orderList.get(orderList.size()-1).getLat()+";"+
				orderList.get(0).getLng()+","+orderList.get(0).getLat()+configs;
		httpres = runHttp.run(url);
		double arrres[] = getDistanceDuration(httpres);
		orderList.get(0).setDistance(arrres[0]);
		orderList.get(0).setDuration(arrres[1]);
		
		
		System.out.println("=====View After Route =====");
		for (int i = 1; i < orderList.size(); i++) {
			System.out.println("Id:"+orderList.get(i).getOrderRouteId()+
					" Lat:"+orderList.get(i).getLat()+
					" Lng:"+orderList.get(i).getLng()+
					" Distance:"+orderList.get(i).getDistance()+
					" Duration:"+orderList.get(i).getDuration()+
					" Index:"+orderList.get(i).getIndex());
		}
		System.out.println("Id:"+orderList.get(0).getOrderRouteId()+
				" Lat:"+orderList.get(0).getLat()+
				" Lng:"+orderList.get(0).getLng()+
				" Distance:"+orderList.get(0).getDistance()+
				" Duration:"+orderList.get(0).getDuration()+
				" Index:"+orderList.get(0).getIndex());
		
		
		
	}
	
	
	private OSRMPayload getindex(String httpres) {
		OSRMPayload osrmJson = new OSRMPayload();
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(httpres,JsonObject.class);
		
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
			TSP[i][0]  = coordinates.get(i).getAsJsonArray().get(0).getAsString();
			TSP[i][1] = coordinates.get(i).getAsJsonArray().get(1).getAsString();
		}
		osrmJson.setIndex(index);
		osrmJson.setTSPLocation(TSP);
		return osrmJson;
	}
	
	private double[] getDistanceDuration(String httpres) {
		double[] res = new double[2];
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(httpres,JsonObject.class);
		JsonArray routes = json.getAsJsonArray("routes");
		JsonObject objJson = routes.get(0).getAsJsonObject();
		res[0] = objJson.get("distance").getAsDouble();
		res[1] = objJson.get("duration").getAsDouble();
		
		return res;
	}
}

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import misl.config.ConfigLoader;
import misl.spring.model.extra.JsonResponse;
import misl.spring.model.json.Testlatlng;
import misl.testcode.OSRMPayload;
import misl.testcode.RunHttp;
import osrm.service;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping(value = { "/testplan" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> test(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GET :provider/findorder");
		JsonResponse res = new JsonResponse();
		ConfigLoader cl = new ConfigLoader();
		ArrayList<double[]> polyLines = new ArrayList<double[]>();
		ArrayList<double[][]> dashedLines = new ArrayList<double[][]>();
		ArrayList<Testlatlng> alljob = new ArrayList<Testlatlng>();
		Testlatlng center = new Testlatlng("16.254788725560726", "103.2350389816385");
		alljob.add(new Testlatlng("16.250734", "103.247433"));
		alljob.add(new Testlatlng("16.247712091927525", "103.25709707558285"));
		String planLine = "";
		String head = cl.getProp("osrm.path")+"/route/v1/driving/";
		String config = "?steps=true&geometries=geojson";
		RunHttp runHttp = new RunHttp();
		
		// Center => First location
		planLine = center.getLng()+","+center.getLat()+";"+alljob.get(0).getLng()+","+alljob.get(0).getLat();
System.out.println(head + planLine + config);
		String httpres = runHttp.run(head + planLine + config);
		double[][] c2f = new service().getroutewaypoint(httpres);
		dashedLines.add(c2f);
		// First location => Next location
		planLine = "";
		for (int i = 1; i < alljob.size(); i++) {
			planLine = alljob.get(i-1).getLng()+","+alljob.get(i-1).getLat()+";"+planLine + alljob.get(i).getLng()+","+alljob.get(i).getLat();
			httpres = runHttp.run(head + planLine + config);
			double[][] n2n = new service().getroutewaypoint(httpres);
			dashedLines.add(n2n);
		}
		// Last location => Center
		planLine = alljob.get(alljob.size()-1).getLng()+","+alljob.get(alljob.size()-1).getLat()+";"+center.getLng()+","+center.getLat();
		System.out.println(head + planLine + config);
		httpres = runHttp.run(head + planLine + config);
		double[][] n2l = new service().getroutewaypoint(httpres);
		dashedLines.add(n2l);
		
		res.setResult(dashedLines);
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
}

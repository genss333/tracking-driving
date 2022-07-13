package misl.spring.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import misl.dao.DriverDAO;
import misl.dao.VehicleDAO;
import misl.spring.model.DriverModel;
import misl.spring.model.VehicleModel;
import misl.spring.model.extra.JsonResponse;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ModelAndView homepage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ModelAndView model = new ModelAndView("Scen1/index");
		ArrayList<DriverModel> driverList = new DriverDAO().FindAll();
		ArrayList<VehicleModel> vehicleList = new VehicleDAO().FindAll();
		model.addObject("driverList",driverList);
		model.addObject("vehicleList",vehicleList);
		return model;
	}
	@RequestMapping(value = { "/addvehicle" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> addvehicle(HttpSession session, HttpServletRequest request,
			HttpServletResponse response,@RequestBody VehicleModel model) throws Exception {
		System.out.println("GET :provider/findorder");
		VehicleDAO vehicleDAO = new VehicleDAO();
		int vid = vehicleDAO.Add(model);
		System.out.println(vid);
		JsonResponse res = new JsonResponse();
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/addDriver" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> addDriver(HttpSession session, HttpServletRequest request,
			HttpServletResponse response,@RequestBody DriverModel bean) throws Exception {
		System.out.println("GET :provider/findorder");
		int id = new DriverDAO().Add(bean);
		JsonResponse res = new JsonResponse();
		res.setMessage("id:"+id);
		
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	


}

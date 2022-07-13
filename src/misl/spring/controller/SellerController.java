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
import org.springframework.web.servlet.ModelAndView;

import misl.dao.OrdersDAO;
import misl.dao.OwnerDAO;
import misl.spring.model.OrdersModel;
import misl.spring.model.OwnerModel;
import misl.spring.model.extra.JsonResponse;
import misl.spring.model.json.OrdersModelJson;
import misl.spring.model.payload.SellerAddOrderPayload;

@Controller
@RequestMapping("/seller")
public class SellerController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Page: Seller");
		ModelAndView model = new ModelAndView("Scen1/seller/index");
		return model;
	}
	@RequestMapping(value = "/vehiclejob/{jobsectionId}", method = RequestMethod.GET)
	public ModelAndView vehiclejobdetails(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@PathVariable int jobsectionId) {
		ModelAndView model = new ModelAndView("Scen1/seller/pagevehiclejob");
		model.addObject("jobsectionId", jobsectionId);
		return model;
	}
	@RequestMapping(value = "/{providerId}/{date}", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@PathVariable int providerId, @PathVariable String date) {
		ModelAndView model = new ModelAndView("Scen1/seller/pagelogistic");
		model.addObject("date", date);
		model.addObject("providerId", providerId);
		return model;
	}
	
	@RequestMapping(value = { "/findorder"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<OrdersModelJson>> findproduct(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("GET :seller/findorder");
		ArrayList<OrdersModelJson> ordersJson = new ArrayList<OrdersModelJson>();
		//ArrayList<OrdersModel> order = ordersDAO.FindAll();
		ArrayList<OwnerModel> orders = new OwnerDAO().FindAll();
		for (OwnerModel ordersModel : orders) {
			OrdersModelJson ordersModelJson = new OrdersModelJson();
			ordersModelJson.setOrderId(ordersModel.getOrderId().getOrderId());
			ordersModelJson.setAddress(ordersModel.getOrderId().getAddress());
			ordersModelJson.setAmphur(ordersModel.getOrderId().getAmphur());
			ordersModelJson.setTambon(ordersModel.getOrderId().getTambon());
			ordersModelJson.setProvince(ordersModel.getOrderId().getProvince());
			ordersModelJson.setFirstname(ordersModel.getOrderId().getFirstname());
			ordersModelJson.setLastname(ordersModel.getOrderId().getLastname());
			ordersModelJson.setOrderNumber(ordersModel.getOrderId().getOrderNumber());
			ordersModelJson.setPhoneNumber(ordersModel.getOrderId().getPhoneNumber());
			ordersModelJson.setProviderModel(ordersModel.getProviderId());
			ordersModelJson.setDate(ordersModel.getDate());
			ordersJson.add(ordersModelJson);
		}
		return new ResponseEntity<ArrayList<OrdersModelJson>>(ordersJson, HttpStatus.OK);
	}
	@RequestMapping(value = { "/findproductmoc"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<SellerAddOrderPayload>> findproductmoc(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("GET :seller/findproductmoc");
		
		ArrayList<OwnerModel> owner = new OwnerDAO().FindAllSameDate();
		ArrayList<SellerAddOrderPayload> sellerorder = new ArrayList<SellerAddOrderPayload>();
		
		for (OwnerModel ownerList : owner) {
			SellerAddOrderPayload sellerorderlist = new SellerAddOrderPayload();
			sellerorderlist.setDate(ownerList.getDate());
			sellerorderlist.setLogisticName(ownerList.getProviderId().getName());
			sellerorderlist.setProviderId(ownerList.getProviderId().getProviderId());
			sellerorderlist.setJobSuccessful(11);
			sellerorderlist.setJobAll(30);
			sellerorderlist.setJobFault(19);
			sellerorderlist.setDistanceSuccessful(92.5);
			sellerorderlist.setDistanceAll(191.5);
			sellerorderlist.setDistanceFault(0);
			
			sellerorder.add(sellerorderlist);
		}
		
		return new ResponseEntity<ArrayList<SellerAddOrderPayload>>(sellerorder, HttpStatus.OK);
	}

}

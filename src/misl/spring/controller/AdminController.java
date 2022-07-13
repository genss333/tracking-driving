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

import misl.dao.EmployeeDAO;
import misl.dao.ProviderDAO;
import misl.spring.model.EmployeeModel;
import misl.spring.model.ProviderModel;
import misl.spring.model.extra.JsonResponse;
import misl.spring.model.json.CompanyAdminJson;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("Scen1/admin/index");
		return model;
	}
	@RequestMapping(value = { "/temp" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> temp(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GET :provider/findorder");
		JsonResponse res = new JsonResponse();
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/findcompany/{status}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> findCompany(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int status) throws Exception {
		System.out.println("GET :admin/findnonapproved");
		JsonResponse res = new JsonResponse();
		ProviderDAO providerDAO = new ProviderDAO();
		ArrayList<ProviderModel> providerList = providerDAO.FindAllCompany(status);
		res.setResult(providerList);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	@RequestMapping(value = { "/findcompanyin" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> findcompanyin(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GET :admin/findnonapproved");
		JsonResponse res = new JsonResponse();
		ArrayList<CompanyAdminJson> companyList = new ArrayList<CompanyAdminJson>();
		ProviderDAO providerDAO = new ProviderDAO();
		ArrayList<ProviderModel> providerList = providerDAO.FindAllCompany(1);
		for (ProviderModel providerModel : providerList) {
			CompanyAdminJson companyAdminJson = new CompanyAdminJson();
			EmployeeModel employeeModel = new EmployeeDAO().FindByProviderId(providerModel.getProviderId());
			companyAdminJson.setEmployeeModel(employeeModel);
			companyAdminJson.setProviderModel(providerModel);
			companyList.add(companyAdminJson);
		}
		res.setResult(companyList);
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/approved/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> approved(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) throws Exception {
		System.out.println("GET :admin/approved");
		JsonResponse res = new JsonResponse();
		ProviderDAO providerDAO = new ProviderDAO();
		ProviderModel bean = providerDAO.FindByID(id);
		if(bean.getProviderId() > 0) {
			bean.setProviderStatus(1);
			providerDAO.Update(bean);
			EmployeeModel employeeModel = new EmployeeModel();
			employeeModel.setUsername("admin"+bean.getProviderId());
			employeeModel.setPassword("12345678");
			employeeModel.setFirstname("Provider");
			employeeModel.setLastname(bean.getName());
			employeeModel.setPhoneNumber(bean.getPhone());
			employeeModel.setAddress(bean.getAddress()+","+bean.getTambon()+","+bean.getAmphur()+","+bean.getProvince());
			employeeModel.setRoleid(1);
			employeeModel.setProviderId(bean);	
			int result = new EmployeeDAO().Add(employeeModel);
			res.setStatus("ok");
			res.setMessage("Updated & add admin !");
		}else {
			res.setStatus("error");
			res.setMessage("Id not found !");
		}
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/rejected/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> rejected(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int id) throws Exception {
		System.out.println("GET :admin/rejected");
		JsonResponse res = new JsonResponse();
		ProviderDAO providerDAO = new ProviderDAO();
		ProviderModel bean = providerDAO.FindByID(id);
		if(bean.getProviderId() > 0) {
			bean.setProviderStatus(2);
			providerDAO.Update(bean);
			res.setStatus("ok");
			res.setMessage("Updated !");
		}else {
			res.setStatus("error");
			res.setMessage("Id not found !");
		}
		return new ResponseEntity<JsonResponse>(res, HttpStatus.OK);
	}
}

package misl.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/report")
public class ReportcController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("Scen1/report/index");
		return model;
	}
}

package com.DailyGroceryShop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.DailyGroceryShop.domain.Complain;
import com.DailyGroceryShop.domain.Order;
import com.DailyGroceryShop.service.ComplainServiceImpl;

@Controller
@RequestMapping("/crmDept")
public class CRMController {
    
	@Autowired
	ComplainServiceImpl complainServiceImpl;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getcrmDeptViewPage(@ModelAttribute("complain") Complain complain, BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest, HttpSession httpSession) {
		List<Complain> complainsInSession = (List<Complain>)httpSession.getAttribute("COMPLAINS_SESSION");
		if(complainsInSession == null ) {
			complainsInSession = complainServiceImpl.getAllComplains();
		}
		httpServletRequest.getSession().setAttribute("COMPLAINS_SESSION", complainsInSession);
		List<Complain> listOfComplains = complainServiceImpl.getAllComplains();
		model.addAttribute("listOfComplains", listOfComplains);
		System.out.println("model listOfComplains "+ listOfComplains);
		return "crmDeptForm";
	}
	
	@RequestMapping(value="/resloveComplain", method=RequestMethod.POST)
	public String crmResloveComplain(@ModelAttribute("complain") Complain complain, BindingResult bindingResult) {
		System.out.println("crmResloveComplain POST "+ complain);
		System.out.println(" complain.getOrderedProd() "+ complain.getOrderedProd());
		complainServiceImpl.resloveComplain(complain);
		return "crmDeptForm";
	}
}

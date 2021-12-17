package com.stock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping(value = ("/home"))
public class HomeController {

	@RequestMapping(value = "/")
	public String home() {
		return "home/home";
	}
	
	@RequestMapping(value = "/home")
	public ModelAndView home2() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home/home");
		return mav;
	}

	
	@RequestMapping(value = "/blank")
	public String blank() {
		return "blank/blank";
	}
	
	
}

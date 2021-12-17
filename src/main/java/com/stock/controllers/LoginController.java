package com.stock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login")
	public String home(/*Locale locale, Model model*/) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		return "login/login";
	}
	
	@RequestMapping(value = "/failedlogin")
	public String failedLogin() {
		
		return "login/login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout() {
		
		return "login/login";
	}
	
}

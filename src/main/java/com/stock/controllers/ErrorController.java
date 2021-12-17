package com.stock.controllers;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/403")
public class ErrorController {
	
	private static final String REFERER = "referer";

	@RequestMapping(value = "/")
	public String errorPage(Model model, HttpServletRequest request) {
		String lastUrl = request.getHeader(REFERER);
		if (StringUtils.hasLength(lastUrl)) {
			model.addAttribute("backUrl", lastUrl);
		}
		return "errors/403";
	}
}

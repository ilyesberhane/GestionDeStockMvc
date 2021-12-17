package com.stock.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/changelocale") 
public class ChangeLocaleController {

	private static final String REFERER = "referer";
	
	@RequestMapping(value = "/{locale}")
	public String changeLocale(HttpServletRequest request, HttpServletResponse response, @PathVariable String locale) {
//		ApplicationUtils.changeLocale(request, response, locale);
		String lastUrl = request.getHeader(REFERER);
		if (StringUtils.hasLength(lastUrl)) {
			String sub = lastUrl.substring(lastUrl.length()-8, lastUrl.length()-2);
			if(sub.equals("?lang=")) {
				lastUrl = lastUrl.substring(0, lastUrl.length()-8);
			}
			return "redirect:" + lastUrl+"?lang="+locale;
		}
		return "redirect:/?lang="+locale;
	}
	
}

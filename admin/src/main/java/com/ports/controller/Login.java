package com.ports.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class Login {
	public static final String USER_SESSION = "admin";
	public static final String PASS = "pass";


	@RequestMapping(value={"/login"},method = RequestMethod.GET)
	public String get(Model model){
		return "login";
	}
	@RequestMapping(value={"/login"},method = RequestMethod.POST)
	public String get(@RequestParam String user, String password, HttpSession session){
		if (isInvalid(user))
			return "login";

		if (isInvalid(password))
			return "login";

		if (user.equals("admin") && password.equals("admin")) {
			session.setAttribute(USER_SESSION, user);
			session.setAttribute(PASS, password);
		}
		return "redirect:users";
	}
	
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
}

package com.lingoli.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class CreateAccountController {

	Logger logger = Logger.getLogger(CreateAccountController.class);
	
	@RequestMapping(value="/CreateAccount", method=RequestMethod.GET)
	public String CreateAccount(Model model) {
		model.addAttribute("step", "AccountCreate");
		logger.info("Called /CreateAccount");
		return "CreateAccountView";
	}
	
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
		logger.info("Called /index: " + name);
		model.addAttribute("name", name);
		return "index";
	}

}

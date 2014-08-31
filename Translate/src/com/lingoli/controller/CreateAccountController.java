package com.lingoli.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lingoli.domain.User;
 
@Controller
public class CreateAccountController {

	Logger logger = Logger.getLogger(CreateAccountController.class);
	
	@RequestMapping("/CreateAccountForm")
	public String CreateAccountForm(Model model) {
		model.addAttribute("step", "AccountCreateForm");
		logger.info("Called /CreateAccountForm");
		return "CreateAccountForm";
	}
	
	@RequestMapping("/CreateAccount")
	public String CreateAccount(@ModelAttribute User user, Model model) {
		model.addAttribute("step", "AccountCreate");
		logger.info("Called /CreateAccount");
		return "CreateAccountView";
	}
	
	@RequestMapping()
	public String index(Model model) {
		logger.info("Called /(index) ");
		return "index.html";
	}

}

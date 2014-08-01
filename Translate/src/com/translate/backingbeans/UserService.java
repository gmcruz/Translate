package com.translate.backingbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.translate.UserManagerServiceLocal;
import com.translate.domain.User;


@ManagedBean(name="userService")
public class UserService {

	private Logger logger = Logger.getLogger(UserService.class);
	
	@EJB
	private UserManagerServiceLocal userManagerService;
	
	String fname = null;
	String lname = null;
	String username = null;	
	String password = null;
	String reenterpassword = null;

	
	public String createUser(){
		logger.info("fname: " + fname + " lname: " + lname + " username: " + username +  " password: " + password + " reenterpassword: " + reenterpassword);
		User createNewUser = new User(fname, lname, username, password, reenterpassword);		
		userManagerService.createUser(createNewUser);
		login();
		return "UserCreated";
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReenterpassword() {
		return reenterpassword;
	}

	public void setReenterpassword(String reenterpassword) {
		this.reenterpassword = reenterpassword;
	}
	
	public String login () {
	    FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest request = (HttpServletRequest) 
	        context.getExternalContext().getRequest();
	    try {
	      request.login(this.username, this.password);
	    } catch (ServletException e) {
	      context.addMessage(null, new FacesMessage("Login failed."));
	      return "error";
	    }
	    return "login-failed";
	  }

	public void logout() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest request = (HttpServletRequest) 
	        context.getExternalContext().getRequest();
	    try {
	      request.logout();
	    } catch (ServletException e) {
	      context.addMessage(null, new FacesMessage("Logout failed."));
	    }
	  }
	
}

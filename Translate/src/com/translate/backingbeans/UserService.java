package com.translate.backingbeans;

import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.translate.UserManagerServiceLocal;
import com.translate.domain.User;
import com.translate.util.randomString;


@Stateless
@ManagedBean(name="userService")
public class UserService {

	private Logger logger = Logger.getLogger(UserService.class);
	
	@EJB
	private UserManagerServiceLocal userManagerService;
	
	//FOR DEVELOPEMENT PURPOSES
	String fname = randomString.generateRandomString().toLowerCase();
	String lname = randomString.generateRandomString().toLowerCase();
	String username = fname + "@" + lname;	
	String password = fname + lname;
	String reenterpassword = fname + lname;

	/*String fname = null;
	String lname = null;
	String username = null;	
	String password = null;
	String reenterpassword = null;*/
	
	
	
	public String logoutUser(){
		logout();		
		return "UserLoggedOut";
	}
	
	public String retrieveUser(){
		logger.debug("username: " + username);		
		String ret = userManagerService.retrieveUser(username);	
		if("failed".equals(ret)){
									
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
			String message = bundle.getString("user.retrieve.emailFail");			
			
			context.addMessage("retrieveUserForm", new FacesMessage(message));
			
			//FLASH SCOPE WORKS MIGHT NEED ELSEWHERE
			//import com.sun.faces.context.flash.ELFlash;
			//ELFlash.getFlash().put("ForgotLoginMsg", message);			
			
			return "ForgotLogin";
			
		}else{
			return "UserRetrieved";
		}
		
	}
	
	public String createUser(){
		logger.debug("fname: " + fname + " lname: " + lname + " username: " + username +  " password: " + password + " reenterpassword: " + reenterpassword);
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
	
	public String login(){
	    FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
	    try {
	    	request.login(this.username, this.password);
	    } catch (ServletException e) {
	    	context.addMessage(null, new FacesMessage("Login failed."));
	    	return "error";
	    }
	    return "login-failed";
	}

	public void logout(){
	    FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
	    try {
	    	request.logout();
	    } catch (ServletException e) {
	    	context.addMessage(null, new FacesMessage("Logout failed."));
	    }
	}
	
}

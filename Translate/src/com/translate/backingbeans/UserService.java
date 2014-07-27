package com.translate.backingbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;

import com.translate.UserManagerServiceLocal;


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
		logger.info("fname: " + fname + " lname: " + lname + " username: " + username +  " password: " + password);
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
	
	
	
}

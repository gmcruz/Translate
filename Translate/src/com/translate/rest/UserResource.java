package com.translate.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.translate.UserManagerServiceLocal;
import com.translate.domain.User;


@Path("/users")
@Stateless
public class UserResource {
	
	static Logger logger = Logger.getLogger(UserResource.class);
	
	@EJB
	private UserManagerServiceLocal userService;
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("/user/{id: \\d+}")
	public User getUserByIdXmlJson(@PathParam("id") int id){
		return userService.getUserById(id);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("/user/{username}")
	public User getUserByUsernameXmlJson(@PathParam("username") String username){
		return userService.getUserByUsername(username);
	}
	
	@Path("/post")
	@POST	
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response createUser(User user){
		
		logger.info("/post (called as): public Response createUser(User:" + user.toString() + ")");	
						
		userService.createUser(user);
						
		String result = "User created (XML JSON) **: " + user.toString();
		return Response.status(201).entity(result).build();
		
	}
	
	
}

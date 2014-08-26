package com.lingoli.rest;

import java.util.Date;
import java.util.List;

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

import com.lingoli.domain.Base;
import com.lingoli.services.BaseManagerServiceLocal;


@Path("/bases")
@Stateless
public class BaseResource {
	
	static Logger logger = Logger.getLogger(BaseResource.class);
	
	@EJB
	private BaseManagerServiceLocal baseService;
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("/base/{id: \\d+}")
	public Base getBaseByIdXmlJson(@PathParam("id") int id){
		return baseService.getBaseById(id);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Base> getAllBases(){
		return baseService.getAllBases();		
	}
	
	@Path("/post")
	@POST	
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response createBase(Base base){
		
		base.setDateCreated(new Date());
		baseService.createBase(base);
		
		logger.debug("in createBase(Base base): " + base.toString());	
		
		String result = "Base created (XML JSON) **: " + base.toString();
		return Response.status(201).entity(result).build();
		
	}
	
	
}

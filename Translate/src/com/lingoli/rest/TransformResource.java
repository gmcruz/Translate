package com.lingoli.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;

import com.lingoli.services.TransformationManagerServiceLocal;


@Path("/transformations")
@Stateless
public class TransformResource {

	private static Logger logger = Logger.getLogger(TransformResource.class);
	
	@EJB
	private TransformationManagerServiceLocal transformationService;
	
	@Context
	SecurityContext securityContext;
	
	@GET
	@Produces({"application/json", "application/xml"})
	public String getTransformation(@QueryParam("textToProcess") String textToProcess, @QueryParam("fromLang") String fromLang, @QueryParam("toLang") String toLang){

		String json;		
		json = "    {											" +
				"        \"language\": {						" +
				"        	\"from\": \"de_DE\",				" +
				"			\"to\": \"en_US\"					" +
				"        },										" +
				"}												"; 
		
		return json;		
	}
	
	
	@Path("/post")
	@POST	
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public String postTransformation(@FormParam("textToProcess") String textToProcess, @FormParam("fromLang") int fromLang, @FormParam("toLang") int toLang){
		
		logger.debug("POST GC CALLED THE fromLang: " + fromLang + " toLang: " + toLang + " textToProcess: " + textToProcess);
		return transformationService.processTransformation(textToProcess, fromLang, toLang);

	}

	
	

}

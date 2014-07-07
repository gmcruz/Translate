package com.translate.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.translate.TransformationManagerServiceLocal;


@Path("/transformations")
@Stateless
public class TransformResource {

	private static Logger logger = Logger.getLogger(TransformResource.class);
	
	@EJB
	private TransformationManagerServiceLocal transformationService;
	
	
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
		
		logger.debug("POST GC CALLED THE fromLang: " + fromLang);
		logger.debug("POST GC CALLED THE toLang: " + toLang);
		logger.debug("POST GC CALLED THE textToProcess: " + textToProcess);	
		
		return transformationService.processTransformation(textToProcess, fromLang, toLang);

	}

	
	

}

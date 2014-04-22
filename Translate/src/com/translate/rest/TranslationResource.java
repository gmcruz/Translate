package com.translate.rest;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.translate.TranslationManagerServiceLocal;
import com.translate.domain.Translation;


@Path("/translations")
@Stateless
public class TranslationResource {

	private static Logger logger = Logger.getLogger(TranslationResource.class);
	
	@EJB
	private TranslationManagerServiceLocal translationService;
	
	
	@GET
	@Produces({"application/json", "application/xml"})
	public List<Translation> getAllTranslations(){
		return translationService.getAllTranslations();		
	}
	
	@Path("/post")
	@POST	
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response createTranslation(Translation translation){
		
		translation.setDateCreated(new Date());
		translationService.createTranslation(translation);
		
		logger.debug("In createTranslation(JSON XML) " + translation.toString());
		
		String result = "Translation Created (XML JSON)" + translation.toString();		
		return Response.status(201).entity(result).build();
		
	}

	@Path("/{id}")
	@GET	
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Translation getTranslation(@PathParam("id") int id){	
		return translationService.getTranslationById(id);
	}	
	
	@Path("/{id}")
	@PUT
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response updateTranslation(@PathParam("id") int id,Translation translation) throws Exception{
		translation.setId(id);				
		translationService.updateTranslation(translation);
		
		String result = "Translation Updated: " + translation.toString();
		return Response.status(201).entity(result).build();
	}
	



}

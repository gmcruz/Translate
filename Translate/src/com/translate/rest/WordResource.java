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

import com.translate.WordManagerServiceLocal;
import com.translate.domain.Word;


@Path("/words")
@Stateless
public class WordResource {
	
	static Logger logger = Logger.getLogger(WordResource.class);
	
	@EJB
	private WordManagerServiceLocal wordService;
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("/word/{id: \\d+}")
	public Word getWordByIdXmlJson(@PathParam("id") int id){
		return wordService.getWordById(id);
	}
	
		
	@Path("/post")
	@POST	
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response createWordTranslation(Word word){
		
		logger.debug("/post (prior to create): " + word.toString());
		
		wordService.createWord(word);
		
		logger.debug("in createWord(Word word): " + word.toString());	
		
		String result = "Word created (XML JSON) **: " + word.toString();
		return Response.status(201).entity(result).build();
		
	}
	
	
}

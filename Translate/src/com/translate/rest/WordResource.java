package com.translate.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

	@Inject
	Word oldWord;

	@Inject
	Word newWord;
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("/word/{id: \\d+}")
	public Word getWordByIdXmlJson(@PathParam("id") int id){
		return wordService.getWordById(id);
	}
	
		
	@Path("/post")
	@POST	
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response createWordTranslation(@FormParam("fromWord") String fromWord, 
											@FormParam("word") String word, 
											@FormParam("localeid") int localeid, 
											@FormParam("maptoid") int maptoid, 
											@FormParam("fromlocaleid") int fromlocaleid){
		
		logger.debug("/post (prior to createWordTranslation): " + word);
		
		oldWord.setId(maptoid);
		oldWord.setWord(fromWord);
		oldWord.setLocaleid(fromlocaleid);
		
		newWord.setLocaleid(localeid);
		newWord.setWord(word);
		
		//1) Find out if the origin word exists if not create.
		
		//2) Check if translation already exists in toLang and make the mapping, if not create and map.

		
		wordService.createWord(newWord);			
		logger.debug("in createWordTranslation(..): " + newWord.toString());	

		wordService.createWord(oldWord);			
		logger.debug("in createWordTranslation(..old): " + oldWord.toString());
		
		String result = "Word created (XML JSON) **: " + newWord.toString();
		return Response.status(201).entity(result).build();
		
	}
	
	
}

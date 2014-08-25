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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.translate.dataaccess.WordMappingDataAccess;
import com.translate.domain.Word;
import com.translate.services.WordManagerServiceLocal;


@Path("/words")
@Stateless
public class WordResource {
	
	static Logger logger = Logger.getLogger(WordResource.class);
	
	@EJB
	private WordManagerServiceLocal wordService;
	
	@EJB
	private WordMappingDataAccess wmdai;

	@Inject
	Word originWord;

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
		
		logger.debug("/post (called as): public Response createWordTranslation(fromWord:" + fromWord + " word:" + word + " localeid:" + localeid + " maptoid:" + maptoid + " fromlocaleid:"+ fromlocaleid + ")");	
		
		originWord.setId(maptoid);
		originWord.setWord(fromWord);
		originWord.setLocaleid(fromlocaleid);
		
		newWord.setLocaleid(localeid);
		newWord.setWord(word);

		//1) Find out if the origin word exists if not create.
			boolean originWordExists = false;
			if(originWord.getId() > 0 && wordService.getWordById(originWord.getId()) != null){
				logger.debug("in createWordTranslation("+word+")     -    "+ originWord.getId()+" > 0 && wordService.getWordById("+originWord.getId()+") != null");
				wordService.mergeWord(originWord);
				originWordExists = true;
			}			
			if(!originWordExists){
				Word tempOriginWord = wordService.getWordByString(originWord.getWord(), originWord.getLocaleid());
				if(tempOriginWord instanceof Word && tempOriginWord.getId() > 0){
					originWordExists = true;
					originWord = tempOriginWord;
					logger.debug("in createWordTranslation(" + word + ")     -    !originWordExists" + tempOriginWord.toString());
				}	
			}			
			if(!originWordExists){			
				wordService.createWord(originWord);
				logger.debug("in createWordTranslation(" + word + ")     -    !originWordExists wordService.createWord(originWord) > AFTER: " + originWord.toString());
			};
		
			
		//2) Check if translation already exists in toLang and make the mapping, if not create and map.
			boolean newWordExists = false;			
			
			Word tempNewWord = wordService.getWordByString(newWord.getWord(), newWord.getLocaleid());
			if(tempNewWord instanceof Word && tempNewWord.getId() > 0){
				newWordExists = true;
				newWord = tempNewWord;
				logger.debug("in createWordTranslation(" + word + ")     -    tempNewWord instanceof Word && " + tempNewWord.getId() + " > 0 " + tempNewWord.toString());
			}	
						
			if(!newWordExists){			
				wordService.createWord(newWord);
				logger.debug("in createWordTranslation(" + word + ")     -    !newWordExists wordService.createWord(newWord) > AFTER: " + newWord.toString());
			};
				
			logger.debug("in createWordTranslation(..newWord): " + newWord.toString());			
			logger.debug("in createWordTranslation(..originWord): " + originWord.toString());
			
					
			
		//3) Now you have the newWord and the originWord complete in Word objects it is time to map them together.
			//Only map if both the words are now objects that are persisted 
			if(originWord.getId() > 0 && newWord.getId() > 0){
				logger.debug("in createWordTranslation() GOING TO MAP THESE IDS: " + originWord.getId() + " > 0 && " + newWord.getId() + " > 0 ");
				wmdai.createWordMappingDAO(originWord, newWord);
			}
		
		
		String result = "Word created (XML JSON) **: " + newWord.toString();
		return Response.status(201).entity(result).build();
		
	}
	
	
}

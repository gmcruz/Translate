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


@Path("/tests")
@Stateless
public class TestResource {

	private static Logger logger = Logger.getLogger(TestResource.class);
	
	@EJB
	private TranslationManagerServiceLocal translationService;
	
	
	@GET
	@Produces({"application/json", "application/xml"})
	public String getTest(){
		logger.debug("CALLED THE TEST");
		String json;		
		json =  "{																												" +
	   			"    \"paragraphs\": {																							" +
	   			"        \"paragraph\": [																						" +
	   			"            {																									" +
	   			"                \"sentences\": [																				" +
	   			"                    {																							" +
	   			"                        \"sentence\": [																		" +
	   			"                            {																					" +
	   			"                                \"id\": 2554,																	" +
	   			"                                \"word\": \"yo\",																" +
	   			"                                \"translation\": \"I\",														" +
	   			"                                \"synonyms\": \"self,me\",														" +
	   			"                                \"definition\": \"to be\",														" +
	   			"                                \"punc\": false,																" +
	   			"                                \"uses\": \"I am who I am.\"													" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 2555,																	" +
	   			"                                \"word\": \"estaba\",															" +
	   			"                                \"translation\": \"was\",														" +
	   			"                                \"synonyms\": \"was doing\",													" +
	   			"                                \"definition\": \" to have been doing something\",								" +
	   			"                                \"punc\": false,																" +
	   			"                                \"uses\": \"It was raining outside so I went back in to get my umbrella.\"		" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 25554577,																" +
	   			"                                \"word\": \"en\",																" +
	   			"                                \"translation\": \"in\",														" +
	   			"                                \"synonyms\": \"inside\",														" +
	   			"                                \"definition\": \"to be within something.\",									" +
	   			"                                \"punc\": false,																" +
	   			"                                \"uses\": \"I was confined inside the box.\"									" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 25554577,																" +
	   			"                                \"word\": \"el\",																" +
	   			"                                \"translation\": \"the\",														" +
	   			"                                \"synonyms\": \"it\",															" +
	   			"                                \"definition\": \"article to a noun.\",										" +
	   			"                                \"punc\": false,																" +
	   			"                                \"uses\": \"The box was big.\"													" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 25554577,																" +
	   			"                                \"word\": \"carro\",															" +
	   			"                                \"translation\": \"car\",														" +
	   			"                                \"synonyms\": \"Automobile\",													" +
	   			"                                \"definition\": \"Machine used to move from place to place on 4 wheels.\",		" +
	   			"                                \"punc\": false,																" +
	   			"                                \"uses\": \"The automobile made travel easier.\"								" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 255,																	" +
	   			"                                \"word\": \".\",																" +
	   			"                                \"punc\": true																	" +
	   			"                            }																					" +
	   			"                        ]																						" +
	   			"                    },																							" +
	   			"                    {																							" +
	   			"                        \"sentence\": [																		" +
	   			"                            {																					" +
	   			"                                \"id\": 2556454654,															" +
	   			"                                \"word\": \"el\",																" +
	   			"                                \"translation\": \"he\",														" +
	   			"                                \"synonyms\": \"himself\",														" +
	   			"                                \"definition\": \"He defined\",												" +
	   			"                                \"punc\": false,																" +
	   			"                                \"uses\": \"He put himself between a rock and a hardplace.\"					" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 2555,																	" +
	   			"                                \"ref\": true																	" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 25554577,																" +
	   			"                                \"ref\": \"true\"																" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 25554577,																" +
	   			"                                \"word\": \"la\",																" +
	   			"                                \"translation\": \"the\",														" +
	   			"                                \"synonyms\": \"it (feminine)\",												" +
	   			"                                \"definition\": \"Feminine article to a noun.\",								" +
	   			"                                \"punc\": false,																" +
	   			"                                \"uses\": \"The box was big.\"													" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 25559554577,															" +
	   			"                                \"word\": \"cahuela\",															" +
	   			"                                \"translation\": \"trunk\",													" +
	   			"                                \"synonyms\": \"kokffer, dead mans space\",									" +
	   			"                                \"definition\": \"The storeage area in an Autobile.\",							" +
	   			"                                \"punc\": false,																" +
	   			"                                \"uses\": \"The trunk of our automobile can hold plenty of luggage.\"			" +
	   			"                            },																					" +
	   			"                            {																					" +
	   			"                                \"id\": 255,																	" +
	   			"                                \"word\": \".\",																" +
	   			"                                \"punc\": true																	" +
	   			"                            }																					" +
	   			"                        ]																						" +
	   			"                    }																							" +
	   			"                ]																								" +
	   			"            }																									" +
	   			"        ]																										" +
	   			"    }																											" +
				"}																												"; 
		
		return json;		
	}

	
	
	//Not Used Below ***************************************************
	
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

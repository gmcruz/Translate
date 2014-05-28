package com.translate.rest;

import java.util.Date;

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
		json = "    {																																	" +
				"        \"paragraphs\": [																												" +
				"            {																															" +
				"                \"paragraph\": [																										" +
				"                    {																													" +
				"                        \"sentences\": [																								" +
				"                            {																											" +
				"                                \"sentence\": [																						" +
				"                                    {																									" +
				"                                        \"id\": 1002,																					" +
				"                                        \"word\": \"Zunächst\",																		" +
				"                                        \"translation\": \"first \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1003,																					" +
				"                                        \"word\": \"muss\",																			" +
				"                                        \"translation\": \"must \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1004,																					" +
				"                                        \"word\": \"ich\",																				" +
				"                                        \"translation\": \"I \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\",																			" +
				"                                        \"show\": false																				" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1005,																					" +
				"                                        \"word\": \"sagen\",																			" +
				"                                        \"translation\": \"say \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1006,																					" +
				"                                        \"word\": \",\",																				" +
				"                                        \"punc\": true																					" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1007,																					" +
				"                                        \"word\": \"dass\",																			" +
				"                                        \"translation\": \"that \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1008,																					" +
				"                                        \"word\": \"es\",																				" +
				"                                        \"translation\": \"it \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1009,																					" +
				"                                        \"word\": \"in\",																				" +
				"                                        \"translation\": \"in \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1010,																					" +
				"                                        \"word\": \"Deutschland\",																		" +
				"                                        \"translation\": \"Germany \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\",																			" +
				"                                        \"show\": false																				" +				
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1011,																					" +
				"                                        \"word\": \"eine\",																			" +
				"                                        \"translation\": \"a \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1012,																					" +
				"                                        \"word\": \"Schulpflicht \",																	" +
				"                                        \"translation\": \"mandatory attendance \",													" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1013,																					" +
				"                                        \"word\": \"gibt\",																			" +
				"                                        \"translation\": \"are \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 255,																					" +
				"                                        \"word\": \".\",																				" +
				"                                        \"punc\": true																					" +
				"                                    }																									" +				
				"                                ],																										" +
				"                                \"meaning\": \"sentence 1 one meaning\"																" +
				"                            },																											" +
				"                            {																											" +
				"                                \"sentence\": [																						" +
				"                                    {																									" +
				"                                        \"id\": 1017,																					" +
				"                                        \"word\": \"Jedes\",																			" +
				"                                        \"translation\": \"Each \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1018,																					" +
				"                                        \"word\": \"Kind\",																			" +
				"                                        \"translation\": \"child \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
	   			"                                		 \"id\": 1003,																					" +
	   			"                                		 \"ref\": true																					" +				
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1020,																					" +
				"                                        \"word\": \"also\",																			" +
				"                                        \"translation\": \"so \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1021,																					" +
				"                                        \"word\": \"eine\",																			" +
				"                                        \"translation\": \"a \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1022,																					" +
				"                                        \"word\": \"Schule\",																			" +
				"                                        \"translation\": \"school \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1023,																					" +
				"                                        \"word\": \"besuchen\",																		" +
				"                                        \"translation\": \"visit \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 255,																					" +
				"                                        \"word\": \".\",																				" +
				"                                        \"punc\": true																					" +
				"                                    }																									" +
				"                                ],																										" +
				"                                \"meaning\": \"sentence 2 one meaning\"																" +
				"                            },																											" +
				"                            {																											" +
				"                                \"sentence\": [																						" +
				"                                    {																									" +
				"                                        \"id\": 1027,																					" +
				"                                        \"word\": \"In\",																				" +
				"                                        \"translation\": \"in \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1028,																						" +
				"                                        \"word\": \"jedem\",																						" +
				"                                        \"translation\": \"each \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1029,																						" +
				"                                        \"word\": \"Bundesland\",																						" +
				"                                        \"translation\": \"state \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1030,																						" +
				"                                        \"word\": \"ist\",																						" +
				"                                        \"translation\": \"is \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1031,																						" +
				"                                        \"word\": \"die\",																						" +
				"                                        \"translation\": \"the \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1032,																						" +
				"                                        \"word\": \"Schulpflicht\",																						" +
				"                                        \"translation\": \"compulsory school attendance \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1033,																						" +
				"                                        \"word\": \"geregelt\",																						" +
				"                                        \"translation\": \"controlled \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 255,																						" +
				"                                        \"word\": \".\",																						" +
				"                                        \"punc\": true																						" +
				"                                    }																						" +
				"                                ],																						" +
				"                                \"meaning\": \"sentence 2 one meaning\"																						" +
				"                            },																						" +
				"                            {																						" +
				"                                \"sentence\": [																						" +
				"                                    {																						" +
				"                                        \"id\": 1037,																						" +
				"                                        \"word\": \"Meistens\",																						" +
				"                                        \"translation\": \"mostly \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1038,																						" +
				"                                        \"word\": \"sind\",																						" +
				"                                        \"translation\": \"are \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1039,																						" +
				"                                        \"word\": \"es\",																						" +
				"                                        \"translation\": \"it \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1040,																						" +
				"                                        \"word\": \"neun\",																						" +
				"                                        \"translation\": \"nine \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1041,																						" +
				"                                        \"word\": \"Jahre\",																						" +
				"                                        \"translation\": \"years \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1042,																						" +
				"                                        \"word\": \",\",																						" +
				"                                        \"punc\": true																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1043,																						" +
				"                                        \"word\": \"die\",																						" +
				"                                        \"translation\": \"the \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1044,																						" +
				"                                        \"word\": \"ein\",																						" +
				"                                        \"translation\": \"a \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1045,																						" +
				"                                        \"word\": \"Kind\",																						" +
				"                                        \"translation\": \"child \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1046,																						" +
				"                                        \"word\": \"in\",																						" +
				"                                        \"translation\": \"in \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1047,																						" +
				"                                        \"word\": \"die\",																						" +
				"                                        \"translation\": \"the \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1048,																						" +
				"                                        \"word\": \"Schule\",																						" +
				"                                        \"translation\": \"school \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1049,																						" +
				"                                        \"word\": \"gehen\",																						" +
				"                                        \"translation\": \"go \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																									" +
	   			"                                		 \"id\": 1003,																					" +
	   			"                                		 \"ref\": true																					" +				
				"                                    },																									" +
				"                                    {																						" +
				"                                        \"id\": 255,																						" +
				"                                        \"word\": \".\",																						" +
				"                                        \"punc\": true																						" +
				"                                    }																						" +
				"                                ],																						" +
				"                                \"meaning\": \"sentence 2 one meaning\"																						" +
				"                            }																						" +				

	   			"                ]																								" +
	   			"            }																									" +
	   			"        ]																										" +
	   			"    }	,																						" +
				"            {																						" +
				"                \"paragraph\": [																						" +
				"                    {																						" +
				"                        \"sentences\": [																						" +
				"                            {																						" +
				"                                \"sentence\": [																						" +
				"                                    {																						" +
				"                                        \"id\": 1056,																						" +
				"                                        \"word\": \"In\",																						" +
				"                                        \"translation\": \"in \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1057,																						" +
				"                                        \"word\": \"Deutschland\",																						" +
				"                                        \"translation\": \"Germany \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\",																" +
				"                                        \"show\": false																	" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1058,																						" +
				"                                        \"word\": \"gibt\",																						" +
				"                                        \"translation\": \"are \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1059,																						" +
				"                                        \"word\": \"es\",																						" +
				"                                        \"translation\": \"it \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1060,																		" +
				"                                        \"word\": \"ungefähr\",															" +
				"                                        \"translation\": \"about \",														" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\"																" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"word\": \"36000\"																" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1062,																		" +
				"                                        \"word\": \"öffentliche\",															" +
				"                                        \"translation\": \"public \",														" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\"																" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1063,																		" +
				"                                        \"word\": \"Schulen\",																" +
				"                                        \"translation\": \"Schools\",														" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\",																" +
				"                                        \"show\": false																	" +				
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1064,																						" +
				"                                        \"word\": \"und\",																						" +
				"                                        \"translation\": \"and \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1065,																		" +
				"                                        \"word\": \"nur\",																	" +
				"                                        \"translation\": \"only \",														" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\"																" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"word\": \"2600\"																	" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1067,																		" +
				"                                        \"word\": \"Private\",																" +
				"                                        \"translation\": \"\",																" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\"																" +
				"                                    },																						" +
				"                                    {																						" +
	   			"                                		 \"id\": 1063,																		" +
	   			"                                		 \"ref\": true																		" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 255,																		" +
				"                                        \"word\": \".\",																	" +
				"                                        \"punc\": true																		" +
				"                                    }																						" +
				"                                ],																							" +
				"                                \"meaning\": \"sentence 1 one meaning\"													" +
				"                            },																								" +
				"                            {																								" +
				"                                \"sentence\": [																			" +
				"                                    {																						" +
	   			"                                		 \"id\": 1067,																		" +
	   			"                                		 \"ref\": true																		" +
				"                                    },																						" +
				"                                    {																						" +
	   			"                                		 \"id\": 1063,																		" +
	   			"                                		 \"ref\": true																		" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1074,																		" +
				"                                        \"word\": \"sind\",																" +
				"                                        \"translation\": \"are \",															" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\"																" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1075,																		" +
				"                                        \"word\": \"solche\",																" +
				"                                        \"translation\": \"such \",														" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\"																" +
				"                                    },																						" +
				"                                    {																						" +
	   			"                                		 \"id\": 1063,																		" +
	   			"                                		 \"ref\": true																		" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1077,																						" +
				"                                        \"word\": \",\",																						" +
				"                                        \"punc\": true																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1078,																						" +
				"                                        \"word\": \"für\",																						" +
				"                                        \"translation\": \"for \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1079,																						" +
				"                                        \"word\": \"die\",																						" +
				"                                        \"translation\": \"the \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1080,																						" +
				"                                        \"word\": \"die\",																						" +
				"                                        \"translation\": \"the \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1081,																						" +
				"                                        \"word\": \"Eltern\",																						" +
				"                                        \"translation\": \"parents \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1082,																						" +
				"                                        \"word\": \"etwas\",																						" +
				"                                        \"translation\": \"something \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1083,																						" +
				"                                        \"word\": \"zahlen\",																						" +
				"                                        \"translation\": \"pay \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1084,																						" +
				"                                        \"word\": \"müssen\",																						" +
				"                                        \"translation\": \"must \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 255,																						" +
				"                                        \"word\": \".\",																						" +
				"                                        \"punc\": true																						" +
				"                                    }																						" +
				"                                ],																						" +
				"                                \"meaning\": \"sentence 2 one meaning\"																						" +
				"                            },																						" +
				"                            {																						" +
				"                                \"sentence\": [																						" +
				"                                    {																						" +
				"                                        \"id\": 1088,																						" +
				"                                        \"word\": \"Öffentliche\",																						" +
				"                                        \"translation\": \"Public \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
	   			"                                		 \"id\": 1063,																					" +
	   			"                                		 \"ref\": true																					" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1090,																						" +
				"                                        \"word\": \"werden\",																						" +
				"                                        \"translation\": \"will \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1091,																						" +
				"                                        \"word\": \"vom\",																						" +
				"                                        \"translation\": \"from \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1092,																						" +
				"                                        \"word\": \"Staat\",																						" +
				"                                        \"translation\": \"state \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1093,																						" +
				"                                        \"word\": \"oder\",																						" +
				"                                        \"translation\": \"or \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1094,																						" +
				"                                        \"word\": \"Bundesland\",																						" +
				"                                        \"translation\": \"state \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1095,																						" +
				"                                        \"word\": \"betrieben\",																						" +
				"                                        \"translation\": \"operated \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1096,																						" +
				"                                        \"word\": \"und\",																						" +
				"                                        \"translation\": \"and \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1097,																		" +
				"                                        \"word\": \"sind\",																" +
				"                                        \"translation\": \"are \",															" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\"																" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1098,																		" +
				"                                        \"word\": \"kostenlos\",															" +
				"                                        \"translation\": \"free \",														" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\",																" +
				"                                        \"show\": false																	" +				
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 255,																		" +
				"                                        \"word\": \".\",																	" +
				"                                        \"punc\": true																		" +
				"                                    }																						" +
				"                                ],																						" +
				"                                \"meaning\": \"sentence 2 one meaning\"																						" +
				"                            }																						" +
				"                        ]																						" +
				"                    }																						" +
				"                ]																						" +
				"            },																						" +
				"            {																						" +
				"                \"paragraph\": [																						" +
				"                    {																						" +
				"                        \"sentences\": [																						" +
				"                            {																						" +
				"                                \"sentence\": [																						" +
				"                                    {																						" +
				"                                        \"id\": 1104,																						" +
				"                                        \"word\": \"Mit\",																						" +
				"                                        \"translation\": \"with \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1105,																						" +
				"                                        \"word\": \"sechs\",																						" +
				"                                        \"translation\": \"six \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1106,																						" +
				"                                        \"word\": \"Jahren\",																						" +
				"                                        \"translation\": \"years \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1107,																						" +
				"                                        \"word\": \"kommt\",																						" +
				"                                        \"translation\": \"comes \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1108,																						" +
				"                                        \"word\": \"ein\",																						" +
				"                                        \"translation\": \"a \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1109,																						" +
				"                                        \"word\": \"deutsches\",																						" +
				"                                        \"translation\": \"German \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\",																" +
				"                                        \"show\": false																	" +				
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1110,																						" +
				"                                        \"word\": \"Kind\",																						" +
				"                                        \"translation\": \"child \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1111,																						" +
				"                                        \"word\": \"in\",																						" +
				"                                        \"translation\": \"in \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1112,																						" +
				"                                        \"word\": \"die\",																						" +
				"                                        \"translation\": \"the \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1113,																						" +
				"                                        \"word\": \"Schule\",																						" +
				"                                        \"translation\": \"school \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 255,																						" +
				"                                        \"word\": \".\",																						" +
				"                                        \"punc\": true																						" +
				"                                    }																						" +
				"                                ],																						" +
				"                                \"meaning\": \"sentence 1 one meaning\"																						" +
				"                            },																						" +
				"                            {																						" +
				"                                \"sentence\": [																						" +
				"                                    {																						" +
				"                                        \"id\": 1117,																						" +
				"                                        \"word\": \"Vorher\",																						" +
				"                                        \"translation\": \"previously \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1118,																						" +
				"                                        \"word\": \"war\",																						" +
				"                                        \"translation\": \"was \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1119,																						" +
				"                                        \"word\": \"es\",																						" +
				"                                        \"translation\": \"it \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1120,																						" +
				"                                        \"word\": \"meistens\",																						" +
				"                                        \"translation\": \"mostly \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1121,																						" +
				"                                        \"word\": \"im\",																						" +
				"                                        \"translation\": \"in the \",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 1122,																						" +
				"                                        \"word\": \"Kindergarten\",																						" +
				"                                        \"translation\": \"\",																						" +
				"                                        \"synonyms\": \"{synonyms}\",																						" +
				"                                        \"definition\": \"{definition}\",																						" +
				"                                        \"uses\": \"{uses}\"																						" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 255,																						" +
				"                                        \"word\": \".\",																						" +
				"                                        \"punc\": true																						" +
				"                                    }																						" +
				"                                ],																						" +
				"                                \"meaning\": \"sentence 2 one meaning\"																						" +
				"                            }																						" +
				"                        ]																						" +
				"                    }																						" +
				"                ]																						" +
				"            }																						" +   			
	   			"  ]																											" +
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

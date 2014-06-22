package com.translate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.translate.domain.Transformation;

@Stateless
public class TransformationManagerServiceImpl implements TransformationManagerServiceLocal {

	private Logger logger = Logger.getLogger(TransformationManagerServiceImpl.class);

	@Override
	public String processTransformation(String textToProcess, int fromLang, int toLang) {
		
		Transformation transformation = new Transformation();		
		transformation.setFromLanguageId(fromLang);
		transformation.setToLanguageId(toLang);
		transformation.setTransformationText(getTempJson());
		
		logger.debug("fromLang: " + fromLang + ", toLang:" + toLang);		
		
		Scanner paragraphs = new Scanner(textToProcess);
		
		JSONObject jsonMainTransformation = new JSONObject();
		JSONObject jsonLanguage = new JSONObject();	
		JSONObject jsonParagraphs = new JSONObject();	
		JSONObject jsonSentences = new JSONObject();	
		
		jsonLanguage.put("from", "dg_DG");//TODO
		jsonLanguage.put("to", "ui_UI");//TODO		
		
		jsonMainTransformation.put("language", jsonLanguage);
		
		logger.debug("jsonMainTransformation.toString(): " + jsonMainTransformation.toString());
		//transformation.setTransformationText(jsonMainTransformation.toString());
		
		//Get all the paragraphs first.
		//Pattern p = Pattern.compile(" |-|\\.|\\r\\n|\\n");
		//Pattern p = Pattern.compile("				");
		Pattern p = Pattern.compile("\\n\\n|\\r\\n|\\r");//TODO this seems to work best but there has to be a better way.
		paragraphs.useDelimiter(p);
		
		JSONArray jsonParagraphsArray = new JSONArray();
		
	    while (paragraphs.hasNext()) {	    	
	    	String paragraph = paragraphs.next();
	    	
	    	Scanner sentences = new Scanner(paragraph);
	    	Pattern s = Pattern.compile("\\.");
	    	sentences.useDelimiter(s);
	    	
	    	JSONArray jsonParagraphArray = new JSONArray();
	    	JSONArray jsonSentencesArray = new JSONArray();
	    	JSONObject jsonSentencesObj = new JSONObject();
	    	
	    	while (sentences.hasNext()) {	    	
	 	    	String sentence = sentences.next(); 	 	    	
	 	    	
	 	    	JSONObject jsonSentenceObj = new JSONObject();
	 	    	JSONArray jsonSentenceArray = new JSONArray();
	 	    	
		    	//get the individual words
		    	Scanner words = new Scanner(sentence);
		    	Pattern w = Pattern.compile(" ");
		    	words.useDelimiter(w);
	 	    	String punctuations = "[()<>/;\\*%$,?!:-]";
	 	    	Pattern paragraphDelimPattern = Pattern.compile(punctuations);	
		    	while (words.hasNext()) {	    	
		 	    	String word = words.next(); 	    	
		 	    	Matcher m = paragraphDelimPattern.matcher(word);	 	    	
		 	    	boolean foundPunctuations;
		 	    	foundPunctuations = m.find();
		 	    	if(foundPunctuations){	
	 	    			logger.debug("||" + m.group() + "||**");
	 	    			logger.debug(word.replaceAll(punctuations,"").trim() + "**");
		 	    	} else{
		 	    		logger.debug(word.trim() + "**");	
		 	    		
		 	    		Map<String, String> wordMap = new HashMap<String, String>();
		 	    		wordMap.put("id", "ui_UI");		
		 	    		wordMap.put("word", word.trim());
		 	    		wordMap.put("translation", "ui_UI");	
		 	    		wordMap.put("synonyms", "ui_UI");
		 	    		wordMap.put("definition", "ui_UI");	
		 	    		wordMap.put("uses", "ui_UI");
		 	    		jsonSentenceArray.add(wordMap);		 	    		
		 	    	}
		 	    		
		    	}//end of words.hasNext
			    
			    words.close();		
			    jsonSentenceObj.put("sentence", jsonSentenceArray);
			    jsonSentencesObj.put("sentences", jsonSentenceObj);	 
			    
	    	}//End sentences.hasNext()
	    	
	    	sentences.close();
	    	jsonParagraphArray.add(jsonSentencesArray);	         
	        jsonParagraphsArray.add(jsonParagraphArray);
	        logger.debug("jsonSentencesObj.toString(): " + jsonSentencesObj.toString());
	        
	    } //paragraphs.hasNext()
	    
	    //Close all resources
	    paragraphs.close();
		return transformation.getTransformationText();
		
	}
	
	
	
	public String getTempJson(){
		String json;		
		json = "    {																																	" +
				"        \"language\": {																												" +
				"        	\"from\": \"de_DE\",																										" +
				"			\"to\": \"en_US\"																											" +
				"        },																																" +
				
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
				"                                \"meaning\": \"First I must say that there is manadatory attendance education in Germany.\"																" +
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
				"                                        \"empty\": true,																				" +
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
				"                                \"meaning\": \"Every child must therefore attend school.\"												" +
				"                            },																											" +
				"                            {																											" +
				"                                \"sentence\": [																						" +
				"                                    {																									" +
				"                                        \"id\": 1027,																					" +
				"                                        \"word\": \"In\",																				" +
				"                                        \"translation\": \"in \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1028,																					" +
				"                                        \"word\": \"jedem\",																			" +
				"                                        \"translation\": \"each \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1029,																					" +
				"                                        \"word\": \"Bundesland\",																		" +
				"                                        \"translation\": \"state \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1030,																					" +
				"                                        \"word\": \"ist\",																				" +
				"                                        \"translation\": \"is \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1031,																					" +
				"                                        \"word\": \"die\",																				" +
				"                                        \"translation\": \"the \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1032,																					" +
				"                                        \"word\": \"Schulpflicht\",																	" +
				"                                        \"translation\": \"compulsory school attendance \",											" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1033,																					" +
				"                                        \"word\": \"geregelt\",																		" +
				"                                        \"translation\": \"controlled \",																" +
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
				"                                \"meaning\": \"In each state compulsory education is regulated. \"										" +
				"                            },																											" +
				"                            {																											" +
				"                                \"sentence\": [																						" +
				"                                    {																									" +
				"                                        \"id\": 1037,																					" +
				"                                        \"word\": \"Meistens\",																		" +
				"                                        \"translation\": \"mostly \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1038,																					" +
				"                                        \"word\": \"sind\",																			" +
				"                                        \"translation\": \"are \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1039,																					" +
				"                                        \"word\": \"es\",																				" +
				"                                        \"translation\": \"it \",																		" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
				"                                        \"definition\": \"{definition}\",																" +
				"                                        \"uses\": \"{uses}\"																			" +
				"                                    },																									" +
				"                                    {																									" +
				"                                        \"id\": 1040,																					" +
				"                                        \"word\": \"neun\",																			" +
				"                                        \"translation\": \"nine \",																	" +
				"                                        \"synonyms\": \"{synonyms}\",																	" +
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
				"                                \"meaning\": \"Mostly it's nine years, the need to go a child in the school. \"																						" +
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
				"                                \"meaning\": \"In Germany there are approximately 36,000 public schools and private schools only in 2600. \"													" +
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
				"                                \"meaning\": \"Private schools are those schools for which parents have to pay something. \"																						" +
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
				"                                        \"empty\": true,																	" +
				"                                        \"synonyms\": \"{synonyms}\",														" +
				"                                        \"definition\": \"{definition}\",													" +
				"                                        \"uses\": \"{uses}\"																" +
				"                                    },																						" +
				"                                    {																						" +
				"                                        \"id\": 255,																		" +
				"                                        \"word\": \".\",																	" +
				"                                        \"punc\": true																		" +
				"                                    }																						" +
				"                                ],																						" +
				"                                \"meaning\": \"Public schools are run by the state or province and are free. \"																						" +
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
				"                                \"meaning\": \"At age six, a German child starts school. \"																						" +
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
				"                                \"meaning\": \"Before, it was mostly in kindergarten. \"																						" +
				"                            }																						" +
				"                        ]																						" +
				"                    }																						" +
				"                ]																						" +
				"            }																						" +   			
	   			"  ]																											" +
				"}																												"; 
		return json;
		
	}

}

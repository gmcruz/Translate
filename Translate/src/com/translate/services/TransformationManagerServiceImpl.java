package com.translate.services;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.translate.dataaccess.UserKnownWordDataAccessInterface;
import com.translate.dataaccess.WordDataAccess;
import com.translate.dataaccess.WordMappingDataAccess;
import com.translate.domain.Transformation;
import com.translate.domain.User;
import com.translate.domain.UserKnownWord;
import com.translate.domain.Word;


@Stateless
@DeclareRoles({"user", "admin"})
public class TransformationManagerServiceImpl implements TransformationManagerServiceLocal {

	private Logger logger = Logger.getLogger(TransformationManagerServiceImpl.class);

	@Inject 
	Transformation transformation;
	
	@EJB 
	WordMappingDataAccess wmDAO;
	
	@EJB 
	WordDataAccess wDAO;
	
	@EJB
	UserKnownWordDataAccessInterface ukwDAO;
	
	@EJB
	private UserManagerServiceLocal userService;
	
	@Resource
	SessionContext sessionContext;
	
	public String processTransformation(String textToProcess, int fromLang, int toLang) {

		logger.debug("fromLang: " + fromLang + ", toLang:" + toLang);		
		
		transformation.setFromLanguageId(fromLang);
		transformation.setToLanguageId(toLang);
		
		Scanner paragraphs = new Scanner(textToProcess);
		
		JSONObject jsonMainTransformation = new JSONObject();
		JSONObject jsonLanguage = new JSONObject();	
		
		jsonLanguage.put("from", fromLang);
		jsonLanguage.put("to", toLang);		
		jsonMainTransformation.put("language", jsonLanguage);
		
		logger.debug("BEFORE ukwDAO.getAllUsersKnownByLocaleDAO: fromLang-" + fromLang + " toLang-" + toLang);
		//Find out what words are known to this logged n user.
		User currentUser = userService.getUserByUsername(sessionContext.getCallerPrincipal().getName());
		List<UserKnownWord> knownWords = ukwDAO.getAllUsersKnownByLocaleDAO(currentUser.getId(), toLang);
		
		for(UserKnownWord ukw : knownWords){
			logger.debug("Known Word ID: " + ukw.getWordid());
		}
				
		//Get all the paragraphs first.
		//Pattern p = Pattern.compile(" |-|\\.|\\r\\n|\\n");
		//Pattern p = Pattern.compile("				");
		Pattern p = Pattern.compile("\\n\\n|\\r\\n|\\r|\\r\\n\\n|@@");//TODO this seems to work best but there has to be a better way.
		paragraphs.useDelimiter(p);
				
		JSONArray jsonParagraphsArray = new JSONArray();
		
	    while (paragraphs.hasNext()) {	    	
	    	String paragraph = paragraphs.next();
	    	
	    	Scanner sentences = new Scanner(paragraph);
	    	Pattern s = Pattern.compile("\\.");
	    	sentences.useDelimiter(s);
	    	
	    	JSONArray jsonParagraphArray = new JSONArray();
	    	JSONObject jsonParagraphObj = new JSONObject();
	    	
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
		    	
	 	    	String punctuations = "\\p{P}";	 	    	
	 	    	Pattern paragraphDelimPattern = Pattern.compile(punctuations);
	 	    	
	 	    	logger.debug("pat: " + paragraphDelimPattern.pattern());
	 	    	
		    	while (words.hasNext()) {	    	
		 	    	String word = words.next(); 	    	
		 	    	Matcher m = paragraphDelimPattern.matcher(word);	 	    	
		 	    	boolean foundPunctuations;
		 	    	foundPunctuations = m.find();
		 	    	
		 	    	logger.debug("word to be computed:[" + word + "]");	 	    			
		 	    	
		 	    	if(foundPunctuations){	
		 	    		
	 	    			logger.debug("calling transformFinalWord(:" + word.replaceAll(punctuations,"").trim());	 	    			
	 	    			
		 	    		Map<String, Object> wordMapWord = transformFinalWord(word.replaceAll(punctuations,"").trim(), fromLang, toLang, knownWords);
		 	    		
		 	    		logger.debug("word: " + word + " (" + (word.length()-1) + ")");
	 	    			logger.debug("Punctuation: " + m.group());
	 	    			logger.debug("Punctuation start: " + m.start());	 	    			
	 	    			
		 	    		Map<String, Object> wordMapPunc = new HashMap<String, Object>();
		 	    		wordMapPunc.put("id", "ID");		
		 	    		wordMapPunc.put("word", m.group());
		 	    		wordMapPunc.put("punc", true);
		 	    		
		 	    		//What was first the punctuation or the word
		 	    		if((word.length()-1) == m.start()){
			 	    		jsonSentenceArray.add(wordMapWord);	
			 	    		wordMapPunc.put("place", "rear");
			 	    		jsonSentenceArray.add(wordMapPunc);		
			 	    		logger.debug("REAR Punctuation: " + m.group() + "; complete word: " + word);
		 	    		}else{
		 	    			wordMapPunc.put("place", "front");
		 	    			jsonSentenceArray.add(wordMapPunc);			 	    		
			 	    		jsonSentenceArray.add(wordMapWord);				 	    		
			 	    		logger.debug("FRONT Punctuation: " + m.group()+ "; complete word: " + word);
		 	    		}		 	    		

		 	    	} else if(isNumeric(word.trim())){
		 	    		
		 	    		logger.debug("Numerical word: " + word.trim());			 	    		
		 	    		Map<String, Object> wordMap = new HashMap<String, Object>();		 	    		
		 	    		wordMap.put("word", word.trim());	
		 	    		jsonSentenceArray.add(wordMap);		
		 	    		
		 	    	}
		 	    	else{
		 	    		logger.debug("[NO PUNC] calling transformFinalWord(:" + word.replaceAll(punctuations,"").trim());	 	    				 	    			 	    		
		 	    		Map<String, Object> wordMap = transformFinalWord(word.trim(), fromLang, toLang, knownWords);		 	    		
		 	    		jsonSentenceArray.add(wordMap);	
		 	    		
		 	    	}
		 	    		
		    	}//end of words.hasNext
			    
			    words.close();	
			    
			    //Add the period to finish the sentence
			    Map<String, Object> wordMapPunc = new HashMap<String, Object>();
 	    		wordMapPunc.put("id", "ID");		
 	    		wordMapPunc.put("word", ".");
 	    		wordMapPunc.put("punc", true);
 	    		jsonSentenceArray.add(wordMapPunc);	
			    
			    jsonSentenceObj.put("sentence", jsonSentenceArray);
			    jsonSentenceObj.put("meaning", "NOTAVAILABLE");			    
			    jsonSentencesObj.accumulate("sentences", jsonSentenceObj);
			    
	    	}//End sentences.hasNext()
	    	
	    	sentences.close();
	    	jsonParagraphArray.add(jsonSentencesObj);		 
	    	jsonParagraphObj.put("paragraph", jsonParagraphArray);
	    		    	
	    	jsonParagraphArray.add(jsonSentencesArray);	         
	        jsonParagraphsArray.add(jsonParagraphObj);    		
	        		
	        		
	    } //paragraphs.hasNext()	   
	    
	    jsonMainTransformation.put("paragraphs", jsonParagraphsArray);
	    
	    logger.debug("JSON set to Ship: " + jsonMainTransformation.toString());
		transformation.setTransformationText(jsonMainTransformation.toString());
	    
	    //Close all resources
	    paragraphs.close();
		return transformation.getTransformationText();
		
	}
	
	public Map<String, Object> transformFinalWord(String word, int fromLang, int toLang, List<UserKnownWord> knownWords){
		
		logger.debug("CALLED transformFinalWord(" + word.trim() + ", " + fromLang + ", " + toLang + ")");	
		
		Word w = wDAO.getWordDAOByString(word, fromLang);
		logger.debug("CALLED transformFinalWord( > wDAO.getWordDAOByString(" + w.toString());
		
		Word wm;
		boolean known = false;
		for(UserKnownWord ukw : knownWords){
			if(ukw.getWordid() == w.getId()){
				logger.debug("Known Word ID: " + ukw.getWordid());
				known = true;
				break;
			}	
		}		
		
		Map<String, Object> wordMap = new HashMap<String, Object>();
		
		wm = wmDAO.getSingleWordMapping(word, fromLang, toLang);			
	
		logger.debug("CALLED transformFinalWord( > wmDAO.getSingleWordMapping(" + wm.toString());		
 		
 		wordMap.put("id", Integer.toString(w.getId()));		
 		wordMap.put("word", wm.getWord().trim());
 		wordMap.put("synonyms", wm.getSynonyms());
 		wordMap.put("antonyms", wm.getAntonyms());
 		wordMap.put("definition", wm.getDefinition());	
 		wordMap.put("uses", wm.getUses());
 		if(w.getId() == 0 || wm.getWordMappingTranslation().trim() == "N_A"){
 			wordMap.put("allowChange", (sessionContext.isCallerInRole("admin") || sessionContext.isCallerInRole("user")) ? true : false);
 		}
 		
 		if(known){
 			wordMap.put("translation", "&nbsp;");
 		}else{
 			wordMap.put("translation", wm.getWordMappingTranslation().trim());
 		}
		
		return wordMap;
		
	};
	
	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args){
		
		String textToProcess = "" + 
				"Es „Demokratischen Aufbruch“ wird Zeit, wir. Sie Mr. Mcdonalds ist Bundeskanzlerin.\n\n" +  

				"Aber weiter Kanzlerin: 1986 bekam Doktortitel.\n\n" +  

				"Seither ist Angela Bundeskanzlerin. Sie tritt beim  meist.";						
		
		TransformationManagerServiceImpl impl = new TransformationManagerServiceImpl();
		//impl.processTransformation(textToProcess, 149, 140);
		
		
		String password = "33Luser";		
		try {
			
	        MessageDigest md2 = MessageDigest.getInstance("SHA-256");
	        md2.update(password.getBytes()); 
	        byte byteData[] = md2.digest();	 
	        
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        String pass = sb.toString();
	        System.out.println(sb.toString());	 
	        System.out.println(password.getBytes());
	        
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		
				
	}
	
}



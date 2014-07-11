package com.translate.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.translate.domain.Word;
import com.translate.domain.WordMapping;

@Stateless
public class WordMappingDataAccessImpl implements WordMappingDataAccess{

	private Logger logger = Logger.getLogger(WordMappingDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Word getSingleWordMapping(String word, int fromLang, int toLang) {
		Word returnWord = new Word();
		returnWord.setWord(word);		
		
		logger.debug("CALL-START getSingleWordMapping(" + word + ", " + fromLang + ", " + toLang);
		
		Query qw = em.createQuery("SELECT w FROM Word w WHERE w.localeid = :localeid AND w.word = :word");	
		qw.setParameter("localeid", fromLang);
		qw.setParameter("word", word);
								
		if(qw.getResultList().size() > 0){			
			logger.debug("em.createQuery(SELECT w FROM Word w WHERE w.localeid = :" + fromLang + " AND w.word = :word FOUND NUMBER OF RECORDS(" + qw.getResultList().size() + ")");
			
			Word wo = (Word) qw.getResultList().get(0);
			
			Query qwm = em.createQuery("SELECT wm FROM WordMapping wm WHERE wm.wordid = :wordid");				
			qwm.setParameter("wordid", wo.getId());
			
			if(qwm.getResultList().size() > 0){	
			
				@SuppressWarnings("unchecked")
				List<WordMapping> tempListqwm = qwm.getResultList();
							
				List<String> listWmids = new ArrayList<String>(); 			
				for (WordMapping next : tempListqwm) {
					int wmId = next.getTowordid();
					logger.debug("wmId next.getTowordid():"+wmId);
					listWmids.add(Integer.toString(wmId));
				}			
			
				logger.debug("em.createQuery(SELECT wm FROM WordMapping wm WHERE wm.wordid = :" + wo.getId() + "  FOUND NUMBER OF RECORDS(" + qwm.getResultList().size() + ")");
				
				Query getFinalTranslation = em.createQuery("SELECT w FROM Word w WHERE w.localeid = :localeid AND w.id IN :listWmids");
				getFinalTranslation.setParameter("localeid", toLang);
				getFinalTranslation.setParameter("listWmids", listWmids);			
				
				if(getFinalTranslation.getResultList().size() > 0){		
					logger.debug("em.createQuery(SELECT w FROM Word w WHERE w.localeid = :" + getFinalTranslation.getParameterValue("localeid") + " AND w.id IN :" + getFinalTranslation.getParameterValue("listWmids") + ": " + getFinalTranslation.getResultList().get(0).toString());
					Word treturnWord = (Word) getFinalTranslation.getResultList().get(0);	
					logger.debug("treturnWord: " + treturnWord.toString());
					returnWord.setWordMappingTranslation(treturnWord.getWord());
					
					logger.debug("returnWord.setWordMappingTranslation("+treturnWord.getWordMappingTranslation()+") : " + returnWord.getWordMappingTranslation());
					
				}
			}			
		}
		
		logger.debug("returnWord.getWord(" + returnWord.getWordMappingTranslation() + ")");
		
		logger.debug("CALL-END getSingleWordMapping(" + word + ", " + fromLang + ", " + toLang);
		
		return returnWord;
		
	}

	@Override
	public void createWordMappingDAO(Word word, Word toWord) {
		
		logger.debug("CALL-START createWordMappingDAO(" + word.getId() + ", " + toWord.getId() + ")");
		
		WordMapping wm = new WordMapping();
		wm.setWordid(word.getId());
		wm.setTowordid(toWord.getId());
		
		em.persist(wm);
		em.flush();
				
		logger.debug("CALL-END createWordMappingDAO(" + word.getId() + ", " + toWord.getId() + ") : (AFTER em.flush()) " + wm.toString());
	}
	

	

	
}


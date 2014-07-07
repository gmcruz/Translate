package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.translate.domain.Word;

@Stateless
public class WordMappingDataAccessImpl implements WordMappingDataAccess{

	private Logger logger = Logger.getLogger(WordMappingDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Word getSingleWordMapping(String word, int fromLang, int toLang) {
		logger.debug("getSingleWordMapping(" + word + ", " + fromLang + ", " + toLang);
		
		Query qw = em.createQuery("SELECT w FROM Word w WHERE w.localeid = :localeid AND w.word = :word");	
		qw.setParameter("localeid", fromLang);
		qw.setParameter("word", word);
		@SuppressWarnings("unchecked")
		List<Object> tempListqw = qw.getResultList();	
		
		logger.debug("em.createQuery(SELECT w FROM Word w WHERE w.localeid = :localeid AND w.word = :word " + qw.getResultList().size());
		
		if(qw.getResultList().size() > 0){			
			logger.debug("em.createQuery(SELECT w FROM Word w WHERE w.localeid = :localeid AND w.word = :word  (0): " + qw.getResultList().get(0).toString());
			
			Word wo = (Word) qw.getResultList().get(0);
			
			Query qwm = em.createQuery("SELECT wm FROM WordMapping wm WHERE wm.wordid = :wordid");				
			qwm.setParameter("wordid", wo.getId());
			@SuppressWarnings("unchecked")
			List<Object> tempListqwm = qwm.getResultList();	
			logger.debug("em.createQuery(SELECT wm FROM WordMapping wm WHERE wm.wordid = :wordid  (0): " + qwm.getResultList().get(0).toString());
			
		}
		
		Word wrd = new Word();
		wrd.setWord(word);
		wrd.setLocaleid(fromLang);
		
		return wrd;
		
	}
	

	

	
}


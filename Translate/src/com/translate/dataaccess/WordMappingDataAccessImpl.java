package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.translate.domain.wordmappings_de_DE_en_US;

@Stateless
public class WordMappingDataAccessImpl implements WordMappingDataAccess{

	private Logger logger = Logger.getLogger(WordMappingDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public wordmappings_de_DE_en_US getSingleWordMapping(String word, String fromLang, String toLang, String ds) {
		logger.debug("getSingleWordMapping(" + word + ", " + fromLang + ", " + toLang + ", " + ds);
		
		Query q = em.createQuery("SELECT t FROM wordmappings_de_DE_en_US t");	
		logger.debug("GC em.createQuery(" + q.getResultList().size());
		
		
		/*@SuppressWarnings("unchecked")
		List<Object> tempList = q.getResultList();	*/
		
		
		wordmappings_de_DE_en_US wm = new wordmappings_de_DE_en_US();
	//	wm.setWordMappingText(word);
		wm.setOriginLangId(fromLang);
		wm.setToLangId(toLang);
		
		return wm;
		
	}
	

	

	
}


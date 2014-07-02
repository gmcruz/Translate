package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.translate.domain.WordMapping;

@Stateless
public class WordMappingDataAccessImpl implements WordMappingDataAccess{

	private Logger logger = Logger.getLogger(WordMappingDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public WordMapping getSingleWordMapping(String word, String fromLang, String toLang, String ds) {
		logger.debug("getSingleWordMapping(" + word + ", " + fromLang + ", " + toLang + ", " + ds);
		
		/*Query q = em.createQuery("SELECT t FROM wordmappings_de_DE_en_US t");	
		@SuppressWarnings("unchecked")
		List<Object> tempList = q.getResultList();	*/
		
		
		WordMapping wm = new WordMapping();
		wm.setWordMappingText(word);
		wm.setOriginLangId(fromLang);
		wm.setToLangId(toLang);
		
		return wm;
		
	}
	

	

	
}


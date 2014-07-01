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

	private Logger logger = Logger.getLogger(TranslationDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	/*public List<Translation> getAllTranslationsDAO() {			
		Query q = em.createQuery("SELECT t FROM Translation t");			
		@SuppressWarnings("unchecked")
		List<Translation> tempList = q.getResultList();			
		return tempList;
	}*/


	@Override
	public WordMapping getSingleWordMapping(String word, String fromLang, String toLang, String ds) {
		/*Query q = em.createQuery("SELECT t FROM " + ds + " t");	
		@SuppressWarnings("unchecked")
		List<Object> tempList = q.getResultList();	*/	
		logger.debug("getSingleWordMapping(" + word + ", " + fromLang + ", " + toLang + ", " + ds);
		return new WordMapping();
		
	}
	

	

	
}


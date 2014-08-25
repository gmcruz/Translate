package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.translate.domain.Locale;

@Stateless
public class LocaleDataAccessImpl implements LocaleDataAccess {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Locale> getAllLocalesDAO(){
		Query q = em.createQuery("SELECT b FROM Locale b");
		@SuppressWarnings("unchecked")
		List<Locale> tempList = q.getResultList();
		return tempList;		
	}
		
	@Override
	public Locale getLocaleDAOById(int id) {
		Locale locale = em.find(Locale.class, id);
		return locale;		
	}
			
	@Override
	public void updateLocaleDAO(Locale locale) {
		//TODO id should BE a valid record already existing.
		em.persist(locale);		
	}

	@Override
	public List<Locale> getAllActiveLocalesDAO() {
		Query q = em.createQuery("SELECT b FROM Locale b WHERE b.active = 1");
		@SuppressWarnings("unchecked")
		List<Locale> tempList = q.getResultList();
		return tempList;		
	}
	
	
	
}


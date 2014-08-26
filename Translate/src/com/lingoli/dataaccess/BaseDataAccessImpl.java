package com.lingoli.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lingoli.domain.Base;

@Stateless
public class BaseDataAccessImpl implements BaseDataAccess {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Base> getAllBasesDAO(){
		Query q = em.createQuery("SELECT b FROM Base b");
		@SuppressWarnings("unchecked")
		List<Base> tempList = q.getResultList();
		return tempList;		
	}

	@Override
	public void createBaseDAO(Base base) {
		em.persist(base);		
	}	
	
	@Override
	public Base getBaseDAOById(int id) {
		Base base = em.find(Base.class, id);
		return base;		
	}
			
	@Override
	public void updateBaseDAO(Base base) {
		//TODO id should BE a valid record already existing.
		em.persist(base);		
	}
	
	@Override
	public void deleteBaseByIdDAO(int id) {
		Base base = em.find(Base.class, id);
		em.remove(base);		
	}
	
}


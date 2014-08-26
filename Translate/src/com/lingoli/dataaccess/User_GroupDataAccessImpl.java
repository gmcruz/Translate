package com.lingoli.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.lingoli.domain.User_Group;

@Stateless
public class User_GroupDataAccessImpl implements User_GroupDataAccessInterface {

	private Logger logger = Logger.getLogger(User_GroupDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<User_Group> getAllUser_GroupsDAO() {		
		Query q = em.createQuery("SELECT ug FROM User_Group ug");
		@SuppressWarnings("unchecked")
		List<User_Group> tempList = q.getResultList();
		return tempList;
	}
		
	@Override
	public void createUser_GroupDAO(User_Group user_group) {
		em.persist(user_group);		
		flushUser_GroupEMDAO();//TODO should this be refreshUser_GroupDAO(User_Group user_group)?		
	}
		
	@Override
	public User_Group getUser_GroupDAOById(int id) {
		User_Group user_group = em.find(User_Group.class, id);
		return user_group;	
	}
	
	@Override
	public void deleteUser_GroupByIdDAO(int id) {
		User_Group user_group = em.find(User_Group.class, id);
		em.remove(user_group);
	}
		
	@Override
	public List<User_Group> getUser_GroupDAOByUsername(String username) {
		logger.debug("CALLED BEFORE getUser_GroupDAOByUsername(String: " + username);
		Query qw = em.createQuery("SELECT ug FROM User_Group ug WHERE ug.username = :username");	
		qw.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<User_Group> tempList = qw.getResultList();		
		logger.debug("CALLED AFTER getUser_GroupDAOByUsername(Username: " + username);
		return tempList;
	}

	@Override
	public void refreshUser_GroupEMDAO(User_Group user_group) {
		em.refresh(user_group);
	}

	@Override
	public void mergeUser_GroupEMDAO(User_Group user_group) {
		em.merge(user_group);
	}
	
	@Override
	public void flushUser_GroupEMDAO() {
		em.flush();
	}

}

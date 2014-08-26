package com.lingoli.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.lingoli.domain.User;

@Stateless
public class UserDataAccessImpl implements UserDataAccessInterface {

	private Logger logger = Logger.getLogger(UserDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> getAllUsersDAO() {		
		Query q = em.createQuery("SELECT u FROM User u");
		@SuppressWarnings("unchecked")
		List<User> tempList = q.getResultList();
		return tempList;
	}

	@Override
	public void createUserDAO(User user) {
		em.persist(user);		
		flushUserEMDAO();//TODO should this be refreshUserDAO(User user)?		
	}

	@Override
	public User getUserDAOById(int id) {
		User user = em.find(User.class, id);
		return user;	
	}

	@Override
	public void updateUserDAO(User user) {
		em.persist(user);
	}

	@Override
	public void deleteUserByIdDAO(int id) {
		User user = em.find(User.class, id);
		em.remove(user);
	}

	@Override
	public User getUserDAOByUsername(String username) {
		User userByUsername = new User();
		logger.debug("CALLED BEFORE getUserDAOByUsername(String: " + userByUsername.toString());
		Query qw = em.createQuery("SELECT w FROM User w WHERE w.username = :username");	
		qw.setParameter("username", username);
		if(qw.getResultList().size() > 0){	
			logger.debug("getUserDAOByUsername(Username " + username + ") " + qw.getResultList().get(0).toString());
			userByUsername = (User) qw.getResultList().get(0);
		}
		logger.debug("CALLED AFTER getUserDAOByUsername(Username: " + userByUsername.toString());
		return userByUsername;
	}

	@Override
	public void refreshUserEMDAO(User user) {
		em.refresh(user);
	}

	@Override
	public void mergeUserEMDAO(User user) {
		em.merge(user);
	}

	@Override
	public void flushUserEMDAO() {
		em.flush();
	}

}

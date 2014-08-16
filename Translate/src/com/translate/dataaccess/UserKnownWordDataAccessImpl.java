package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.translate.domain.UserKnownWord;

@Stateless
public class UserKnownWordDataAccessImpl implements UserKnownWordDataAccessInterface {

	private Logger logger = Logger.getLogger(UserKnownWordDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;
			
	@Override
	public List<UserKnownWord> getAllUsersKnownByLocaleDAO(int userid, int localeid) {		
		Query q = em.createQuery("SELECT ukw FROM UserKnownWord ukw WHERE ukw.userid = :userid AND ukw.localeid = :localeid");
		q.setParameter("userid", userid);
		q.setParameter("localeid", localeid);
		@SuppressWarnings("unchecked")
		List<UserKnownWord> tempList = q.getResultList();
		return tempList;
	}
	
	@Override
	public void createUserKnownWordDAO(UserKnownWord userKnownWord) {
		em.persist(userKnownWord);		
		flushUserKnownWordEMDAO();//TODO should this be refreshUserDAO(User user)?		
	}

	@Override
	public UserKnownWord getUserKnownWordDAOById(int id) {
		UserKnownWord userKnownWord = em.find(UserKnownWord.class, id);
		return userKnownWord;	
	}

	@Override
	public void updateUserKnownWordDAO(UserKnownWord userKnownWord) {
		em.persist(userKnownWord);
	}

	@Override
	public void deleteUserKnownWordByIdDAO(int id) {
		UserKnownWord userKnownWord = em.find(UserKnownWord.class, id);
		em.remove(userKnownWord);
	}

	@Override
	public void refreshUserKnownWordEMDAO(UserKnownWord userKnownWord) {
		em.refresh(userKnownWord);
	}

	@Override
	public void mergeUserKnownWordEMDAO(UserKnownWord userKnownWord) {
		em.merge(userKnownWord);
	}

	@Override
	public void flushUserKnownWordEMDAO() {
		em.flush();
	}

}

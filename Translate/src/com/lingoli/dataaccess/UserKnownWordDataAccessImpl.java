package com.lingoli.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.lingoli.domain.UserKnownWord;
import com.lingoli.domain.Word;

@Stateless
public class UserKnownWordDataAccessImpl implements UserKnownWordDataAccessInterface {

	private Logger logger = Logger.getLogger(UserKnownWordDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;
			
	@Override
	public List<UserKnownWord> getAllUsersKnownByLocaleDAO(int userid, int localeid) {	
		
		logger.debug("CALL-START getAllUsersKnownByLocaleDAO(int " + userid + ", int " + localeid + ")");		
		
		Query q = em.createQuery("SELECT ukw FROM UserKnownWord ukw WHERE ukw.userid = :userid AND ukw.localeid = :localeid");
		q.setParameter("userid", userid);
		q.setParameter("localeid", localeid);
		@SuppressWarnings("unchecked")
		List<UserKnownWord> tempList = q.getResultList();
		
		logger.debug("SELECT ukw FROM UserKnownWord ukw WHERE ukw.userid = " + userid + " AND ukw.localeid = " + localeid + " RETURNED # RECORDS: " + q.getResultList().size());
		
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

	@Override
	public UserKnownWord bringUpFromPersistenceEMDAO(UserKnownWord userKnownWord) {
		
		logger.debug("CALL-START bringUpFromPersistenceEMDAO(UserKnownWord userKnownWord)");
		
		Query qw = em.createQuery("SELECT ukw FROM UserKnownWord ukw WHERE ukw.userid = :userid AND ukw.localeid = :localeid AND ukw.wordid = :wordid");	
		qw.setParameter("userid", userKnownWord.getUserid());
		qw.setParameter("localeid", userKnownWord.getLocaleid());
		qw.setParameter("wordid", userKnownWord.getWordid());
								
		if(qw.getResultList().size() > 0){
			userKnownWord = (UserKnownWord) qw.getResultList().get(0);
			logger.debug(userKnownWord.toString());			
		}	
		
		return userKnownWord;
		
	}
	
}

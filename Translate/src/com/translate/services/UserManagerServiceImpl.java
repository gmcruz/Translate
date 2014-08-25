package com.translate.services;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.translate.dataaccess.UserDataAccessInterface;
import com.translate.dataaccess.UserKnownWordDataAccessInterface;
import com.translate.dataaccess.User_GroupDataAccessInterface;
import com.translate.domain.User;
import com.translate.domain.UserKnownWord;
import com.translate.domain.User_Group;

@Stateless
public class UserManagerServiceImpl implements UserManagerServiceLocal, UserManagerServiceRemote {
	
	@EJB
	UserDataAccessInterface userDAO;
	
	@EJB
	UserKnownWordDataAccessInterface userKnownWordDAO;
	
	@EJB
	User_GroupDataAccessInterface user_groupDAO;
	
	@Resource
	SessionContext sessionContext;
	
	
	private Logger logger = Logger.getLogger(UserManagerServiceImpl.class);
		
	@Override
	public void createUser(User user) {
		logger.debug("userDAO.createUserDAO(user): " + user.toString());
		userDAO.createUserDAO(user);
		User_Group newUserGroup = new User_Group(user.getUsername(), "user");
		user_groupDAO.createUser_GroupDAO(newUserGroup);
	}
	
	@Override
	public User getUserById(int id) {
		return userDAO.getUserDAOById(id);
	}
		
	@Override
	public void updateUser(User user){
		userDAO.updateUserDAO(user);
	}
	
	@Override
	public void removeUserById(int id) {
		userDAO.deleteUserByIdDAO(id);		
	}
	
	@Override
	public User getUserByUsername(String username) {
		logger.debug("called UserManagerServiceImpl as getUserByUsername(String " + username + ")");
		return userDAO.getUserDAOByUsername(username);
	}

	@Override
	public void refreshUser(User user) {
		userDAO.refreshUserEMDAO(user);			
	}
	
	@Override
	public void mergeUser(User user) {
		userDAO.mergeUserEMDAO(user);			
	}
	
	@Override
	public void flushUser() {		
		userDAO.flushUserEMDAO();		
	}
	
	@Override
	public void setKnownWord(int userid, int localeid, int wordid) {
		UserKnownWord userKnownWord = new UserKnownWord(userid, localeid, wordid);
		userKnownWordDAO.createUserKnownWordDAO(userKnownWord);		
	}

	@Override
	public void setUnknownWord(int userid, int localeid, int wordid) {
		UserKnownWord userKnownWord = new UserKnownWord(userid, localeid, wordid);
		userKnownWord = userKnownWordDAO.bringUpFromPersistenceEMDAO(userKnownWord);
		logger.info("ABOUT TO BE DELETED: " + userKnownWord.toString() + " by: " + sessionContext.getCallerPrincipal().getName());
		userKnownWordDAO.deleteUserKnownWordByIdDAO(userKnownWord.getId());	
	}

	@Override
	public String retrieveUser(String username) {
		String ret = "failed";
		try{
			logger.info("CALLED retrieveUser(String " + username);
			User user = getUserByUsername(username);
			if(user != null && user.getId() > 1){				
				sendUserRetrievelLink(user.getUsername());
				ret = "sucess";
			}
		}catch(Exception e){
			logger.info("EXCEPTION retrieveUser(String "+ username + ") --> getUserByUsername(: " + e.getMessage());
		}
		return ret;
	}

	private void sendUserRetrievelLink(String username) {
		logger.info("CALLED sendUserRetrievelLink(String " + username);		
		//TODO send email out.
		logger.info("CALLED (Link Sent) sendUserRetrievelLink(String " + username);		
	}
	
}

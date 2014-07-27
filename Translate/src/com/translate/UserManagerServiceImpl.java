package com.translate;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.translate.dataaccess.UserDataAccessInterface;
import com.translate.domain.User;

@Stateless
public class UserManagerServiceImpl implements UserManagerServiceLocal, UserManagerServiceRemote {
	
	@EJB
	UserDataAccessInterface userDAO;
	
	private Logger logger = Logger.getLogger(UserManagerServiceImpl.class);
		
	@Override
	public void createUser(User user) {
		logger.debug("userDAO.createUserDAO(user): " + user.toString());
		userDAO.createUserDAO(user);
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

}

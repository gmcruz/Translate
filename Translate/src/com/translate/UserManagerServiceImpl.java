package com.translate;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.translate.dataaccess.UserDataAccessInterface;
import com.translate.dataaccess.User_GroupDataAccessInterface;
import com.translate.domain.User;
import com.translate.domain.User_Group;

@Stateless
public class UserManagerServiceImpl implements UserManagerServiceLocal, UserManagerServiceRemote {
	
	@EJB
	UserDataAccessInterface userDAO;

	@EJB
	User_GroupDataAccessInterface user_groupDAO;
	
	
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

}

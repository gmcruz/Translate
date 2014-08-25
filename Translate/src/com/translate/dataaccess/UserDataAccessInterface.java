package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Local;

import com.translate.domain.User;

@Local
public interface UserDataAccessInterface {
	public List<User> getAllUsersDAO();
	public void createUserDAO(User user);
	public User getUserDAOById(int id);
	public void updateUserDAO(User user);
	public void deleteUserByIdDAO(int id);
	public User getUserDAOByUsername(String username);
	void refreshUserEMDAO(User user);	
	void mergeUserEMDAO(User user);	
	void flushUserEMDAO();
}

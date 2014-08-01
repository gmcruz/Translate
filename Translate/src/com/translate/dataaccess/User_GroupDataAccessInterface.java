package com.translate.dataaccess;

import java.util.List;

import com.translate.domain.User_Group;

public interface User_GroupDataAccessInterface {

	public abstract List<User_Group> getAllUser_GroupsDAO();

	public abstract void createUser_GroupDAO(User_Group user_group);

	public abstract User_Group getUser_GroupDAOById(int id);

	public abstract void deleteUser_GroupByIdDAO(int id);

	public abstract List<User_Group> getUser_GroupDAOByUsername(String username);

	public abstract void refreshUser_GroupEMDAO(User_Group user_group);

	public abstract void mergeUser_GroupEMDAO(User_Group user_group);

	public abstract void flushUser_GroupEMDAO();

}
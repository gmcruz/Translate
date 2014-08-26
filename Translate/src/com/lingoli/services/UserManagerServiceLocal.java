package com.lingoli.services;

import javax.ejb.Local;

import com.lingoli.domain.User;

@Local
public interface UserManagerServiceLocal {
	public abstract void createUser(User user);
	public abstract User getUserById(int id);
	public abstract void updateUser(User user);
	public abstract void removeUserById(int id);
	public abstract User getUserByUsername(String user);
	public abstract void refreshUser(User user);
	public abstract void mergeUser(User user);
	public abstract void flushUser();
	public abstract void setKnownWord(int userid, int localeid, int wordid);
	public abstract void setUnknownWord(int userid, int localeid, int wordid);
	public abstract String retrieveUser(String username);
}
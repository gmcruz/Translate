package com.translate;

import com.translate.domain.User;

public interface UserManagerServiceLocal {
	public abstract void createUser(User user);
	public abstract User getUserById(int id);
	public abstract void updateUser(User user);
	public abstract void removeUserById(int id);
	public abstract User getUserByUsername(String user);
	public abstract void refreshUser(User user);
	public abstract void mergeUser(User user);
	public abstract void flushUser();
}
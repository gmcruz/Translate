package com.translate.dataaccess;

import java.util.List;

import com.translate.domain.UserKnownWord;

public interface UserKnownWordDataAccessInterface {

	public abstract List<UserKnownWord> getAllUsersKnownByLocaleDAO(int userid, int localeid);

	public abstract void createUserKnownWordDAO(UserKnownWord userKnownWord);

	public abstract UserKnownWord getUserKnownWordDAOById(int id);

	public abstract void updateUserKnownWordDAO(UserKnownWord userKnownWord);

	public abstract void deleteUserKnownWordByIdDAO(int id);

	public abstract void refreshUserKnownWordEMDAO(UserKnownWord userKnownWord);

	public abstract void mergeUserKnownWordEMDAO(UserKnownWord userKnownWord);

	public abstract void flushUserKnownWordEMDAO();

	public abstract UserKnownWord bringUpFromPersistenceEMDAO(UserKnownWord userKnownWord);
	
}
package com.lingoli.dataaccess;

import java.util.List;

import javax.ejb.Local;

import com.lingoli.domain.Locale;

@Local
public interface LocaleDataAccess {
	public List<Locale> getAllLocalesDAO();	
	public List<Locale> getAllActiveLocalesDAO();	
	public Locale getLocaleDAOById(int id);
	public void updateLocaleDAO(Locale locale);
}

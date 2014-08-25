package com.translate.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.translate.dataaccess.LocaleDataAccess;
import com.translate.domain.Locale;

@Stateless
public class LocaleManagerServiceImpl implements LocaleManagerServiceLocal {
	
	@EJB
	LocaleDataAccess localeDAO;
	
	@Override
	public List<Locale> getAllLocales() {
		return localeDAO.getAllLocalesDAO();
	}
	
	@Override
	public Locale getLocaleById(int id) {
		return localeDAO.getLocaleDAOById(id);
	}
	
	@Override
	public void updateLocale(Locale locale){
		localeDAO.updateLocaleDAO(locale);
	}

	@Override
	public List<Locale> getAllActiveLocales() {
		return localeDAO.getAllActiveLocalesDAO();
	}

	
}

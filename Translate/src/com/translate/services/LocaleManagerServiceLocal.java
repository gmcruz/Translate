package com.translate.services;

import java.util.List;

import javax.ejb.Local;

import com.translate.domain.Locale;

@Local
public interface LocaleManagerServiceLocal {
	public List<Locale> getAllLocales();	
	public List<Locale> getAllActiveLocales();	
	public Locale getLocaleById(int id);
	public void updateLocale(Locale locale);
}

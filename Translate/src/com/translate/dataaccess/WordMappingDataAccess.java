package com.translate.dataaccess;

import javax.ejb.Local;

import com.translate.domain.WordMapping;

@Local
public interface WordMappingDataAccess {
	
	public WordMapping getSingleWordMapping(String word, String fromLang, String toLang, String ds);
	
}

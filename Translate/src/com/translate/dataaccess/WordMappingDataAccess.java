package com.translate.dataaccess;

import javax.ejb.Local;

import com.translate.domain.wordmappings_de_DE_en_US;

@Local
public interface WordMappingDataAccess {
	
	public wordmappings_de_DE_en_US getSingleWordMapping(String word, String fromLang, String toLang, String ds);
	
}

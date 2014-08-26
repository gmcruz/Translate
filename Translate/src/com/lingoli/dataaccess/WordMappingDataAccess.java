package com.lingoli.dataaccess;

import javax.ejb.Local;

import com.lingoli.domain.Word;

@Local
public interface WordMappingDataAccess {
	
	public Word getSingleWordMapping(String word, int fromLang, int toLang);
	public void createWordMappingDAO(Word word, Word toWord);
	
}

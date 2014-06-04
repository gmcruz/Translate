package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Local;

import com.translate.domain.Word;

@Local
public interface WordDataAccess {
	public List<Word> getAllWordsDAO();
	public void createWordDAO(Word word);
	public Word getWordDAOById(int id);
	public void updateWordDAO(Word word);
	public void deleteWordByIdDAO(int id);	
}

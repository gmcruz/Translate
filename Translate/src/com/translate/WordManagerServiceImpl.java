package com.translate;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.translate.dataaccess.WordDataAccess;
import com.translate.domain.Word;

@Stateless
public class WordManagerServiceImpl implements WordManagerServiceLocal {
	
	@EJB
	WordDataAccess wordDAO;
	
	
	@Override
	public void createWord(Word word) {
		wordDAO.createWordDAO(word);
	}

	@Override
	public Word getWordById(int id) {
		return wordDAO.getWordDAOById(id);
	}
	
	@Override
	public void updateWord(Word word){
		wordDAO.updateWordDAO(word);
	}

	@Override
	public void removeWordById(int id) {
		wordDAO.deleteWordByIdDAO(id);		
	}

}

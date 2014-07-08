package com.translate;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.translate.dataaccess.WordDataAccess;
import com.translate.dataaccess.WordMappingDataAccessImpl;
import com.translate.domain.Word;

@Stateless
public class WordManagerServiceImpl implements WordManagerServiceLocal {
	
	@EJB
	WordDataAccess wordDAO;
	
	private Logger logger = Logger.getLogger(WordManagerServiceImpl.class);
	
	@Override
	public void createWord(Word word) {
		//1) Find out if the origin word exists if not create.
		//2) Check if translation already exists in toLang and make the mapping, if not create and map.
		logger.debug("wordDAO.createWordDAO(word): " + word.toString());
		//wordDAO.createWordDAO(word);
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

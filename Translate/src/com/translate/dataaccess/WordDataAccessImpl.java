package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.translate.domain.Word;

@Stateless
public class WordDataAccessImpl implements WordDataAccess {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Word> getAllWordsDAO(){
		Query q = em.createQuery("SELECT b FROM Word b");
		@SuppressWarnings("unchecked")
		List<Word> tempList = q.getResultList();
		return tempList;		
	}

	@Override
	public void createWordDAO(Word word) {
		em.persist(word);		
	}	
	
	@Override
	public Word getWordDAOById(int id) {
		Word word = em.find(Word.class, id);
		return word;		
	}
			
	@Override
	public void updateWordDAO(Word word) {
		//TODO id should BE a valid record already existing.
		em.persist(word);		
	}
	
	@Override
	public void deleteWordByIdDAO(int id) {
		Word word = em.find(Word.class, id);
		em.remove(word);		
	}
		
	@Override
	public Word getWordDAOByString(String word) {
		Word wo = new Word();;
		Query qw = em.createQuery("SELECT w FROM Word w WHERE w.word = :word");	
		qw.setParameter("word", word);
		if(qw.getResultList().size() > 0){			
			wo = (Word) qw.getResultList().get(0);
		}
		
		return wo;
	}
	
}


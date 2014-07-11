package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.translate.domain.Word;

@Stateless
public class WordDataAccessImpl implements WordDataAccess {

	private Logger logger = Logger.getLogger(WordDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	//TODO need to get this at the method level so each getWordDAOByString call will produce a new word objectr for the request.
	//@Inject
	//Word wordByString;
	
	@Override
	public List<Word> getAllWordsDAO(){
		Query q = em.createQuery("SELECT b FROM Word b");
		@SuppressWarnings("unchecked")
		List<Word> tempList = q.getResultList();
		return tempList;		
	}

	/*the EntityManager.refresh() does the reverse. It will update the entity object with values taken 
	from the database. Any new values that are set to the entity objects will be lost as a result of 
	this method call. */
	@Override
	public void refreshWordEMDAO(Word word) {
		em.refresh(word);
	}

	@Override
	public void mergeWordEMDAO(Word word) {
		em.merge(word);
	}
	
	/*The EntityManager.flush() will synchronize all the changes that are made to the persistent entities 
	back to the underlying database, */
	@Override
	public void flushWordEMDAO() {
		em.flush();
	}
	
	@Override
	public void createWordDAO(Word word) {
		em.persist(word);
		flushWordEMDAO();//TODO should this be refreshWordDAO(Word word)?
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
	public Word getWordDAOByString(String word, int localeid) {		
		Word wordByString = new Word();
		logger.debug("CALLED BEFORE getWordDAOByString(String: " + wordByString.toString());
		Query qw = em.createQuery("SELECT w FROM Word w WHERE w.word = :word AND w.localeid = :localeid");	
		qw.setParameter("word", word);
		qw.setParameter("localeid", localeid);
		if(qw.getResultList().size() > 0){	
			logger.debug("getWordDAOByString(String " + word + ") " + qw.getResultList().get(0).toString());
			wordByString = (Word) qw.getResultList().get(0);
		}
		logger.debug("CALLED AFTER getWordDAOByString(String: " + wordByString.toString());
		return wordByString;
	}
	
}


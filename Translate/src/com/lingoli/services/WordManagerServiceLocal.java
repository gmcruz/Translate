package com.lingoli.services;

import javax.ejb.Local;

import com.lingoli.domain.Word;

@Local
public interface WordManagerServiceLocal {
	public void createWord(Word word);
	public Word getWordById(int id);
	public void updateWord(Word word);
	public void removeWordById(int id);	
	public Word getWordByString(String word, int localeid);
	void refreshWord(Word word);	
	void mergeWord(Word word);	
	void flushWord();
}

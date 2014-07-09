package com.translate;

import javax.ejb.Local;

import com.translate.domain.Word;

@Local
public interface WordManagerServiceLocal {
	public void createWord(Word word);
	public Word getWordById(int id);
	public void updateWord(Word word);
	public void removeWordById(int id);	
	public Word getWordByString(String word);
}

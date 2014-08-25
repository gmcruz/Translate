package com.translate.services;

import java.util.List;

import javax.ejb.Local;

import com.translate.domain.Translation;

@Local
public interface TranslationManagerServiceLocal {
	
	public List<Translation> getAllTranslations();

	public void createTranslation(Translation translation);

	public void updateTranslation(Translation translation) throws Exception;

	public Translation getTranslationById(int id);
	
	
}

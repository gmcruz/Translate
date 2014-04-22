package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Local;

import com.translate.domain.Translation;

@Local
public interface TranslationDataAccess {
	
	public List<Translation> getAllTranslationsDAO();

	public void createTranslationDAO(Translation translation);

	public void updateTranslationDAO(Translation translation) throws Exception;

	public Translation getTranslationById(int id);
	
}

package com.lingoli.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.lingoli.dataaccess.TranslationDataAccess;
import com.lingoli.domain.Translation;

@Stateless
public class TranslationManagerServiceImpl implements TranslationManagerServiceLocal, TranslationManagerServiceRemote {

	private Logger logger = Logger.getLogger(TranslationManagerServiceImpl.class);
	
	@EJB
	public TranslationDataAccess translationDA;
	
	@Override
	public List<Translation> getAllTranslations() {	
		return translationDA.getAllTranslationsDAO();
	}

	@Override
	public void createTranslation(Translation translation) {
		translationDA.createTranslationDAO(translation);		
	}

	@Override
	public void updateTranslation(Translation translation) throws Exception {		
		translationDA.updateTranslationDAO(translation);		
	}

	@Override
	public Translation getTranslationById(int id){		
		return translationDA.getTranslationById(id);
	}

}

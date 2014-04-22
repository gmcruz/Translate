package com.translate;

import java.util.List;

import javax.ejb.Remote;

import com.translate.domain.Translation;

@Remote
public interface TranslationManagerServiceRemote {
	public List<Translation> getAllTranslations();	
}

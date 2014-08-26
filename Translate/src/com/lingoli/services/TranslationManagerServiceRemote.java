package com.lingoli.services;

import java.util.List;

import javax.ejb.Remote;

import com.lingoli.domain.Translation;

@Remote
public interface TranslationManagerServiceRemote {
	public List<Translation> getAllTranslations();	
}

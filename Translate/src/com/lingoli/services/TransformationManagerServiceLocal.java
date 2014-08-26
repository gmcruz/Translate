package com.lingoli.services;

import javax.ejb.Local;

@Local
public interface TransformationManagerServiceLocal {
		
	public String processTransformation(String testToProcess, int fromLang, int toLang);
	
}

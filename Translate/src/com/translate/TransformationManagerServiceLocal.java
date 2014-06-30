package com.translate;

import javax.ejb.Local;

@Local
public interface TransformationManagerServiceLocal {
		
	public String processTransformation(String testToProcess, String fromLang, String toLang);
	
}

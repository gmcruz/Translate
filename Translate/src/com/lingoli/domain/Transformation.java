package com.lingoli.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Transformation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	int fromLanguageId = 0;
	int toLanguageId = 0;
	String transformationText = ""; 
	
	public Transformation() {}
	
	public Transformation(int fromLanguageId, int toLanguageId, String transformationText){
		super();		
		this.fromLanguageId = fromLanguageId; 
		this.toLanguageId = toLanguageId; 
		this.transformationText = transformationText;
	}

	public int getFromLanguageId() {
		return fromLanguageId;
	}

	public void setFromLanguageId(int fromLanguageId) {
		this.fromLanguageId = fromLanguageId;
	}	
	
	public int getToLanguageId() {
		return toLanguageId;
	}

	public void setToLanguageId(int toLanguageId) {
		this.toLanguageId = toLanguageId;
	}	
	
	public String getTransformationText() {
		return transformationText;
	}

	public void setTransformationText(String transformationText) {
		this.transformationText = transformationText;
	}
	
	public String toString(){			
		return "fromLanguageId: " + this.fromLanguageId + ", toLanguageId: " + this.toLanguageId + ", transformationText: " + this.transformationText;
	}
	
}

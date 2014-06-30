package com.translate.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Transformation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String fromLanguageId = "";
	String toLanguageId = "";
	String transformationText = ""; 
	
	public Transformation() {}
	
	public Transformation(String fromLanguageId, String toLanguageId, String transformationText){
		super();		
		this.fromLanguageId = fromLanguageId; 
		this.toLanguageId = toLanguageId; 
		this.transformationText = transformationText;
	}

	public String getFromLanguageId() {
		return fromLanguageId;
	}

	public void setFromLanguageId(String fromLanguageId) {
		this.fromLanguageId = fromLanguageId;
	}	
	
	public String getToLanguageId() {
		return toLanguageId;
	}

	public void setToLanguageId(String toLanguageId) {
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

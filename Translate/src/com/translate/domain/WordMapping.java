package com.translate.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class WordMapping implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String fromLanguageId = "";
	String toLanguageId = "";
	String wordMappingText = ""; 
	/*id
	synonyms
	antonyms
	definition
	uses*/
	
	public WordMapping() {}
	
	public WordMapping(String fromLanguageId, String toLanguageId, String wordMappingText){
		super();		
		this.fromLanguageId = fromLanguageId; 
		this.toLanguageId = toLanguageId; 
		this.wordMappingText = wordMappingText;
	}

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public String getWordMappingText() {
		return wordMappingText;
	}

	public void setwordMappingText(String wordMappingText) {
		this.wordMappingText = wordMappingText;
	}
	
	public String toString(){			
		return "fromLanguageId: " + this.fromLanguageId + ", toLanguageId: " + this.toLanguageId + ", wordMappingText: " + this.wordMappingText;
	}
	
}

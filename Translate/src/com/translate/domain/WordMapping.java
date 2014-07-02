package com.translate.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="wordmappings_de_DE_en_US")
public class WordMapping implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String originLangId = "ko_KO";
	String toLangId = "en_UK";
	@Transient String wordMappingText = "main"; 
	@Transient String wordMappingTranslation = "henry"; 
	@Transient String synonyms = "all,synonyms,go,here"; 
	@Transient String antonyms = "all,antonyms,here"; 
	@Transient String definition = "Henry winkler the Fonz"; 
	@Transient String uses = "The Fonz is cool, henry Winkler played the Fonz"; 

	public WordMapping() {}
	
	public WordMapping(String originLangId, String toLangId, String wordMappingText){
		super();		
		this.originLangId = originLangId; 
		this.toLangId = toLangId; 
		this.wordMappingText = wordMappingText;
	}

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getOriginLangId() {
		return originLangId;
	}

	public void setOriginLangId(String originLangId) {
		this.originLangId = originLangId;
	}	
	
	public String getToLangId() {
		return toLangId;
	}

	public void setToLangId(String toLangId) {
		this.toLangId = toLangId;
	}	
	
	public String getWordMappingText() {
		return wordMappingText;
	}

	public void setWordMappingText(String wordMappingText) {
		this.wordMappingText = wordMappingText;
	}
	
	public String getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}

	public String getAntonyms() {
		return antonyms;
	}

	public void setAntonyms(String antonyms) {
		this.antonyms = antonyms;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}

	public String getWordMappingTranslation() {
		return wordMappingTranslation;
	}

	public void setWordMappingTranslation(String wordMappingTranslation) {
		this.wordMappingTranslation = wordMappingTranslation;
	}	
	
	public String toString(){			
		return "originLangId: " + this.originLangId + ", toLangId: " + this.toLangId + ", wordMappingText: " + this.wordMappingText;
	}
	
}

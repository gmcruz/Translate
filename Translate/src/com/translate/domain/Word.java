package com.translate.domain;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@RequestScoped
@XmlRootElement
@Entity
@Table(name="word")
public class Word implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	int localeid;
	String word;
	@Transient String wordMappingTranslation = "N_A"; 
	@Transient String synonyms = "all,synonyms,go,here"; 
	@Transient String antonyms = "all,antonyms,here"; 
	@Transient String definition = "Henry winkler the Fonz"; 
	@Transient String uses = "The Fonz is cool, henry Winkler played the Fonz";
	@Transient int mapToId;
	
	//for JPA
	public Word(){}

	public Word(int localeid, String word){
		super();
		this.localeid = localeid;
		this.word = word;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLocaleid() {
		return localeid;
	}

	public void setLocaleid(int localeid) {
		this.localeid = localeid;
	}

	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}

	public String getWordMappingTranslation() {
		return wordMappingTranslation;
	}

	public void setWordMappingTranslation(String wordMappingTranslation) {
		this.wordMappingTranslation = wordMappingTranslation;
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
		
	public int getMapToId() {
		return mapToId;
	}

	public void setMapToId(int mapToId) {
		this.mapToId = mapToId;
	}	
	
	public String toString(){
		return "Word Object - id:" + this.id + " word:" + this.word + " localeid:" + this.localeid + " wordMappingTranslation:" + this.wordMappingTranslation + " synonyms:" + this.synonyms + " antonyms:" + this.antonyms + " definition:" + this.definition + " uses:" + this.uses + " mapToId:" + this.mapToId;
	}
	
}



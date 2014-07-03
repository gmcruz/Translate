package com.translate.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class wordmappings_de_DE_en_US implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String originLangId = "ko_KO";
	String toLangId = "en_UK";
	public wordmappings_de_DE_en_US() {}
	
	public wordmappings_de_DE_en_US(String originLangId, String toLangId, String wordMappingText){
		super();		
		this.originLangId = originLangId; 
		this.toLangId = toLangId;
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
	
	public String toString(){			
		return "originLangId: " + this.originLangId + ", toLangId: " + this.toLangId + ", wordMappingText: ";
	}
	
}

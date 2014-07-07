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
	int wordid;
	int towordid;
 
	public WordMapping() {}
	
	public WordMapping(int wordid, int towordid){
		super();		
		this.wordid = wordid; 
		this.towordid = towordid; 		
	}

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getOriginLangId() {
		return wordid;
	}

	public void setOriginLangId(int wordid) {
		this.wordid = wordid;
	}	
	
	public int getToLangId() {
		return towordid;
	}

	public void setToLangId(int towordid) {
		this.towordid = towordid;
	}	
	
	

	public String toString(){			
		return "wordid: " + this.wordid + ", towordid: " + this.towordid;
	}
	
}

package com.lingoli.domain;

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

	public int getWordid() {
		return wordid;
	}

	public void setWordid(int wordid) {
		this.wordid = wordid;
	}

	public int getTowordid() {
		return towordid;
	}

	public void setTowordid(int towordid) {
		this.towordid = towordid;
	}

	public String toString(){			
		return "wordid: " + this.wordid + ", towordid: " + this.towordid;
	}
	
}

package com.lingoli.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Locale implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;	
	String language;	
	String country;
	String localeid;
	int active;

	//for JPA
	public Locale(){}
	
	public Locale(String language, String country, String localeid, int active){
		super();
		this.language = language;
		this.country = country;
		this.localeid = localeid;
		this.active = active;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
			
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getLocaleId() {
		return localeid;
	}
	
	public void setLocaleId(String localeid) {
		this.localeid = localeid;
	}

	public int getActive() {
		return active;
	}
	
	public void setActive(int active) {
		this.active = active;
	}	
	
	public String toString(){
		return "Locale Object - id: " + this.id + " language:" + this.language + " country:" + this.country;
	}
	
}

 
  
package com.translate.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Language implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int Id;
	String language;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateCreated;
	
	//for JPA to use
	public Language(){}
	
	public Language(String language, Date dateCreated){
		super();
		this.language = language;
		this.dateCreated = dateCreated;
	}
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}

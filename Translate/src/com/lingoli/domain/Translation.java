package com.lingoli.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Translation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//for JPA
	@Id
	@Column(name="id") 
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;	
	int baseId = 0;	
	int languageId = 0;
	int starRating = 5;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	Date lastUpdated;
	String translationText = "oh the humanity"; //because text can be a keyword.
	
	@Transient
	List<String> paramsSetToUpdate = new ArrayList<String>();

	
	//for JPA to use
	public Translation(){}

	public Translation(int baseId, int languageId, int starRating, Date dateCreated, Date lastUpdated, String translationText){
		super();
		this.baseId = baseId; 
		this.languageId = languageId; 
		this.starRating = starRating; 
		this.dateCreated = dateCreated; 
		this.lastUpdated = lastUpdated; 
		this.translationText = translationText;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		paramsSetToUpdate.add("Id"); 
	}		
	
	public int getBaseId() {
		return baseId;
	}

	public void setBaseId(int baseId) {
		this.baseId = baseId;
		paramsSetToUpdate.add("BaseId"); 
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
		paramsSetToUpdate.add("LanguageId"); 
	}	
	
	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
		paramsSetToUpdate.add("StarRating"); 
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
		paramsSetToUpdate.add("DateCreated"); 
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {		
		this.lastUpdated = lastUpdated;
		paramsSetToUpdate.add("LastUpdated"); 
	}

	public String getTranslationText() {
		return translationText;
	}

	public void setTranslationText(String translationText) {
		this.translationText = translationText;
		paramsSetToUpdate.add("TranslationText"); 
	}

	//there has got to be a better way.
	public List<String> getParamsSetToUpdate() {
		return paramsSetToUpdate;
	}

	public void setParamsSetToUpdate(List<String> paramsSetToUpdate) {
		this.paramsSetToUpdate = paramsSetToUpdate;
	}
	
	public String toString(){			
		return "id:"+ this.id + ", " + this.baseId + ", " + this.languageId + ", " + this.starRating + ", " + this.translationText + ", " + this.dateCreated + ", " + this.lastUpdated;
	}
	
}

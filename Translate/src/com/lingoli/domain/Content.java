package com.lingoli.domain;

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
public class Content implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int Id;
	String title;
	int contentTypeId;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateCreated;
	
	public Content(){}
	
	public Content(String title, int contentTypeId, Date dateCreated){
		super();
		this.title = title;
		this.contentTypeId = contentTypeId;
		this.dateCreated = dateCreated;
	}	
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getContentTypeId() {
		return contentTypeId;
	}
	
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
		
}

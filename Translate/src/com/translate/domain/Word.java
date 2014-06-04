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
public class Word implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	int contentId;
	String word;
	int languageId;
	@Temporal(TemporalType.TIMESTAMP)
	Date timeMiliStart;
	@Temporal(TemporalType.TIMESTAMP)
	Date timeMiliEnd;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateCreated;

	//for JPA
	public Word(){}
	
	public Word(int contentId, String word, int languageId, Date timeMiliStart, Date timeMiliEnd, Date dateCreated){
		super();
		this.contentId = contentId;
		this.languageId = languageId;
		this.timeMiliStart = timeMiliStart;
		this.timeMiliEnd = timeMiliEnd;
		this.dateCreated = dateCreated;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getContentId() {
		return contentId;
	}
	
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public int getLanguageId() {
		return languageId;
	}
	
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	
	public Date getTimeMiliStart() {
		return timeMiliStart;
	}
	
	public void setTimeMiliStart(Date timeMiliStart) {
		this.timeMiliStart = timeMiliStart;
	}
	
	public Date getTimeMiliEnd() {
		return timeMiliEnd;
	}
	
	public void setTimeMiliEnd(Date timeMiliEnd) {
		this.timeMiliEnd = timeMiliEnd;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public String toString(){
		return "Word Object - id: " + this.id + " word:" + this.word + " contentId:" + this.contentId + " languageId:" + this.languageId + " timeMiliStart:" + this.timeMiliStart + " timeMiliEnd:" + this.timeMiliEnd + " dateCreated:" + this.dateCreated;
	}
	
}




/*
 CREATE TABLE `word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contentId` int(11) NOT NULL,
  `word` varchar(1024) NOT NULL,
  `languageId` int(11) NOT NULL,
  `timeMiliStart` datetime(3) DEFAULT NULL,
  `timeMiliEnd` datetime(3) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
 
 * */

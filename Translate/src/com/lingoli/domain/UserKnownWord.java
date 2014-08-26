package com.lingoli.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="userknownword")
public class UserKnownWord implements Serializable {

	public static final long serialVersionUID = 1L;
			
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;	
	int userid;
	int localeid;
	int wordid;	
	

	//for JPA
	public UserKnownWord(){}
	
	public UserKnownWord(int id, int userid, int localeid, int wordid){
		super();
		this.id = id;
		this.userid = userid;
		this.localeid = localeid;
		this.wordid = wordid;		
	}	

	public UserKnownWord(int userid, int localeid, int wordid){
		super();		
		this.userid = userid;
		this.localeid = localeid;
		this.wordid = wordid;		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getLocaleid() {
		return localeid;
	}

	public void setLocaleid(int localeid) {
		this.localeid = localeid;
	}

	public int getWordid() {
		return wordid;
	}

	public void setWordid(int wordid) {
		this.wordid = wordid;
	}

	public String toString(){
		String str = "User Known Word Obj - id: " + getId() + " " + " userid: " + getUserid() + " " + " localeid: " + getLocaleid() + " " + " wordid: " + getWordid();
		return str;  
	}
	
	
	public static void main(String[] args){		
		UserKnownWord ukw = new UserKnownWord(234, 111, 222, 333);
		System.out.println(ukw.toString());
	}
	
}

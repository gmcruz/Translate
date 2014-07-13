package com.translate.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="user")
public class User implements Serializable {

	public static final long serialVersionUID = 1L;
			
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String username = "";
	@Transient
	String password = "";
	@OneToOne(fetch=FetchType.LAZY)
	Person person = null;

	//for JPA
	public User(){}
	
	public User(int id, String username, String password, Person p){
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.person = p;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		//TODO user logged in only gets the correct word
		Pattern p = Pattern.compile("[a-zA-Z0-9]");
		Matcher m = p.matcher(this.password);
		String hiddenText = m.replaceAll("*")+"*****";
		return hiddenText;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	public String toString(){
		String str = "User Obj - id: " + getId() + " " + " username: " + getUsername() + " " + " password: " + getPassword() + " (Person param) getPerson().toString()";
		return str;  
	}
	
	
	public static void main(String[] args){
		Date dob = new Date();
		Person p = new Person(555, "Guillermo", "Cruz", "Themex", dob);
		User u = new User(234, "gcruz", "passw", p);
		System.out.println(u.toString());
	}
	
}

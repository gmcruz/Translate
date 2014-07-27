package com.translate.domain;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	//@Transient
	String password = "";
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
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

	public User(String username, String password, Person p){
		super();		
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
		return this.password;
	}

	public void setPassword(String password) {
		String SHA256_password = null;
		
		if(password.length() < 10){
		
			try {
				
		        MessageDigest md2 = MessageDigest.getInstance("SHA-256");
		        md2.update(password.getBytes()); 
		        byte byteData[] = md2.digest();	 
		        
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < byteData.length; i++) {
		         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		        }
		 
		        SHA256_password = sb.toString();	 
		        
			} catch (Exception e) {			
				e.printStackTrace();
			}		
			
			this.password = SHA256_password;
		
		}
		else{
			this.password = password;
		}
		
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	public String toString(){
		String str = "User Obj - id: " + getId() + " " + " username: " + getUsername() + " " + " password: " + getPassword() + " " + getPerson().toString();
		return str;  
	}
	
	
	public static void main(String[] args){
		Date dob = new Date();
		Person p = new Person(555, "Guillermo", "Cruz", "Themex", dob);
		User u = new User(234, "gcruz", "passw", p);
		u.setPassword("abcdefghi");
		System.out.println(u.toString());
	}
	
}

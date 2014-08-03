package com.translate.domain;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
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
	@Size(min=8)
	String password = "";
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
	Person person = null;

	//for JPA
	public User(){}
	
	public User(int id, String username, String password, Person p){
		super();
		this.id = id;
		//you must set password thru this setter it hashes.
		this.setPassword(password);
		this.username = username;
		this.person = p;
	}	

	public User(String username, String password, Person p){
		super();		
		//you must set password thru this setter it hashes.
		this.setPassword(password);
		this.username = username;
		this.person = p;
	}

	
	public User(String fname, String lname, String username, String password, String reenterpassword){		
		super();	
		
		//This is not the first line of defense but we must make sure.
		if(!password.equals(reenterpassword)){
			throw new IllegalArgumentException("Password and re-enter password do not match.");
		}		
		
		//you must set password thru this setter it hashes.
		this.setPassword(password);
		this.username = username;
		
		this.person = new Person();
		this.person.setFname(fname);
		this.person.setLname(lname);			
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
		
		if(password.length() < 13){
		
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

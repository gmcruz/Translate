package com.translate.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="user_group")
public class User_Group implements Serializable {

	public static final long serialVersionUID = 1L;
			
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String username = "";	
	String groupname = "";

	//for JPA
	public User_Group(){}
	
	public User_Group(int id, String username, String groupname){
		super();
		this.id = id;
		this.groupname = groupname;
		this.username = username;
	}	

	public User_Group(String username, String groupname){
		super();
		this.groupname = groupname;
		this.username = username;
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

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	public String toString(){
		String str = "User_Group Obj - id: " + getId() + " " + " username: " + getUsername() + " " + " groupname: " + getGroupname();
		return str;  
	}
	
	
	public static void main(String[] args){
		User_Group u = new User_Group(234, "gcruz", "admin");
		u.setGroupname("abcdefghi");
		System.out.println(u.toString());
	}
	
}

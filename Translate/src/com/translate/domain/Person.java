package com.translate.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="person")
public class Person implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String fname;
	String lname;
	String mname;
	@Temporal(TemporalType.TIMESTAMP)
	Date dob;	
	
	//for JPA
	public Person(){}
	
	public Person(int id, String fname, String lname, String mname, Date dob){
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.mname = mname;
		this.dob = dob;
	}		

	public Person(String fname, String lname, String mname, Date dob){
		super();
		this.fname = fname;
		this.lname = lname;
		this.mname = mname;
		this.dob = dob;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String toString(){
		String str = "Person Obj - id: " + getId() + " fname: " + getFname() + " lname: " + getLname() + " mname: " + getMname() + " dob: " + getDob();
		return str;		
	}	
	
	public static void main (String[] args){
		Date dob = new Date();
		Person p = new Person(555, "Guillermo", "Cruz", "Themex", dob);
		System.out.println(p.toString());
	}
	
}

package com.econsult.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEDICAL_INFORMATION")
public class MedicalInfo extends AbstractAuditableEntity implements Serializable{
	
	@Id
	@OneToOne
	@JoinColumn(name = "USER_ID")
	Patient patient;
	
	@Column(name = "GENDER")
	String gender;
	
	@Column(name = "WEIGHT")
	short weight;
	
	@Column(name = "ALLERGIES")
	String allergies;
	
	@Column(name = "DOB")
	Date dob;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public short getWeight() {
		return weight;
	}

	public void setWeight(short weight) {
		this.weight = weight;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
}

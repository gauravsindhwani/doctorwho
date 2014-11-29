package com.econsult.model;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.econsult.persistence.LocalDatePersistenceConverter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "MEDICAL_INFORMATION")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="patientId")
public class MedicalInfo extends AbstractAuditableEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	long patientId;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	@JsonIgnore
	Patient patient;
	
	@Column(name = "GENDER")
	String gender;
	
	@Column(name = "WEIGHT")
	short weight;
	
	@Column(name = "ALLERGIES")
	String allergies;
	
	@Column(name = "DOB")
	@Convert(converter = LocalDatePersistenceConverter.class)
	@JsonProperty(value = "dobToDisplay")
	LocalDate dob;

	

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

	
	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	@JsonProperty
	public void setDobToDisplay(String dob) throws ParseException {
		this.dob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	@JsonProperty
	public String getDobToDisplay(){
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(dob);
	}
	
	public long getPatientId(){
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	
}

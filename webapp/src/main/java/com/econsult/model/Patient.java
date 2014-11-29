package com.econsult.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PATIENT")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Patient extends DefaultUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Patient(Long id) {
		super(id);
		setRole(Role.Roles.PATIENT.role);
	}
	
	public Patient() {
		super();
		setRole(Role.Roles.PATIENT.role);
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	Set<Post> posts = new HashSet<>();
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID")
	Set<Query> queries = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	@JsonProperty(value= "accountId")
	Account account;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	MedicalInfo medicalInfo;


	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Query> getQueries() {
		return queries;
	}

	public void setQueries(Set<Query> queries) {
		this.queries = queries;
	}

	@JsonProperty
	public long getAccountId(){
		return account.getId();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public MedicalInfo getMedicalInfo() {
		return medicalInfo;
	}

	public void setMedicalInfo(MedicalInfo medicalInfo) {
		medicalInfo.patient = this;
		this.medicalInfo = medicalInfo;
	}

}

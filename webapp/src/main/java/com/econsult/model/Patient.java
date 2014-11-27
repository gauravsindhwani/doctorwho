package com.econsult.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT")
public class Patient extends DefaultUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Patient(Long id) {
		super(id);
	}
	
	public Patient() {
		super();
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	Set<Post> posts = new HashSet<>();
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID")
	Set<Query> queries = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	Account account;
	
	@OneToOne(mappedBy = "patient")
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
		this.medicalInfo = medicalInfo;
	}

}

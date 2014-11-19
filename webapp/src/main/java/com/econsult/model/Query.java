package com.econsult.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "QUERY")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="queryId")
public class Query implements Audtiable {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long queryId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON_DATE", insertable = false)
	Date createdOnDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON_DATE")
	Date updatedOnDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTOR_ID")
	Doctor doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID")
	Patient patient;

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "QUERY_ID")
	Set<Post> posts = new HashSet<>();

	public Query() {
		super();
	}

	public Query(Long queryId) {
		super();
		this.queryId = queryId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		System.out.println("Setting doctor");
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Long getQueryId() {
		return queryId;
	}

	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

	@Override
	public Date getCreatedOnDate() {
		return createdOnDate;
	}

	@Override
	public Date getUpdatedOnDate() {
		if(updatedOnDate == null){
			return new Date();
		}
		return updatedOnDate;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	public void addPost(Post post){
		posts.add(post);
	}
	
}

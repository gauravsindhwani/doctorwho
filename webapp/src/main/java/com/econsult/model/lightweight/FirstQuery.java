package com.econsult.model.lightweight;

import java.util.Date;

import com.econsult.model.Patient;
import com.econsult.model.Post;
import com.econsult.model.Query;
import com.econsult.model.Role;

public class FirstQuery {
	
	Patient patient;
	String text;
	
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Query buildQuery(){
		Query query = new Query();
		query.setPatient(patient);
		Post post = new Post();
		post.setQuery(query);
		post.setText(text);
		post.setUser(patient);
		post.setUpdatedOnDate(new Date());
		post.setPostBy(Role.Roles.PATIENT.getRole());
		query.addPost(post);
	//	query.setUpdatedOnDate(new Date());
		return query;
	}

}

package com.econsult.model.lightweight;

import com.econsult.model.Patient;
import com.econsult.model.Post;
import com.econsult.model.Query;

public class LWFirstQuery {
	
	long patientId;
	String text;
	
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Query buildQuery(){
		Patient patient = new Patient(patientId);
		Query query = new Query();
		query.setPatient(patient);
		Post post = new Post();
		post.setQuery(query);
		post.setText(text);
		post.setUser(patient);
		query.addPost(post);
		return query;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

}

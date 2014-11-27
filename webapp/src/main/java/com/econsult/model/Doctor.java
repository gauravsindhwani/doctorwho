package com.econsult.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="DOCTOR")
public class Doctor extends DefaultUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Doctor() {
		super();
	}
	
	public Doctor(Long id) {
		super(id);
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	Set<Post> posts = new HashSet<>();
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTOR_ID")
	Set<Query> queries = new HashSet<>();

	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinTable(name="DOCTOR_SPECIALITY",
		joinColumns={@JoinColumn(name="DOCTOR_ID")},
		inverseJoinColumns={@JoinColumn(name="SPECIALITY")})
	Set<Speciality> specialities = new HashSet<>();
	

}

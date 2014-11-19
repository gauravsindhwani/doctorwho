package com.econsult.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="SPECIALITIES")
public class Speciality implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SPECIALITY")
	String specialityName;
	
	@ManyToMany(mappedBy="specialities", fetch=FetchType.LAZY)
	Set<Doctor> doctors = new HashSet<Doctor>();
}

package com.econsult.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ALLERGIES")
public class Allergy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	String allergyName;

	private Allergy() {
		super();
	}
	@ManyToMany(mappedBy="allergies")
	Set<Patient> patients = new HashSet<Patient>();
}

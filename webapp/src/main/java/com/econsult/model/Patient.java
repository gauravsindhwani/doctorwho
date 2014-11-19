package com.econsult.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT")
@AttributeOverrides({
    @AttributeOverride(name="USER_ID", column=@Column(name="PATIENT_ID"))
})
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

	
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinTable(name="PATIENT_ALLERGY",
		joinColumns={@JoinColumn(name="PATIENT_ID")},
		inverseJoinColumns={@JoinColumn(name="ALLERGY")})
	
	Set<Allergy> allergies = new HashSet<>();
	
	public void setPatientId(long id){
		this.id = id;
	}
	

}

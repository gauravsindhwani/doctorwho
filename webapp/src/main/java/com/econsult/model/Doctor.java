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
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="DOCTOR")
@AttributeOverrides({
    @AttributeOverride(name="USER_ID", column=@Column(name="DOCTOR_ID"))
})
@XmlRootElement
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

	

	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinTable(name="DOCTOR_SPECIALITY",
		joinColumns={@JoinColumn(name="DOCTOR_ID")},
		inverseJoinColumns={@JoinColumn(name="SPECIALITY")})
	Set<Speciality> specialities = new HashSet<>();
	

}

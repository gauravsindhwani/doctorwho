package com.econsult.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CONTACT_INFO")
@AttributeOverride(name = "id", column = @Column(name = "CONTACT_ID"))
public class ContactInfo extends AbstractAuditableAutoIncrementingEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="EMAIL")
	private String  email;
	
	@Column(name="PRIMARY_PHONE")
	private String primaryPhone;
	
	@Column(name="SECONDARY_PHONE")
	private String secondaryPhone;
	
	@Column(name = "EMAIL_VERIFIED")
	private boolean emailVerified;
	
	@Column(name = "PHONE_VERIFIED")
	private boolean phoneVerified;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}
	
	@JsonIgnore
	public long getContactId() {
		return id;
	}
	
	@PrePersist
	@PreUpdate
	public void setDefaultUpdatedOnDate(){
		updatedOnDate = new Date();
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean isPhoneVerified() {
		return phoneVerified;
	}

	public void setPhoneVerified(boolean phoneVerified) {
		this.phoneVerified = phoneVerified;
	}
}

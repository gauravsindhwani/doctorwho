package com.econsult.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "CORPORATION")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="corpId")
@AttributeOverride(name = "id", column = @Column(name = "CORP_ID"))
public class Corporation extends AbstractAuditableAutoIncrementingEntity{
	
	public Corporation(long id) {
		super(id);
	}

	public Corporation() {
	
	}
	
	@Column(name = "NAME")
	String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="CONTACT_ID")
	ContactInfo contact;
	
	@Column(name = "DOMAIN")
	String domain;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ADMIN")
	Admin admin;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContactInfo getContact() {
		return contact;
	}

	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Date getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}

	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

	public long getCorpId() {
		return id;
	}

}

package com.econsult.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "CORPORATION")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@AttributeOverride(name = "id", column = @Column(name = "CORP_ID"))
public class Corporation extends AbstractAuditableAutoIncrementingEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Corporation(long id) {
		super(id);
	}
	
	public Corporation(long id, ServicePlan serviceplan) {
		super(id);
		this.servicePlan = serviceplan;
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
	
	@OneToOne
	@JoinColumn(name = "SERVICE_PLAN_ID")
	ServicePlan servicePlan;
	
	@OneToMany(mappedBy = "corporation", fetch = FetchType.LAZY)
	Set<CorpAccount> corpAccounts = new HashSet<CorpAccount>();

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

	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}

	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

    @JsonIgnore
	public long getCorpId() {
		return id;
	}

	public ServicePlan getServicePlan() {
		return servicePlan;
	}

	public void setServicePlan(ServicePlan servicePlan) {
		this.servicePlan = servicePlan;
	}

}

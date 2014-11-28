package com.econsult.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@AttributeOverride(name = "id", column = @Column(name = "ACCOUNT_ID"))
public class Account extends AbstractAuditableAutoIncrementingEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADMIN_ID")
	Patient admin;
	
	@OneToOne
	@JoinColumn(name = "SERVICE_PLAN_ID")
	ServicePlan servicePlan;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_ID")
	Set<Patient> patients = new HashSet<>();
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "ACCOUNT_ID")
	CorpAccount corpAccount;
	
	@Column(name = "VERIFIED", columnDefinition= "default '0'")
	boolean verified;
	
	public Account(long id) {
		super(id);
	}

	public Account() {
	}

	
	public DefaultUser getAdmin() {
		return admin;
	}

	public void setAdmin(Patient admin) {
		this.admin = admin;
		patients.add(admin);
	}

	public ServicePlan getServicePlan() {
		return servicePlan;
	}

	public void setServicePlan(ServicePlan servicePlan) {
		this.servicePlan = servicePlan;
	}

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	public CorpAccount getCorpAccount() {
		return corpAccount;
	}

	public void setCorpAccount(CorpAccount corpAccount) {
		this.corpAccount = corpAccount;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean enabled) {
		this.verified = enabled;
	}
	
	
}

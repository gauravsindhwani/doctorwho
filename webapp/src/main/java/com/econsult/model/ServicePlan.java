package com.econsult.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_PLAN")
@AttributeOverride(name = "id", column = @Column(name = "SERVICE_PLAN_ID"))
public class ServicePlan extends AbstractAuditableAutoIncrementingEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServicePlan(long id) {
		super(id);
	}
	
	public ServicePlan() {
		super();
	}

	@Column(name = "NAME")
	String name;
	
	@Column(name = "DURATION")
	String duration;
	
	@Column(name = "DEPENDENTS")
	short numberOfDependents;
	
	@Column(name = "FEE")
	int fee;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public short getNumberOfDependents() {
		return numberOfDependents;
	}

	public void setNumberOfDependents(short numberOfDependents) {
		this.numberOfDependents = numberOfDependents;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
}

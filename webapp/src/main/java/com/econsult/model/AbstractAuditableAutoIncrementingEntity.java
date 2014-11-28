package com.econsult.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractAuditableAutoIncrementingEntity extends AbstractAutoIncrementingEntity implements Audtiable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbstractAuditableAutoIncrementingEntity(long id) {
		super(id);
	}
	
	public AbstractAuditableAutoIncrementingEntity() {
		super();
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON_DATE")
	Date updatedOnDate;
	
	@PrePersist
	@PreUpdate
	public void updateUpdatedDate(){
		this.updatedOnDate = new Date();
	}
	
	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

	@Override
	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}
}

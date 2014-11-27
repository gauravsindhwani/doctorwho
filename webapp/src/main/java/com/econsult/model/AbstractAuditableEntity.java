package com.econsult.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractAuditableEntity implements Audtiable{
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON_DATE", insertable = false)
	Date createdOnDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON_DATE")
	Date updatedOnDate;
	
	@PrePersist
	@PreUpdate
	public void updateUpdatedDate(){
		this.updatedOnDate = new Date();
	}
	
	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

	@Override
	public Date getCreatedOnDate() {
		return createdOnDate;
	}

	@Override
	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}
}

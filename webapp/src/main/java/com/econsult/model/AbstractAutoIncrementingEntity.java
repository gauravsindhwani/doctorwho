package com.econsult.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractAutoIncrementingEntity {

	public AbstractAutoIncrementingEntity(long id) {
		super();
		this.id = id;
	}

	public AbstractAutoIncrementingEntity() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	long id;

	public long getId() {
		return id;
	}
	
	
}

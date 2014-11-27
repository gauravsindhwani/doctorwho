package com.econsult.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class AbstractDao {

	@PersistenceContext
	protected EntityManager entitymanager;


	public void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}


}

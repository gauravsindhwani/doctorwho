package com.econsult.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractBaseDao {

	EntityManager entitymanager;
	
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
            this.entitymanager = entityManager;
    }
}

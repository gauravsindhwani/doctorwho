package com.econsult.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.jpa.GeneralDAO;
import com.googlecode.genericdao.dao.jpa.GeneralDAOImpl;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;

@Repository
public class EconsultGenericDaoImpl extends GeneralDAOImpl implements GeneralDAO{
	
	@Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
            super.setEntityManager(entityManager);
    }

    @Override
    @Autowired
    public void setSearchProcessor(JPASearchProcessor searchProcessor) {
            super.setSearchProcessor(searchProcessor);
    }
}

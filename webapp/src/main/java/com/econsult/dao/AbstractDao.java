package com.econsult.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractDao {


	protected SessionFactory sessionFactory;

	protected Session session;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		session = sessionFactory.openSession();
	}

	

	protected Session getSession(){
		return sessionFactory.openSession();
	}

}

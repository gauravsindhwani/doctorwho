package com.econsult.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import org.hibernate.Session;

import com.econsult.model.DefaultUser;
import com.econsult.model.Doctor;
import com.econsult.model.Patient;
import com.econsult.model.Post;
import com.econsult.model.Query;
import com.econsult.model.Role;
import com.econsult.model.util.HibernateUtil;

public class FirstTest {
	
	public static void main(String[] args) {
		
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
		//	test1(session);
		//	test2(session);
			test3(session);
			session.getTransaction().commit();
		//	session.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
	}

	private static void test3(Session session) {
		Query q = (Query)session.get(Query.class, 1L);
		Set<Post> posts = q.getPosts();
		for (Post post : posts) {
			System.out.println(post.getText());
		}
		
	}

	private static void test2(Session session) {
		Post post = getPost(1L);
		session.save(post);
	}

	private static Post getPost(long parentID) {
		Post p = new Post(2L);
		p.setQuery(new Query());
		p.setUser(new Doctor(1L));
		p.setCreatedOnDate(new Date());
		p.setUpdatedOnDate(new Date());
		p.setText("I am good");
		p.setPostBy(new Role("doctor"));
		if(parentID > 0){
			p.setParent(new Post(parentID));
		}
		return p;
	}

	private static void test1(Session session) {
		Doctor doc = getDoc();
		Patient patient = getPatient();
		session.save(doc);
		session.save(patient);
		Query q = getQuery(doc, patient);
		session.save(q);		
	}

	private static Query getQuery(Doctor doc, Patient patient) {
		Query q = new Query();
		q.setCreatedOnDate(new Date());
		q.setUpdatedOnDate(new Date());
		q.setDoctor(doc);
		q.setPatient(patient);
		return q;
	}

	private static Patient getPatient() {
		Patient doc = new Patient(2L);
		doc.setFirstName("Pat");
		doc.setLastName("d");
		doc.setPassword("c");
		doc.setCreatedOnDate(new Timestamp(System.currentTimeMillis()));
		doc.setUpdatedOnDate(new Timestamp(System.currentTimeMillis()));
		doc.setRole(new Role("Patient"));
		return doc;
	}

	private static Doctor getDoc() {
		Doctor doc = new Doctor(1L);
		doc.setFirstName("Doc");
		doc.setLastName("d");
		doc.setPassword("c");
		doc.setCreatedOnDate(new Timestamp(System.currentTimeMillis()));
		doc.setUpdatedOnDate(new Timestamp(System.currentTimeMillis()));
		doc.setRole(new Role("Doctor"));
		return doc;
	}

}

package com.econsult.dao;

import org.springframework.stereotype.Repository;

import com.econsult.model.DefaultUser;
import com.econsult.model.Doctor;
import com.econsult.model.Patient;
import com.econsult.model.User;

@Repository
public class UserDao extends AbstractDao {

	/**
	 * @param Id
	 * @return
	 */
	public Doctor getDoctor(long Id){
		return (Doctor)session.get(Doctor.class, Id);
	}

	/**
	 * @param Id
	 * @return
	 */
	public Patient getPatient(long Id){
		return (Patient)session.get(Patient.class, Id);
	}

	/**
	 * @param Id
	 * @return
	 */
	public User getUser(long Id){
		return (User)session.get(DefaultUser.class, Id);
	}

	public long saveDoctor(Doctor doctor){
		session.beginTransaction();
		long id = (Long)session.save(doctor);
		session.getTransaction().commit();
		return id;
	}

	public long savePatient(Patient patient){
		session.beginTransaction();
		long id = (Long)session.save(patient);
		session.getTransaction().commit();
		return id;
	}
}

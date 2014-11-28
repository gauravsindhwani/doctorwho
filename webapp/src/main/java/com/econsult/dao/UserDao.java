package com.econsult.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.econsult.model.DefaultUser;
import com.econsult.model.Doctor;
import com.econsult.model.MedicalInfo;
import com.econsult.model.Patient;
import com.econsult.model.User;

@Repository
@Transactional
public class UserDao extends AbstractDao {

	/**
	 * @param Id
	 * @return
	 */
	public Doctor getDoctor(long Id){
		return entitymanager.find(Doctor.class, Id);
	}

	/**
	 * @param Id
	 * @return
	 */
	public Patient getPatient(long Id){
		return entitymanager.find(Patient.class, Id);
	}

	/**
	 * @param Id
	 * @return
	 */
	public User getUser(long Id){
		return entitymanager.find(DefaultUser.class, Id);
	}

	public Doctor saveDoctor(Doctor doctor){
		entitymanager.persist(doctor);
		return doctor;
	}

	public Patient savePatient(Patient patient){
		entitymanager.persist(patient);
		return patient;
	}

	public MedicalInfo saveMedicalInfo(MedicalInfo medicalInfo){
		entitymanager.persist(medicalInfo);
		return medicalInfo;
	}

	public MedicalInfo updatedMedicalInfo(MedicalInfo medicalInfo){
		entitymanager.merge(medicalInfo);
		return medicalInfo;
	}

	public MedicalInfo getMedicalInfo(long id){
		return entitymanager.find(MedicalInfo.class, id);
	}

}

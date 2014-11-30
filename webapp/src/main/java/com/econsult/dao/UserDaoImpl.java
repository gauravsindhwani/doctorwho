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
public class UserDaoImpl extends AbstractDao implements UserDao {

	/**
	 * @param Id
	 * @return
	 */
	public Doctor getDoctor(long Id){
		return find(Doctor.class, Id);
	}

	/**
	 * @param Id
	 * @return
	 */
	public Patient getPatient(long Id){
		return find(Patient.class, Id);
	}

	/**
	 * @param Id
	 * @return
	 */
	public User getUser(long Id){
		return find(DefaultUser.class, Id);
	}

	public Doctor saveDoctor(Doctor doctor){
		return save(doctor);
	}

	public Patient savePatient(Patient patient){
		return save(patient);
	}
	
	public Patient updatePatient(Patient patient){
		return merge(patient);
	}


	public MedicalInfo saveMedicalInfo(MedicalInfo medicalInfo){
		return save(medicalInfo);
	}

	public MedicalInfo updatedMedicalInfo(MedicalInfo medicalInfo){
		return merge(medicalInfo);
	}

	public MedicalInfo getMedicalInfo(long id){
		return find(MedicalInfo.class, id);
	}

}

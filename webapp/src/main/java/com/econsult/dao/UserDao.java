package com.econsult.dao;

import com.econsult.model.Doctor;
import com.econsult.model.MedicalInfo;
import com.econsult.model.Patient;
import com.econsult.model.User;

public interface UserDao {

	/**
	 * @param Id
	 * @return
	 */
	public abstract Doctor getDoctor(long Id);

	/**
	 * @param Id
	 * @return
	 */
	public abstract Patient getPatient(long Id);

	/**
	 * @param Id
	 * @return
	 */
	public abstract User getUser(long Id);

	public abstract Doctor saveDoctor(Doctor doctor);

	public abstract Patient savePatient(Patient patient);

	public abstract Patient updatePatient(Patient patient);

	public abstract MedicalInfo saveMedicalInfo(MedicalInfo medicalInfo);

	public abstract MedicalInfo updatedMedicalInfo(MedicalInfo medicalInfo);

	public abstract MedicalInfo getMedicalInfo(long id);

}
package com.econsult.dao;

import java.util.Date;

import com.econsult.model.MedicalInfo;
import com.econsult.model.Patient;

public class UserDaoTest extends AbstractDaoTest {

	public void testSaveMedicalInfo(){
		System.out.println(userDao.saveMedicalInfo(getMedicalInfo()));
	}

	private MedicalInfo getMedicalInfo() {
		MedicalInfo info = new MedicalInfo();
		info.setPatient(new Patient(23L));
		info.setAllergies("sulphur");
		info.setDob(new Date());
		info.setWeight((short)80);
		info.setGender("MALE");
		return info;
	}
}

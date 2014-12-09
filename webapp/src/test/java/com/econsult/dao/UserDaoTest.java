package com.econsult.dao;

import java.time.LocalDate;

import com.econsult.model.MedicalInfo;

public class UserDaoTest extends AbstractDaoTest {

	public void testSaveMedicalInfo(){
	//	System.out.println(userDao.saveMedicalInfo(getMedicalInfo()));
	}

	private MedicalInfo getMedicalInfo() {
		MedicalInfo info = new MedicalInfo();
		info.setPatientId(23L);
		info.setAllergies("sulphur");
		info.setDob(LocalDate.now());
		info.setWeight((short)80);
		info.setGender("MALE");
		return info;
	}
}

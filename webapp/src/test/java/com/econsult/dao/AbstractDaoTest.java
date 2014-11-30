package com.econsult.dao;

import java.util.Random;

import javax.persistence.EntityManager;

import com.econsult.model.Admin;
import com.econsult.model.ContactInfo;
import com.econsult.model.DefaultUser;
import com.econsult.model.Patient;
import com.econsult.model.Role;
import com.econsult.util.EntityManagerUtil;

public abstract class AbstractDaoTest {

	AdministrationDaoImpl adminDao = new AdministrationDaoImpl();
	UserDaoImpl userDao = new UserDaoImpl();
	static EntityManager em = EntityManagerUtil.entityManager;
	public AbstractDaoTest(){
		adminDao.entitymanager = em;
		userDao.entitymanager = em;
	}
	
	protected Admin getAdminUser(){
		Admin ad = new Admin();
		ad.setFirstName("aDMIN");
		ad.setPassword("pass");
		ad.setContactInfo(getContactInfo());
		ad.setRole(Role.Roles.ADMIN.getRole());
		return ad;
		
	}
	
	protected Patient getDefaultUser(){
		Patient df = new Patient();
		df.setFirstName("accntAdmin");
		df.setPassword("pass");
		df.setContactInfo(getContactInfo());
		df.setRole(Role.Roles.PATIENT.getRole());
		return df;
		
	}
	
	
	
	protected ContactInfo getContactInfo(String email, String primaryPhone){
		ContactInfo ci = new ContactInfo();
		ci.setEmail(email);
		ci.setPrimaryPhone(primaryPhone);
		return ci;
	}
	
	protected ContactInfo getContactInfo() {
		String email = "con" + new Random().nextInt(1000) + "@econsult.com";
		String primaryPhoneTemp = new String("000000000" + new Random().nextInt(1000000000));
		String primaryPhone = primaryPhoneTemp.substring(primaryPhoneTemp.length() - 10);
		return getContactInfo(email, primaryPhone);
	}
	
	public static void main(String[] args){
		begin("createCorp");
	}

	private static void begin(String testType) {
		em.getTransaction().begin();
		AdministrationDaoTest adminDaoTest = new AdministrationDaoTest();
		UserDaoTest userDaoTest = new UserDaoTest();
		switch (testType) {
		case "createCorp":
			adminDaoTest.testCreateCorp();
			break;
		case "createSp":
			adminDaoTest.testCreateSP();
			break;
		case "createAcct":
			adminDaoTest.testCreateAccount();
			break;
		case "createCorpAcct":
			adminDaoTest.testCreateCorpAccount();
			break;
		case "createMedicalInfo":
			userDaoTest.testSaveMedicalInfo();
			break;
		default:
			break;
		}
		em.getTransaction().commit();
		
	}
}

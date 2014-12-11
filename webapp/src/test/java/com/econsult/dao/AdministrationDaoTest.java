package com.econsult.dao;

import com.econsult.model.Account;
import com.econsult.model.CorpAccount;
import com.econsult.model.Corporation;
import com.econsult.model.ServicePlan;

public class AdministrationDaoTest extends AbstractDaoTest{
	
	



	private Corporation getCorporation() {
		Corporation corp = new Corporation();
		corp.setAdmin(getAdminUser());
		corp.setDomain("newcorp.com");
		corp.setName("ECONSULT");
		corp.setContact(getContactInfo());
		return corp;
	}
	
	
	
	private ServicePlan getSP(){
		ServicePlan sp = new ServicePlan();
		sp.setDuration("HALF-YEARLY");
		sp.setFee(100);
		sp.setName("BASIC");
		sp.setNumberOfDependents((short)2);
		return sp;
	}
	
	
	
	private Account getAccount(){
		Account account = new Account();
		account.setAdmin(getDefaultUser());
		account.setServicePlan(adminDao.getServicePlanByName("BASIC"));
		return account;
	}
	

	
	
	private CorpAccount getCorpAccount(){
		CorpAccount ca = new CorpAccount();
		ca.setCorpMailId("a@econsult.com");
		ca.setCorporation(new Corporation(1L));
		ca.setAccount(new Account(1L));
		return ca;
		}
}

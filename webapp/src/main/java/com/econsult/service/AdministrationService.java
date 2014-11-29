package com.econsult.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.econsult.dao.AdministrationDao;
import com.econsult.model.Account;
import com.econsult.model.ContactInfo;
import com.econsult.model.CorpAccount;
import com.econsult.model.Corporation;
import com.econsult.model.Patient;
import com.econsult.model.ServicePlan;
import com.econsult.model.lightweight.LWFirstUser;
import com.econsult.model.lightweight.LWCorporation;

@Component
@Path("econsult/admin/")
public class AdministrationService {
	
	private final static Logger logger = LoggerFactory.getLogger(AdministrationService.class); 

	@Autowired
	private AdministrationDao adminDao;
	
	@GET
	@Path("/corp/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Corporation getCorporation(@PathParam("Id") long id){
		logger.trace("Getting corporation for ID {}", id);
		return adminDao.getCorporation(id);
	}
	
	
	@GET
	@Path("/account/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccount(@PathParam("Id") long id){
		logger.trace("Getting account for ID {}", id);
		return adminDao.getAccount(id);
	}
	
	@GET
	@Path("/plan/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServicePlan getServicePlan(@PathParam("Id") long id){
		logger.trace("Getting service for ID {}", id);
		return adminDao.getServicePlan(id);
	}
	
	@POST
	@Path("/plan/")
	public long createServicePlan(ServicePlan plan){
		return adminDao.saveServicePlan(plan).getId();
	}
	
	@POST
	@Path("/corp/")
	public long createCorporation(LWCorporation inflighCorp){
		return adminDao.saveCorporation(inflighCorp.buildCorporation()).getCorpId();
	}
	
	@POST
	@Path("/account/")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean registerNewUserFromCorpMail(LWFirstUser user){
		// create a patient
		Patient patient = new Patient();
		ContactInfo contact = new ContactInfo();
		contact.setEmail(user.getCorpEmail());
		patient.setContactInfo(contact);
		// create a new account for this primary patient
		Account account = new Account();
		account.setAdmin(patient);
		
		//set a service plan
		Corporation corporation = adminDao.getCorpByDomain(user.getCorpDomain());
		if(corporation.getServicePlan() != null){
			account.setServicePlan(corporation.getServicePlan());
		}else{
			account.setServicePlan(adminDao.getServicePlanByName("BASIC"));
		}
		
		// create a corpaccount
		CorpAccount corpAccount = new CorpAccount();
		corpAccount.setCorpMailId(user.getCorpEmail());
		corpAccount.setAccount(account);
		corpAccount.setCorporation(corporation);
		account.setCorpAccount(corpAccount);
		adminDao.saveAccount(account);
		return true;
	}
	
	
}

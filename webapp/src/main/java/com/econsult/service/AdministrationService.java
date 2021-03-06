package com.econsult.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.econsult.dao.AdministrationDao;
import com.econsult.model.Account;
import com.econsult.model.ContactInfo;
import com.econsult.model.CorpAccount;
import com.econsult.model.Corporation;
import com.econsult.model.Patient;
import com.econsult.model.ServicePlan;
import com.econsult.model.lightweight.LWCorporation;
import com.econsult.model.lightweight.LWFirstUser;
import com.googlecode.genericdao.dao.jpa.GeneralDAO;

import java.util.List;

@Component
@Path("/admin/")
@Transactional
public class AdministrationService {
	
	private final static Logger logger = LoggerFactory.getLogger(AdministrationService.class); 

	@Autowired
	private AdministrationDao adminDao;
	
	@Autowired
	private GeneralDAO generalDao;
	
	@GET
	@Path("/corp/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Corporation getCorporation(@PathParam("Id") long id){
		logger.trace("Getting corporation for ID {}", id);
		return generalDao.find(Corporation.class, id);
	}

    @DELETE
    @Path("/corp/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCorporation(@PathParam("Id") long id){
        logger.trace("Deleting corporation for ID {}", id);
        return generalDao.remove(generalDao.find(Corporation.class, id));
    }

    @GET
    @Path("/corp/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Corporation> getCorporations(){
        logger.trace("Getting all corporations");
        return generalDao.findAll(Corporation.class);
    }
	
	
	@GET
	@Path("/account/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccount(@PathParam("Id") long id){
		logger.trace("Getting account for ID {}", id);
		return generalDao.find(Account.class, id);
	}

    @GET
    @Path("/plan/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServicePlan> getServicePlans(){
        logger.trace("Getting all service plans");
        return generalDao.findAll(ServicePlan.class);
    }


    @GET
	@Path("/plan/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServicePlan getServicePlan(@PathParam("Id") long id){
		logger.trace("Getting service for ID {}", id);
		return generalDao.find(ServicePlan.class, id);
	}
	
	@GET
	@Path("/plan/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public ServicePlan getServicePlan(@PathParam("name") String name){
		return adminDao.getServicePlanByName(name);
	}
	
	@POST
	@Path("/plan/")
	public long createServicePlan(ServicePlan plan){
		return generalDao.save(plan).getId();
	}
	
	@POST
	@Path("/corp/")
	public long createCorporation(LWCorporation inflighCorp){
		return generalDao.save(inflighCorp.buildCorporation()).getCorpId();
	}

    @POST
    @Path("/corp/{Id}")
    public long updateCorporation(@PathParam("Id") long id, LWCorporation inflighCorp){
        logger.debug("updateCorporation id=" + id);
        Corporation corp = generalDao.find(Corporation.class, id);
        return generalDao.save(inflighCorp.buildCorporation(corp)).getCorpId();
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
		generalDao.save(account);
		return true;
	}
	
	
}

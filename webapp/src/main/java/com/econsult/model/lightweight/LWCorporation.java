package com.econsult.model.lightweight;

import com.econsult.model.ContactInfo;
import com.econsult.model.Corporation;
import com.googlecode.genericdao.dao.jpa.GeneralDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LWCorporation {
    private final static Logger logger = LoggerFactory.getLogger(LWCorporation.class);

    long id = -1;
	String name;
	String email;
	String primaryPhone;
	String domain;

    @Autowired
    private GeneralDAO generalDao;


    public long getId() {return id;}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public Corporation buildCorporation(){
        return buildCorporation(null);
	}

    public Corporation buildCorporation(Corporation corp){
        logger.trace("buildCorporation id= " + id);
        if(corp == null) {
            corp = new Corporation();
        }
        corp.setDomain(domain);
        corp.setName(name);
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail(email);
        contactInfo.setPrimaryPhone(primaryPhone);
        corp.setContact(contactInfo);
        return corp;
    }
	
}

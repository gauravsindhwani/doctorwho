package com.econsult.model.lightweight;

import com.econsult.model.ContactInfo;
import com.econsult.model.Corporation;

public class InFlightCorporation {
	String name;
	String email;
	String primaryPhone;
	String domain;
	
	
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
		Corporation corp = new Corporation();
		corp.setDomain(domain);
		corp.setName(name);
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setEmail(email);
		contactInfo.setPrimaryPhone(primaryPhone);
		corp.setContact(contactInfo);
		return corp;
	}
	
}

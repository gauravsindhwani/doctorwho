package com.econsult.model.lightweight;

public class FirstUser {
	String corpEmail;

	public String getCorpEmail() {
		return corpEmail;
	}

	public void setCorpEmail(String corpEmail) {
		this.corpEmail = corpEmail;
	}
	
	public String getCorpDomain(){
		return corpEmail.substring(corpEmail.indexOf("@") + 1);
	}
}

package com.econsult.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CORP_ACCOUNT")
public class CorpAccount {
	
	@Id
	@Column(name = "CORP_MAIL_ID")
	String corpMailId;
	
	@ManyToOne
	@JoinColumn(name = "CORP_ID")
	Corporation corporation;
	
	@OneToOne
	@JoinColumn(name = "ACCOUNT_ID")
	Account account;

	public String getCorpMailId() {
		return corpMailId;
	}

	public void setCorpMailId(String corpMailId) {
		this.corpMailId = corpMailId;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

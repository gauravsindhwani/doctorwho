package com.econsult.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
public class DefaultUser extends AbstractAuditableAutoIncrementingEntity implements User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="FIRST_NAME")
	String firstName;

	@Column(name="LAST_NAME")
	String lastName;

	@Column(name="PASSWORD")
	String password;

	@Column(name="LOGIN_NAME")
	String loginName;
	
	@Column(name="LOGIN_TYPE")
	String loginType;
	
	@ManyToOne
	@JoinColumn(name = "ROLE_NAME")
	Role role;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CONTACT_ID")
	ContactInfo contactInfo;
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

	public DefaultUser() {
		super();
	}
	
	public DefaultUser(Long id) {
		super();
		this.id = id;
	}

	
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		
		return lastName;
	}

	@Override
	public String getPassword() {
		
		return password;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

}

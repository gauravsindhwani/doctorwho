package com.econsult.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ADMIN")
public class Admin extends DefaultUser {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8981421171733424233L;

	public Admin(){
		setRole(Role.Roles.ADMIN.role);
	}
	
	public Admin(long id){
		super(id);
		setRole(Role.Roles.ADMIN.role);
	}
}

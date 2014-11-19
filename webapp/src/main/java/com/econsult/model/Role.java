package com.econsult.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ROLE")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ROLE_NAME")
	String roleName;
	
	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Role() {
		super();
	}
	
	public static enum Roles{
		DOCTOR("DOCTOR"), PATIENT("PATIENT");
		Role role;
		
		private Roles(String name){
			role = new Role(name);
		}
		
		public Role getRole(){
			return role;
		}
		
	}
	

}

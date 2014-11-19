package com.econsult.model;

import java.io.Serializable;

public interface User extends Audtiable, Serializable{

	Long getId();
	String getFirstName();
	String getLastName();
	String getPassword();
	Role getRole();
}

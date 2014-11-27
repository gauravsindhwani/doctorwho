package com.econsult.model;

import java.io.Serializable;

public interface User extends Audtiable, Serializable{

	long getId();
	String getFirstName();
	String getLastName();
	String getPassword();
	Role getRole();
}

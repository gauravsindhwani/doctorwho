package com.econsult.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class DefaultUser implements PostingUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	Long id;
	
	

	@Column(name="FIRST_NAME")
	String firstName;

	@Column(name="LAST_NAME")
	String lastName;

	@Column(name="PASSWORD")
	String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON_DATE" , insertable = false)
	Date createdOnDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_ON_DATE")
	Date updatedOnDate;
	
	@OneToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "ROLE_NAME")
	Role role;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	Set<Post> posts = new HashSet<>();
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID")
	Set<Query> queries = new HashSet<>();
	
	
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
	public Long getId() {
		return id;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public Date getCreatedOnDate() {
		// TODO Auto-generated method stub
		return createdOnDate;
	}

	@Override
	public Date getUpdatedOnDate() {
		// TODO Auto-generated method stub
		return updatedOnDate;
	}
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@PrePersist
	@PreUpdate
	public void setDefaultUpdatedOnDate(){
		updatedOnDate = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultUser other = (DefaultUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

package com.econsult.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "POST")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="postId")
public class Post implements Audtiable {
	

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long postId;
	
	public Post(Long postId) {
		super();
		this.postId = postId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON_DATE", insertable = false)
	Date createdOnDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_ON_DATE")
	Date updatedOnDate;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "QUERY_ID")
	Query query;
	
	@Column(name = "TEXT", length = 65535)
	@Type(type = "text")
	String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	DefaultUser user;
	
	@OneToOne
	@JoinColumn(name = "POST_BY")
	Role postBy;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	Post parent;
	
	@JsonIgnore
	public Post getParent() {
		return parent;
	}

	public void setParent(Post parent) {
		this.parent = parent;
	}

	
	
	public Post() {
		super();
	}

	public Date getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}

	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

	//@JsonIgnore
	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DefaultUser getUser() {
		return user;
	}

	public void setUser(DefaultUser user) {
		this.user = user;
	}

	public Long getPostId() {
		return postId;
	}

	

	public Role getPostBy() {
		return postBy;
	}

	public void setPostBy(Role postBy) {
		this.postBy = postBy;
	}
	
	
}

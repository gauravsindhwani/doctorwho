package com.econsult.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "POST")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Post extends AbstractAuditableAutoIncrementingEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Post(Long postId) {
		super(postId);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUERY_ID")
	@JsonProperty(value = "queryId")
	Query query;
	
	@Column(name = "TEXT", length = 65535)
	@Type(type = "text")
	String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", foreignKey=@javax.persistence.ForeignKey(name = "POST_USER_FK"))
	@JsonProperty(value = "userId")
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

	@JsonProperty
	public long getUserId(){
		return user.getId();
	}
	
	public void setUser(DefaultUser user) {
		this.user = user;
		this.postBy = user.getRole();
	}

	public Role getPostBy() {
		return postBy;
	}

	public void setPostBy(Role postBy) {
		this.postBy = postBy;
	}
	
	@JsonProperty
	public long getQueryId(){
		return query.getId();
	}
	
}

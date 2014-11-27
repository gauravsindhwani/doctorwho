package com.econsult.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.econsult.model.Post;
import com.econsult.model.Query;

@Repository
@Transactional
public class QueryDao extends AbstractDao{
	@Transactional
	public Query getQuery(Long Id){
		return entitymanager.find(Query.class, Id);
	}
	
	public Query saveQuery(Query query){
		entitymanager.persist(query);
		return query;
	}
	
	public Post savePost(Post post){
		entitymanager.persist(post);
		return post;
	}
}

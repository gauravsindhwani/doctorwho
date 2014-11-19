package com.econsult.dao;

import org.springframework.stereotype.Repository;

import com.econsult.model.Post;
import com.econsult.model.Query;

@Repository
public class QueryDao extends AbstractDao{
	
	public Query getQuery(Long Id){
			return (Query)session.get(Query.class, Id);
	}
	
	public long saveQuery(Query query){
		session.beginTransaction();
		long id = (Long)session.save(query);
		session.getTransaction().commit();
		return id;
	}
	
	public long savePost(Post post){
		session.beginTransaction();
		long id = (Long)session.save(post);
		session.getTransaction().commit();
		return id;
	}
}

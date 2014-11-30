package com.econsult.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.econsult.model.Post;
import com.econsult.model.Query;

@Repository
@Transactional
public class QueryDaoImpl extends AbstractDao implements QueryDao{
	@Override
	public Query getQuery(Long Id){
		return find(Query.class, Id);
	}
	
	@Override
	public Query saveQuery(Query query){
		return save(query);
	}
	
	@Override
	public Post savePost(Post post){
		return save(post);
	}
}

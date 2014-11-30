package com.econsult.dao;

import com.econsult.model.Post;
import com.econsult.model.Query;

public interface QueryDao {

	public Query getQuery(Long Id);

	public Query saveQuery(Query query);

	public Post savePost(Post post);

}
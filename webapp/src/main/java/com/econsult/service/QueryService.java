package com.econsult.service;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.econsult.dao.QueryDao;
import com.econsult.model.Post;
import com.econsult.model.Query;
import com.econsult.model.lightweight.LWFirstQuery;

@Component
@Path("econsult/q/")
public class QueryService {
	private final static Logger logger = LoggerFactory.getLogger(QueryService.class); 

	@Autowired
	private QueryDao dao;
	
	@GET
	@Path("/query/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Query getQuery(@PathParam("Id") long Id){
		logger.trace("Getting query for ID {}", Id);
		return dao.getQuery(Id);
	}
	
	@POST
	@Path("/query/")
	public long createQuery(LWFirstQuery lightQuery){
		return dao.saveQuery(lightQuery.buildQuery()).getId();
	}
	
	@POST
	@Path("/post/")
	public long createPost(Post post){
		post.setUpdatedOnDate(new Date());
		return dao.savePost(post).getPostId();
	}
	
}

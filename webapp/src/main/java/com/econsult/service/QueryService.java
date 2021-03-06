package com.econsult.service;

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
import org.springframework.transaction.annotation.Transactional;

import com.econsult.model.Post;
import com.econsult.model.Query;
import com.econsult.model.lightweight.LWFirstQuery;
import com.econsult.model.lightweight.LWPost;
import com.googlecode.genericdao.dao.jpa.GeneralDAO;

@Component
@Path("/q/")
@Transactional
public class QueryService {
	private final static Logger logger = LoggerFactory.getLogger(QueryService.class); 

	
	@Autowired
	private GeneralDAO generalDao;
	
	
	@GET
	@Path("/query/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Query getQuery(@PathParam("Id") long id){
		logger.trace("Getting query for ID {}", id);
		return generalDao.find(Query.class, id);
	}
	
	@POST
	@Path("/query/")
	public long createQuery(LWFirstQuery lightQuery){
		return generalDao.save(lightQuery.buildQuery()).getId();
	}
	
	@GET
	@Path("/post/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Post getPost(@PathParam("Id") long id){
		logger.trace("Getting query for ID {}", id);
		return generalDao.find(Post.class, id);
	}
	
	@POST
	@Path("/post/")
	public long createPost(LWPost lwPost){
		Post post = lwPost.buildPost();
		return generalDao.save(post).getId();
	}
	
}

package com.econsult.model.lightweight;

import com.econsult.model.DefaultUser;
import com.econsult.model.Post;
import com.econsult.model.Query;

public class LWPost {
	
	long queryId;
	
	String text;
	
	DefaultUser user;

	public long getQueryId() {
		return queryId;
	}

	public void setQueryId(long queryId) {
		this.queryId = queryId;
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
	
	public Post buildPost(){
		Post post = new Post();
		post.setText(text);
		post.setUser(user);
		post.setQuery(new Query(queryId));
		return post;
	}

}

package com.maybank.labs.starter.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import com.maybank.labs.starter.utils.StarterConstants;

public class StarterRequestBean {

	private final Logger logger = LogManager.getLogger(this.getClass());


	private String uri;

	public String getUri() {
		return uri;
	}

	public void setUri(final String uri) {
		this.uri = uri;
	}

	public String getUser() {
		return user;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	private String user;

	private long timeTaken;


	public void testMe() {
		System.out.println(StarterConstants.PROJECT_IDENTIFIER+"REQUEST BEAN TEST-ME");
		System.out.println(StarterConstants.PROJECT_IDENTIFIER+"TIME CONSTRUCTED"+timeTaken);
	}

	@PostConstruct
	private void postConstruct() {
		logger.info(StarterConstants.PROJECT_IDENTIFIER+"REQUEST BEAN INITIATING");
		System.out.println(StarterConstants.PROJECT_IDENTIFIER+"REQUEST BEAN INITIATING");
		timeTaken = System.currentTimeMillis();
	}

	@PreDestroy
	public void preDestroy() {
		timeTaken = System.currentTimeMillis() - timeTaken;
		logger.info(StarterConstants.PROJECT_IDENTIFIER+"USER :: " + user + " >> URI :: " + uri + " >> TIME TAKEN ::" + timeTaken);
		System.out.println(StarterConstants.PROJECT_IDENTIFIER+"USER :: " + user + " >> URI :: " + uri + " >> TIME TAKEN ::" + timeTaken);
	}

}

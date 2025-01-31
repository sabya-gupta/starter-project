package com.maybank.labs.starter.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.maybank.labs.starter.bean.StarterRequestBean;

@Component
public class CommonFunctions {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Resource(name = "starterRequestBean")
	StarterRequestBean starterRequestBean;


	public void auditRequest(final HttpServletRequest request) {
		String loggedInUser = request.getHeader(StarterConstants.REQ_HEADER_LOGGED_IN_USER);
		loggedInUser = loggedInUser == null ? "TEST" : loggedInUser;
		final String reqURI = request.getRequestURI();

		starterRequestBean.setUri(reqURI);
		starterRequestBean.setUser(loggedInUser);

	}
}

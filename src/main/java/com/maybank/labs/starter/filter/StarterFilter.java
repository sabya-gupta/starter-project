package com.maybank.labs.starter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.maybank.labs.starter.utils.StarterConstants;

@WebFilter(filterName = "starterFilter", urlPatterns = "/*")
public class StarterFilter implements Filter {


	private final Logger logger = LogManager.getLogger(StarterFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final FilterChain filterChain) throws IOException, ServletException {

		final String queryStr = ((HttpServletRequest) servletRequest).getRequestURI();

		logger.error(StarterConstants.PROJECT_IDENTIFIER + "QUERY STRING = " + queryStr);

		filterChain.doFilter(servletRequest, servletResponse);

	}
}

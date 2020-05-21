package com.backbase.assignment.configuration;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {
	 private static final Logger logger = Logger.getLogger(CORSFilter.class);

	 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		  logger.info("Filtering on...........................................................");
		  chain.doFilter(req, res);
	 }

	 public void init(FilterConfig filterConfig) {}

	 public void destroy() {}

}

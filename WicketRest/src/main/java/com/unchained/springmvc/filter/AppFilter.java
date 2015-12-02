package com.unchained.springmvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AppFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			servletResponse.setHeader("Access-Control-Allow-Origin", "*");
			servletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			servletResponse.setHeader("Access-Control-Max-Age", "3600");
			servletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			chain.doFilter(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMessage", e);
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}

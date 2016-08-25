package com.bitwise.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpSession session = rq.getSession(false);
		
		if (rq.getRequestURI().equals("/Shopping_Cart/")) {
			chain.doFilter(rq, response);
			
		} 
		else if (rq.getRequestURI().equals("/Shopping_Cart/login")) {
			chain.doFilter(rq, response);
			
		} 
		else if (rq.getRequestURI().equals("/Shopping_Cart/success")) {
			chain.doFilter(rq, response);
		}

		else if (rq.getRequestURI().equals("/Shopping_Cart/logout")) {
			chain.doFilter(rq, response);
		}

		else if (session != null && session.getAttribute("user") != null) {
			chain.doFilter(rq, response);
		}

		else {
			
			out.print("<center><font color='red'>Please login first</font></center>");
			rq.getRequestDispatcher("/login").include(rq, response);
		}
		out.close();
		
		
	}

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

	
	
}

package com.example.book.filter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.example.book.model.Constants;

@WebFilter("/CountFilter")
public class CountFilter implements Filter {

	ServletContext context;
	
    public CountFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		AtomicInteger count = (AtomicInteger) context.getAttribute(Constants.HIT_COUNT);
		System.out.println(count.addAndGet(1));
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		context.setAttribute(Constants.HIT_COUNT, new AtomicInteger());
	}

}

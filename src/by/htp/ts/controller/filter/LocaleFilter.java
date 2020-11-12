package by.htp.ts.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LocaleFilter implements Filter {

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getParameter("sessionLocale") != null) {
			req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
		}
		chain.doFilter(request, response);

	}

}

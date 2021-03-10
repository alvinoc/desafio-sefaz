package filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import beans.LoggedInUserBean;

@WebFilter(urlPatterns = {"/home.xhtml*", "/update.xhtml*"})
public class LoginFilter implements Filter{

	@Inject
	private LoggedInUserBean login;

		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	    HttpServletResponse res = (HttpServletResponse) response;
		if(!login.isLoggedIn()) {
		    res.sendRedirect("/desafio-sefaz/index.xhtml");
		} else {
			chain.doFilter(request, response);
		}
		
		
	}

}

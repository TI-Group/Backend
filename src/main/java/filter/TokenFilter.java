package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dao.TokenDao;

public class TokenFilter implements Filter{

	private TokenDao tokenDao;
	private List<String> uris;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;  
		String uri=req.getRequestURI();
		if(!uris.contains(uri)){
			chain.doFilter(request,response);
			return;
		}
		int userId=Integer.parseInt(req.getParameter("user_id"));
		String token=req.getParameter("token");
		try {
			if(!tokenDao.checkToken(userId, token)){
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "status wrong");
	            return;
			}
			chain.doFilter(request,response);
		} catch (Exception e) {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(fConfig.getServletContext());
		tokenDao= (TokenDao) wac.getBean("tokenDao");
		uris=new ArrayList<String>();
		// add a URI into uris to go through filter if it come from app and you need ensure the uri has a token
		//uris.add("/track/rest/app/user/clientLogin");
	

	}

	

}

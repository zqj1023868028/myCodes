package com.zqj.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("User过滤器进来了");
		HttpServletRequest request2=(HttpServletRequest)request;
		HttpServletResponse response2=(HttpServletResponse) response;
		String sevletPaht = request2.getServletPath();
		System.out.println(sevletPaht);
		if(sevletPaht.startsWith("/User")) {
			HttpSession session=request2.getSession(false);
			System.out.println("是以User开头的");
			if(session!=null) {
				System.out.println("session不是空的"+session.getAttribute("loginuser"));
				Object userid=session.getAttribute("loginuser");
				System.out.println(userid);
				if(userid!=null) {
					System.out.println(userid);
					chain.doFilter(request2, response2);
				}else {
				request2.getRequestDispatcher("/LoginServlet.jsp").forward(request2, response2);
					return;
				}
			}
				
		}else {
		chain.doFilter(request2, response2);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

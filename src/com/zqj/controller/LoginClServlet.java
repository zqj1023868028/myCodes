package com.zqj.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zqj.pojo.User;
import com.zqj.service.UserService;

/**
 * Servlet implementation class LoginClServlet
 */
public class LoginClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginClServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("成功挑战");
		int id=Integer.parseInt(request.getParameter("id"));
		String password=request.getParameter("password");
		System.out.println(request + "***********************************");
		User user=new User(id, "", "", 0, password);
		UserService userService=new UserService();
		String val=request.getParameter("iskeepinfo");
		String id2=request.getParameter("id");
		String password2=request.getParameter("password");
		String va2=request.getParameter("autoLogin");
		if(val!=null&&val.equals("keep")) {
			Cookie cookie=new Cookie("id", id2);
			Cookie cookie2=new Cookie("password", password2);
			Cookie cookie12=new Cookie("iskeepinfo", "true");
			cookie.setMaxAge(7*24*3600);
			response.addCookie(cookie);
			response.addCookie(cookie2);
			response.addCookie(cookie12);
			if(va2!=null&&va2.equals("checked")) {
				Cookie cookie3=new Cookie("autoLogin", "true");
				cookie3.setMaxAge(30*24*3600);
				response.addCookie(cookie3);
				System.out.println(12345678);
			}
		}
		if(va2==null) {
			String usercheckcode=request.getParameter("checkcode");
			HttpSession session=request.getSession();
			String servercheckcode=(String) session.getAttribute("checkcode");
			System.out.println(usercheckcode+"|"+servercheckcode);
			if(!usercheckcode.equals(servercheckcode)) {
				request.setAttribute("err", "你的输入有误");
				request.getRequestDispatcher("/LoginServlet.jsp").forward(request, response);
				return;
			}
		}
		System.out.println("zheli");
		if(userService.checkUser(user)) {
			HttpSession session=request.getSession();
			session.setAttribute("loginuser", user.getId());
			getTime(request, response);
			request.getRequestDispatcher("MainFrame.jsp").forward(request, response);
		}
		else {
			request.setAttribute("err", "你的输入有误");
			request.getRequestDispatcher("LoginServlet.jsp").forward(request, response);
			return;
		}
		}

	private void getTime(HttpServletRequest request, HttpServletResponse response) {
		Cookie []cookies=request.getCookies();
		boolean b=false;
		if(cookies!=null) {
		for (Cookie cookie : cookies) {
			String name=cookie.getName();
			if("lasttime".equals(name)) {
				request.setAttribute("log", "您上次登录时间是"+cookie.getValue());
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd-hh-mm-ss");
				Date date=new Date();
				String nowTime=simpleDateFormat.format(date);
				System.out.println(nowTime);
				cookie.setValue(nowTime);
				cookie.setMaxAge(7*24*3600);
				response.addCookie(cookie);
				b=true;
				break;
			}
		}
		}
		if(!b) {
			System.out.println(123);
			request.setAttribute("log", "您是第一次登录");
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd-hh-mm-ss");
			Date date=new Date();
			String nowTime=simpleDateFormat.format(date);
			System.out.println(nowTime);
			Cookie cookie=new Cookie("lasttime",nowTime);
			cookie.setMaxAge(7*24*3600);
			response.addCookie(cookie);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

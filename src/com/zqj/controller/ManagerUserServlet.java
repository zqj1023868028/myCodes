package com.zqj.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zqj.pojo.User;
import com.zqj.service.UserService;

/**
 * Servlet implementation class ManagerUserServlet
 */
public class ManagerUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pageNow=Integer.parseInt(request.getParameter("pageNow"));
		int pageSize=3;
		int pageCount=0;
		UserService userService=new UserService();
		pageCount=userService.getPageCount(pageSize);
		List<User> userlist=userService.getUsersByPage(pageNow, pageSize);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("userslist", userlist);
		for (User user : userlist) {
			System.out.println(user);
		}
		request.getRequestDispatcher("/ManagerUsers.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

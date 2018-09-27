package com.zqj.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zqj.pojo.User;
import com.zqj.service.UserService;

/**
 * Servlet implementation class UserClServlet
 */
public class UserClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserClServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**select * from users limit 8,5;
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UserService userService=new UserService();
		String email=request.getParameter("email");
		System.out.println(email);
		String flag=request.getParameter("flag");
		System.out.println(flag);
		if("del".equals(flag)) {
			delUser(request, response, userService);
		}
		else if ("gotoUpdView".equals(flag)) {
			String id=request.getParameter("id");
			User user=userService.getUserById(id);
			request.setAttribute("userinfo", user);
			request.getRequestDispatcher("/UpdateUserView.jsp").forward(request, response);
		}else if ("update".equals(flag)) {
			update(request, response, userService);
		}else if("gotoAddUser".equals(flag)){
			request.getRequestDispatcher("/AddUserView.jsp").forward(request, response);
		}else if ("add".equals(flag)) {
			addUser(request, response, userService);
		}
		if(email==null) {
			response.getWriter().write("");
		}else {
			User user=new User();
			user.setEmail(email);
			if(userService.checkUserEmail(user)) {
				System.out.println("邮箱已被使用");
				response.getWriter().write("邮箱已被使用!");
				return;
			}else {
				response.getWriter().write("");
			}
			
		}
	}

	private void delUser(HttpServletRequest request, HttpServletResponse response, UserService userService)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		if(userService.delUser(id)) {
			request.setAttribute("info", "删除成功");
			request.getRequestDispatcher("/Ok.jsp").forward(request, response);
		}else {
			request.setAttribute("info", "删除失败");
			request.getRequestDispatcher("/Err.jsp").forward(request, response);
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response, UserService userService)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String grade=request.getParameter("grade");
		String password=request.getParameter("password");
		User user=new User();
		user.setEmail(email);
		user.setGrade(Integer.parseInt(grade));
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(user.toString());
		if(userService.addUser(user)) {
			request.setAttribute("info", "添加成功");
			request.getRequestDispatcher("/Ok.jsp").forward(request, response);
		}else {
			request.setAttribute("info", "添加失败");
			request.getRequestDispatcher("/Err.jsp").forward(request, response);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response, UserService userService)
			throws ServletException, IOException {
		System.out.println("okk");
		String id=request.getParameter("id");
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String grade=request.getParameter("grade");
		String password=request.getParameter("password");
		System.out.println(id);
		User user=new User(Integer.parseInt(id), username, email, Integer.parseInt(grade), password);
		System.out.println(user.toString());
		if(userService.updUser(user)) {
			request.setAttribute("info", "修改成功");
			request.getRequestDispatcher("/Ok.jsp").forward(request, response);
		}else {
			request.setAttribute("info", "修改失败");
			request.getRequestDispatcher("/Err.jsp").forward(request, response);
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

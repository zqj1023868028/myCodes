package com.zqj.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zqj.pojo.User;
import com.zqj.service.UserService;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//PrintWriter out =response.getWriter();
		System.out.println(1233534);
		String referer=request.getHeader("Referer");
		System.out.println(referer);
		if(referer==null||!referer.startsWith("http://localhost:8080/UsersManager3")) {
			response.sendRedirect("/UsersManager4/Error");
		}
		String filename=request.getParameter("filename");
		String temp=java.net.URLEncoder.encode(filename,"utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+temp);
		String path=this.getServletContext().getRealPath("/img/"+filename);
		FileInputStream fis=new FileInputStream(path);
		byte buff[]=new byte[1024];
		int len=0;
		OutputStream os=response.getOutputStream();
		while((len=fis.read(buff))>0) {
			os.write(buff, 0, len);
		}
		os.close();
		fis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

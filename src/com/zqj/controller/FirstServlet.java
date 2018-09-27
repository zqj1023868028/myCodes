package com.zqj.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	@Override
	public void init() throws ServletException {
		FileReader fileReader;
		BufferedReader bufferedReader = null;
		String filepath = this.getServletContext().getRealPath("/WEB-INF/num.txt");
		System.out.println(filepath);
		try {
			fileReader=new FileReader(filepath);
			bufferedReader=new BufferedReader(fileReader);
			String nums=bufferedReader.readLine();
			System.out.println("≥ı ºªØusernum"+nums);
			this.getServletContext().setAttribute("usernum", nums);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void destroy() {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter = null;
		String filepath = this.getServletContext().getRealPath("/WEB-INF/num.txt");
		try {
			fileWriter=new FileWriter(filepath);
			bufferedWriter=new BufferedWriter(fileWriter);
			
			Object nuns = this.getServletContext().getAttribute("usernum");
			System.out.println(nuns);
			bufferedWriter.write(nuns.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

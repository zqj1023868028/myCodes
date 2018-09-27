package com.zqj.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CreateCode() {
       }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setDateHeader("Exprires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Content-Type", "imge/jpeg");
		BufferedImage image = new BufferedImage(60, 30, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 60, 30);
		g.setColor(Color.blue);
		g.setFont(new Font(null, Font.BOLD, 20));
		String num=makeNum();
		request.getSession().setAttribute("checkcode", num);
		g.drawString(num, 0, 20);
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	private String makeNum() {
		Random r= new Random();
		String num =r.nextInt(9999)+"";
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<4-num.length();i++) {
			sb.append("0");
		}
		num = sb.toString()+num;
		System.out.println(num);
		return num;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

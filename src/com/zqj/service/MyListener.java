package com.zqj.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.zqj.pojo.User;

@WebListener
public class MyListener implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("监听器进来了");
		ServletRequest request = sre.getServletRequest();
		String ip=request.getRemoteAddr();
		ServletContext app=request.getServletContext();
		if(app.getAttribute("userList")==null) {
			System.out.println("第一次创建userlist");
			List userList=new ArrayList();
			userList.add(ip);
			System.out.println("ip:"+ip);
			app.setAttribute("userList", userList);
			app.setAttribute("usernum", userList.size());
		}else {
			List userList=(List) app.getAttribute("userList");
			System.out.println(ip);
			int flag=0;
			for(int i=0;i<userList.size();i++) {
				String ip2=(String) userList.get(i);
				if(ip.equals(ip2)) {
					flag=1;
					break;
				}
			}
			if(flag==0) {
				userList.add(ip);
				app.setAttribute("userList", userList);
				System.out.println("当前人数"+userList.size());
				app.setAttribute("usernum", userList.size());
			}
		}

		
	}

}

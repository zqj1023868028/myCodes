package com.zqj.decorators;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomRequest extends HttpServletRequestWrapper{
	public CustomRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> newMap = new HashMap<>();

		Map<String, String[]> originalMap = super.getParameterMap();
		if(originalMap==null) {
			return super.getParameterMap();
		}
		try {
			for(String key:originalMap.keySet()) {
				String[] values = originalMap.get(key);
				for (int i=0;i<values.length;i++) {
					byte[] bytes = values[i].getBytes("ISO8859-1");
					System.out.println(bytes+"ÂÒÂë");
					values[i]=new String(bytes, "utf-8");
					System.out.println(values[i].getBytes()+"ºÅÂë");
				}
				newMap.put(key, values);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("b");
		return newMap;
	}
	@Override
	public Enumeration<String> getParameterNames() {
		Map<String, String[]> map = this.getParameterMap();
		Set<String> keySet = map.keySet();
		Vector keyVector=(Vector) keySet;
		System.out.println('a');
		return keyVector.elements();
	}
	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map = this.getParameterMap();
		System.out.println(map.get(name));
		return map.get(name);
	}
	@Override
	public String getParameter(String name) {
		String[] values=this.getParameterValues(name);
		if(values==null) {
			return super.getParameter(name);
		}else {
			System.out.println(values[0]+"zzzqq");
			return values[0];
		}
	}
}

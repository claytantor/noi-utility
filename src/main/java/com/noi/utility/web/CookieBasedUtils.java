package com.noi.utility.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieBasedUtils {
	public static boolean hasCookie(HttpServletRequest request, String name)
	{
		Cookie[] cookies = request.getCookies();
        boolean foundCookie = false;
        for(int i = 0; i < cookies.length; i++) { 
            Cookie cookie1 = cookies[i];
            if (cookie1.getName().equals(name)) {           
                foundCookie = true;
            }
        }  
		return foundCookie;
	}
	
	public static void setCookie(HttpServletResponse response, String name, String value, Integer maxAgeSeconds)
	{
		Cookie cookie1 = new Cookie(name, value);
		if(maxAgeSeconds != null)
			cookie1.setMaxAge(maxAgeSeconds);
        response.addCookie(cookie1); 
	}

	public static String getCookie(HttpServletRequest request, String name)
	{
		Cookie[] cookies = request.getCookies();
        for(int i = 0; i < cookies.length; i++) { 
            Cookie cookie1 = cookies[i];
            if (cookie1.getName().equals(name)) {           
                return cookie1.getValue();
            }
        }  
		return null; 
	}	
	
	public static void removeCookies(HttpServletRequest request, HttpServletResponse response)
	{
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			removeCookie(response, cookie.getName());
		}
	}
	
	public static void removeCookie(HttpServletResponse response, String name)
	{
		Cookie cookie = new Cookie( name, "" );
		cookie.setMaxAge( 0 );
		response.addCookie(cookie); 
	}	
}

package com.songlou.instrument;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GlobalHelper {
    /**
     * 判断字符串是否为NULL或者为空
     * @param content
     * @return
     */
    public static boolean IsNullOrEmpty(String content){
    	return content == null || content.isEmpty();
    }
    
    /**
     * 判断是否为Ajax请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header)){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * 获取指定cookie
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组

        if (cookies == null) {//如果没有cookie数组
            return null;
        }
        
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName)){
            	return cookie.getValue();
            }
        }
        
        return null;
    }
}

package com.songlou.instrument;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GlobalHelper {
    /**
     * �ж��ַ����Ƿ�ΪNULL����Ϊ��
     * @param content
     * @return
     */
    public static boolean IsNullOrEmpty(String content){
    	return content == null || content.isEmpty();
    }
    
    /**
     * �ж��Ƿ�ΪAjax����
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
     * ��ȡָ��cookie
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();//�����������ݣ��ҵ�cookie����

        if (cookies == null) {//���û��cookie����
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

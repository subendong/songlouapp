package com.songlou.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songlou.annotation.AuthorizeAnnotation;
import com.songlou.common.DesHelper;
import com.songlou.common.ResultHelper;
import com.songlou.model.SiteModel;
import com.songlou.pojo.Admin;
import com.songlou.service.AdminService;

public class AuthorizeInterceptor  extends HandlerInterceptorAdapter {
	@Autowired
	private AdminService adminService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            AuthorizeAnnotation authorizeAnnotation = ((HandlerMethod) handler).getMethodAnnotation(AuthorizeAnnotation.class);
            // �����uri
            //String uri = request.getRequestURI();
            
            //û��������ҪȨ��,������������֤Ȩ��
            if(authorizeAnnotation == null || authorizeAnnotation.validate() == false){
                return true;
            }
            else{
            	String cookieValue = getCookie(request, SiteModel.cookieKey);
                if (cookieValue == null || cookieValue.isEmpty()) {
                    boolean isAjaxRequest = isAjaxRequest(request);
                    if (isAjaxRequest) {
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write("���Ѿ�̫��ʱ��û�в���,��ˢ��ҳ��");
                        return false;
                    }
                    response.sendRedirect("/songlouapp/login/index");
                    return false;
                }
                
                //����
                String strJson = null;
    			try {
    				strJson = new DesHelper().decrypt(cookieValue);
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
                
                //�����л�
                Admin admin = null;
        		ObjectMapper mapper = new ObjectMapper();
        		try {
        			admin = mapper.readValue(strJson, Admin.class);
        		} catch (JsonProcessingException e) {
        			e.printStackTrace();
        		}
        		
        		//https://www.cnblogs.com/digdeep/p/4770004.html?tvd
        		ResultHelper resultHelper = adminService.select(admin);
        		if(!resultHelper.isSuccess()){
        			
        		}
        		return true;
            }
        }
        else{
            return true;
        }
	}

    /**
     * �ж��Ƿ�ΪAjax����
     * @param request
     * @return
     */
    private static boolean isAjaxRequest(HttpServletRequest request) {
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
    private String getCookie(HttpServletRequest request, String cookieName){
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

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
            // 请求的uri
            //String uri = request.getRequestURI();
            
            //没有声明需要权限,或者声明不验证权限
            if(authorizeAnnotation == null || authorizeAnnotation.validate() == false){
                return true;
            }
            else{
            	String cookieValue = getCookie(request, SiteModel.cookieKey);
                if (cookieValue == null || cookieValue.isEmpty()) {
                    boolean isAjaxRequest = isAjaxRequest(request);
                    if (isAjaxRequest) {
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write("您已经太长时间没有操作,请刷新页面");
                        return false;
                    }
                    response.sendRedirect("/songlouapp/login/index");
                    return false;
                }
                
                //解密
                String strJson = null;
    			try {
    				strJson = new DesHelper().decrypt(cookieValue);
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
                
                //反序列化
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
     * 判断是否为Ajax请求
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
     * 获取指定cookie
     * @param request
     * @param cookieName
     * @return
     */
    private String getCookie(HttpServletRequest request, String cookieName){
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

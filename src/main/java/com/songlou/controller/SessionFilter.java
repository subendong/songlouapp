package com.songlou.controller;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songlou.common.DesHelper;
import com.songlou.common.ResultHelper;
import com.songlou.model.SiteModel;
import com.songlou.pojo.Admin;
import com.songlou.service.AdminService;
import com.songlou.service.AdminServiceImpl;
import com.songlou.service.LoginService;

/**
 * 1.sessionFilter参考网址:
 * https://www.cnblogs.com/juin1058/p/6605468.html
 * 2.cookie参考网址:
 * https://www.cnblogs.com/red-code/p/6629363.html
 * http://blog.csdn.net/u011848397/article/details/52201339
 * 3.在sessionFilter中注入service会有问题：
 * https://www.cnblogs.com/digdeep/p/4770004.html?tvd
 * http://blog.csdn.net/hanqi1202/article/details/48517081
 * 4.可以将session换成cookie，毕竟登录模块大部分都是使用的cookie
 * @author sbd04462
 *
 */
public class SessionFilter extends OncePerRequestFilter{
	@Autowired
    private LoginService loginService;	
	
	/**
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// 不过滤的uri
        String[] notFilter = new String[] { "/login", "/css", "/fonts", "/images", "/js", "/upload" };
        // 请求的uri
        String uri = request.getRequestURI();        
        // 是否过滤
        boolean isFilter = true;
        for (String s : notFilter) {
            if (uri.indexOf(s) != -1) {
                // 如果uri中包含不过滤的uri，则不进行过滤
            	isFilter = false;
                break;
            }
        }

        if (isFilter) {
            String cookieValue = getCookie(request, SiteModel.cookieKey);
            if (cookieValue == null || cookieValue.isEmpty()) {
                boolean isAjaxRequest = isAjaxRequest(request);
                if (isAjaxRequest) {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("您已经太长时间没有操作,请刷新页面");
                    return;
                }
                response.sendRedirect("/songlouapp/login/index");
                return;
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
    	/*	AdminService adminService = new AdminServiceImpl();
    		ResultHelper resultHelper = adminService.select(admin);
    		if(!resultHelper.isSuccess()){
    			
    		}*/
        } 
        filterChain.doFilter(request, response);
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
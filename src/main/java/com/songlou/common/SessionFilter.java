package com.songlou.common;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * sessionFilter参考网址:https://www.cnblogs.com/juin1058/p/6605468.html
 * cookie参考网址:https://www.cnblogs.com/red-code/p/6629363.html
 * 可以将session换成cookie，毕竟登录模块大部分都是使用的cookie
 * @author sbd04462
 *
 */
public class SessionFilter extends OncePerRequestFilter{
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
            // 执行过滤
            // 从session中获取登录者实体
            Object obj = getCookie(request, "songlouappmanage");
            if (obj == null) {
                boolean isAjaxRequest = isAjaxRequest(request);
                if (isAjaxRequest) {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("您已经太长时间没有操作,请刷新页面");
                    return;
                }
                response.sendRedirect("/songlouapp/login/index");
                return;
            }
        } 
        filterChain.doFilter(request, response);
	}

    /**
     * 判断是否为Ajax请求 <功能详细描述>
     * 
     * @param request
     * @return 是true, 否false
     * @see [类、类#方法、类#成员]
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

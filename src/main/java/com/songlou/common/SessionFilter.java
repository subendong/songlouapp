package com.songlou.common;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * sessionFilter�ο���ַ:https://www.cnblogs.com/juin1058/p/6605468.html
 * cookie�ο���ַ:https://www.cnblogs.com/red-code/p/6629363.html
 * ���Խ�session����cookie���Ͼ���¼ģ��󲿷ֶ���ʹ�õ�cookie
 * @author sbd04462
 *
 */
public class SessionFilter extends OncePerRequestFilter{
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// �����˵�uri
        String[] notFilter = new String[] { "/login", "/css", "/fonts", "/images", "/js", "/upload" };
        // �����uri
        String uri = request.getRequestURI();        
        // �Ƿ����
        boolean isFilter = true;
        for (String s : notFilter) {
            if (uri.indexOf(s) != -1) {
                // ���uri�а��������˵�uri���򲻽��й���
            	isFilter = false;
                break;
            }
        }

        if (isFilter) {
            // ִ�й���
            // ��session�л�ȡ��¼��ʵ��
            Object obj = getCookie(request, "songlouappmanage");
            if (obj == null) {
                boolean isAjaxRequest = isAjaxRequest(request);
                if (isAjaxRequest) {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("���Ѿ�̫��ʱ��û�в���,��ˢ��ҳ��");
                    return;
                }
                response.sendRedirect("/songlouapp/login/index");
                return;
            }
        } 
        filterChain.doFilter(request, response);
	}

    /**
     * �ж��Ƿ�ΪAjax���� <������ϸ����>
     * 
     * @param request
     * @return ��true, ��false
     * @see [�ࡢ��#��������#��Ա]
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

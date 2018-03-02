package com.songlou.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
//import com.songlou.model.SiteModel;

/**
 * 1.sessionFilter�ο���ַ:
 * https://www.cnblogs.com/juin1058/p/6605468.html
 * 2.cookie�ο���ַ:
 * https://www.cnblogs.com/red-code/p/6629363.html
 * http://blog.csdn.net/u011848397/article/details/52201339
 * 3.��sessionFilter��ע��service�������⣺
 * https://www.cnblogs.com/digdeep/p/4770004.html?tvd
 * http://blog.csdn.net/hanqi1202/article/details/48517081
 * 4.���Խ�session����cookie���Ͼ���¼ģ��󲿷ֶ���ʹ�õ�cookie
 * ��ʹʧ��ҲҪ��ע����������γ���ʹ��SessionFilter��������Ȩ�޴������ǲ��У�ʼ���޷���Filter����ע��AdminService��������������ע������Ȩ�޿���
 * @author sbd04462
 *
 */
public class SessionFilter extends OncePerRequestFilter{
	//@Autowired
    //private LoginService loginService;	
	
	/**
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		// �����˵�uri
//        String[] notFilter = new String[] { "/login", "/css", "/fonts", "/images", "/js", "/upload" };
//        // �����uri
//        String uri = request.getRequestURI();        
//        // �Ƿ����
//        boolean isFilter = true;
//        for (String s : notFilter) {
//            if (uri.indexOf(s) != -1) {
//                // ���uri�а��������˵�uri���򲻽��й���
//            	isFilter = false;
//                break;
//            }
//        }
//
//        if (isFilter) {
//            String cookieValue = getCookie(request, SiteModel.cookieKey);
//            if (cookieValue == null || cookieValue.isEmpty()) {
//                boolean isAjaxRequest = isAjaxRequest(request);
//                if (isAjaxRequest) {
//                    response.setContentType("text/html;charset=UTF-8");
//                    response.getWriter().write("���Ѿ�̫��ʱ��û�в���,��ˢ��ҳ��");
//                    return;
//                }
//                response.sendRedirect("/songlouapp/login/index");
//                return;
//            }
//            
//            //����
//        /*    String strJson = null;
//			try {
//				strJson = new DesHelper().decrypt(cookieValue);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//            */
//            //�����л�
//        /*    Admin admin = null;
//    		ObjectMapper mapper = new ObjectMapper();
//    		try {
//    			admin = mapper.readValue(strJson, Admin.class);
//    		} catch (JsonProcessingException e) {
//    			e.printStackTrace();
//    		}*/
//    		
//    		//https://www.cnblogs.com/digdeep/p/4770004.html?tvd
//    	/*	AdminService adminService = new AdminServiceImpl();
//    		ResultHelper resultHelper = adminService.select(admin);
//    		if(!resultHelper.isSuccess()){
//    			
//    		}*/
//        } 
        filterChain.doFilter(request, response);
	}

    /**
     * �ж��Ƿ�ΪAjax����
     * @param request
     * @return
     */
//    private static boolean isAjaxRequest(HttpServletRequest request) {
//        String header = request.getHeader("X-Requested-With");
//        if (header != null && "XMLHttpRequest".equals(header)){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
    
    /**
     * ��ȡָ��cookie
     * @param request
     * @param cookieName
     * @return
     */
//    private String getCookie(HttpServletRequest request, String cookieName){
//        Cookie[] cookies = request.getCookies();//�����������ݣ��ҵ�cookie����
//
//        if (cookies == null) {//���û��cookie����
//            return null;
//        }
//        
//        for(Cookie cookie : cookies){
//            if(cookie.getName().equals(cookieName)){
//            	return cookie.getValue();
//            }
//        }
//        
//        return null;
//    }
}
package com.songlou.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.songlou.annotation.NeedLogin;
import com.songlou.instrument.GlobalHelper;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Admin;
import com.songlou.pojo.Rank;
import com.songlou.service.AdminService;
import com.songlou.service.LoginService;
/*
 * HandlerInterceptor��HandlerInterceptorAdapter������
 * ���ʹ��HandlerInterceptor��������������������û���õ�������д�ϣ���HandlerInterceptorAdapter����Ҫ
 * HandlerInterceptorAdapter�̳���HandlerInterceptor��Ĭ����д��HandlerInterceptor����������
 * ����ʹ��HandlerInterceptorAdapter�Ļ���ֻ��Ҫ��д�õ��ķ�������
 * ����HandlerInterceptor��ʹ�ã�https://www.cnblogs.com/angto64/p/5264959.html
 */
public class AuthorizeInterceptor  extends HandlerInterceptorAdapter {
	@Autowired
	private AdminService adminService;
	@Autowired
	private LoginService loginService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            NeedLogin authorizeAnnotation = ((HandlerMethod) handler).getMethodAnnotation(NeedLogin.class);
            
            //�������������֤ע�⣬����ȷ�涨��Ҫ��֤
            if(authorizeAnnotation != null && authorizeAnnotation.validate()){
            	ResultHelper resultHelper = this.Check(request);
            	if(!resultHelper.isSuccess()){
            		boolean isAjaxRequest = GlobalHelper.isAjaxRequest(request);
            		if (isAjaxRequest) {
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(resultHelper.getMessage());
                        return false;
                    }
                    response.sendRedirect("/songlouapp/login/index");
                    return false;
            	}
            }
            
            return true;
        }
        else{
            return true;
        }
	}
	
	/**
	 * ��֤�û��Ϸ���
	 * @param request
	 * @return
	 */
	private ResultHelper Check(HttpServletRequest request){
		//��cookie�л�ȡ��ǰ��¼�û�
		ResultHelper resultHelper = loginService.getAdminFromCookie(request);
		if(!resultHelper.isSuccess()){
			return resultHelper;
		}
		Admin admin = (Admin)resultHelper.getData();
		
		//ʵʱ�жϵ�ǰ�û��Ƿ���Ч��ÿ�ζ���ѯ���ݿ⡣Ŀǰ������ƥ���˺ź����룬�Ժ�Ҫ����������Ƿ���Ч��
		//https://www.cnblogs.com/digdeep/p/4770004.html?tvd
		resultHelper = adminService.select(admin);
		if(!resultHelper.isSuccess()){
			return new ResultHelper(1, false, "�û�������", null);
		}
		
		/**********�����д�����***************************/
		//������Ȩ��
        //���������uri��ת����ʵ����Ҫ���ַ���
        String uri = request.getRequestURI();///songlouapp/admin/list
        String path = request.getContextPath();///songlouapp
        path = uri.replaceFirst(path, "").replaceFirst("/",  "");//��һ��б�ܲ���Ҫ��Ҳ��Ҫ�滻��
        //��session��ȡ��ǰ�û�ӵ�е�Ȩ��
        List<Rank> ranks = loginService.getRankListSession(request, admin);
        boolean isHaveRank = false;
        for(Rank rank: ranks){
        	String tempPath = rank.getController() + "/" + rank.getAction();
        	if(path.equals(tempPath)){
        		isHaveRank = true;
        		break;
        	}
        }
        if(!isHaveRank){
        	return new ResultHelper(1, false, "û��Ȩ��", null);
        }
		
		return new ResultHelper(0, true, "success", null);
	}
}

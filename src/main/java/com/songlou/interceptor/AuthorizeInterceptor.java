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
 * HandlerInterceptor和HandlerInterceptorAdapter的区别：
 * 如果使用HandlerInterceptor，其它两个方法不管有没有用到，都得写上，而HandlerInterceptorAdapter不需要
 * HandlerInterceptorAdapter继承了HandlerInterceptor，默认重写了HandlerInterceptor的三个方法
 * 所以使用HandlerInterceptorAdapter的话，只需要重写用到的方法就行
 * 关于HandlerInterceptor的使用：https://www.cnblogs.com/angto64/p/5264959.html
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
            
            //方法上添加了验证注解，且明确规定需要验证
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
	 * 验证用户合法性
	 * @param request
	 * @return
	 */
	private ResultHelper Check(HttpServletRequest request){
		//从cookie中获取当前登录用户
		ResultHelper resultHelper = loginService.getAdminFromCookie(request);
		if(!resultHelper.isSuccess()){
			return resultHelper;
		}
		Admin admin = (Admin)resultHelper.getData();
		
		//实时判断当前用户是否有效，每次都查询数据库。目前仅仅是匹配账号和密码，以后要添加条件：是否有效等
		//https://www.cnblogs.com/digdeep/p/4770004.html?tvd
		resultHelper = adminService.select(admin);
		if(!resultHelper.isSuccess()){
			return new ResultHelper(1, false, "用户不存在", null);
		}
		
		/**********功能有待完善***************************/
		//检查具体权限
        //处理请求的uri，转换成实际需要的字符串
        String uri = request.getRequestURI();///songlouapp/admin/list
        String path = request.getContextPath();///songlouapp
        path = uri.replaceFirst(path, "").replaceFirst("/",  "");//第一个斜杠不需要，也需要替换掉
        //从session中取当前用户拥有的权限
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
        	return new ResultHelper(1, false, "没有权限", null);
        }
		
		return new ResultHelper(0, true, "success", null);
	}
}

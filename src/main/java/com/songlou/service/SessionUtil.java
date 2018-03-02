package com.songlou.service;

import javax.servlet.http.HttpServletRequest;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Admin;

/**
 * 会话类
 * 对request的session和cookie进行操作
 * @author sbd04462
 *
 */
public class SessionUtil {
	/**
	 * 获取当前登录用户信息（从cookie获取的）
	 * @param request
	 * @return
	 */
	public static Admin getCurrentAdmin(HttpServletRequest request) {
		try {
			LoginService loginService = new LoginServiceImpl();
			ResultHelper resultHelper = loginService.getAdminFromCookie(request);
			if(!resultHelper.isSuccess()){
				return new Admin();
			}
			
			return (Admin)resultHelper.getData();
		} catch (Exception e) {
			return new Admin();
		}
	}
}

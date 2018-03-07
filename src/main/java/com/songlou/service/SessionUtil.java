package com.songlou.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Admin;
import com.songlou.pojo.Rank;

/**
 * 会话工具类
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
			LoginService  loginService = new LoginServiceImpl();
			ResultHelper resultHelper = loginService.getAdminFromCookie(request);
			if(!resultHelper.isSuccess()){
				return new Admin();
			}
			
			return (Admin)resultHelper.getData();
		} catch (Exception e) {
			return new Admin();
		}
	}
	
	/**
	 * 获取权限列表
	 * @return
	 */
	public static List<Rank> getRankListSession(HttpServletRequest request, Admin admin){
		LoginService  loginService = new LoginServiceImpl();
		List<Rank> ranks = loginService.getRankListSession(request, admin);
		return ranks;
	}
}

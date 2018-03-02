package com.songlou.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Admin;

public interface LoginService {
	/**
	 * 登录
	 * @param admin
	 * @param response
	 * @return
	 */
	public ResultHelper Login(Admin admin, HttpServletResponse response);
	
	/**
	 * 从cookie中获取当前用户信息
	 * @param request
	 * @return
	 */
	public ResultHelper getAdminFromCookie(HttpServletRequest request);
}
package com.songlou.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Admin;
import com.songlou.pojo.Rank;

public interface LoginService {
	/**
	 * 登录
	 * @param admin
	 * @param response
	 * @return
	 */
	public ResultHelper Login(Admin admin, HttpServletResponse response);
	
	/**
	 * 退出
	 * @return
	 */
	public ResultHelper logout(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 从cookie中获取当前用户信息
	 * @param request
	 * @return
	 */
	public ResultHelper getAdminFromCookie(HttpServletRequest request);
	
	/**
	 * 获取权限列表
	 * @param request
	 * @param admin
	 * @return
	 */
	public List<Rank> getRankListSession(HttpServletRequest request, Admin admin);
}
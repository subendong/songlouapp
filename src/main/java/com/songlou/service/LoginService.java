package com.songlou.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Admin;

public interface LoginService {
	/**
	 * ��¼
	 * @param admin
	 * @param response
	 * @return
	 */
	public ResultHelper Login(Admin admin, HttpServletResponse response);
	
	/**
	 * ��cookie�л�ȡ��ǰ�û���Ϣ
	 * @param request
	 * @return
	 */
	public ResultHelper getAdminFromCookie(HttpServletRequest request);
}
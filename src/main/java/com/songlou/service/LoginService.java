package com.songlou.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Admin;
import com.songlou.pojo.Rank;

public interface LoginService {
	/**
	 * ��¼
	 * @param admin
	 * @param response
	 * @return
	 */
	public ResultHelper Login(Admin admin, HttpServletResponse response);
	
	/**
	 * �˳�
	 * @return
	 */
	public ResultHelper logout(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * ��cookie�л�ȡ��ǰ�û���Ϣ
	 * @param request
	 * @return
	 */
	public ResultHelper getAdminFromCookie(HttpServletRequest request);
	
	/**
	 * ��ȡȨ���б�
	 * @param request
	 * @param admin
	 * @return
	 */
	public List<Rank> getRankListSession(HttpServletRequest request, Admin admin);
}
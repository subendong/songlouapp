package com.songlou.service;

import javax.servlet.http.HttpServletResponse;
import com.songlou.common.ResultHelper;
import com.songlou.pojo.Admin;

public interface LoginService {
	/**
	 * µÇÂ¼
	 * @param admin
	 * @param response
	 * @return
	 */
	public ResultHelper Login(Admin admin, HttpServletResponse response);
}
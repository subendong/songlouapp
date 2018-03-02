package com.songlou.service;

import javax.servlet.http.HttpServletRequest;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Admin;

/**
 * �Ự��
 * ��request��session��cookie���в���
 * @author sbd04462
 *
 */
public class SessionUtil {
	/**
	 * ��ȡ��ǰ��¼�û���Ϣ����cookie��ȡ�ģ�
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

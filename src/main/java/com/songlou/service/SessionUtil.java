package com.songlou.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Admin;
import com.songlou.pojo.Rank;

/**
 * �Ự������
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
	 * ��ȡȨ���б�
	 * @return
	 */
	public static List<Rank> getRankListSession(HttpServletRequest request, Admin admin){
		LoginService  loginService = new LoginServiceImpl();
		List<Rank> ranks = loginService.getRankListSession(request, admin);
		return ranks;
	}
}

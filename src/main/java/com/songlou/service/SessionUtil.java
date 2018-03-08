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
	static
	LoginService loginService;
	
	/**
	 * ��ȡ��ǰ��¼�û���Ϣ����cookie��ȡ�ģ�
	 * @param request
	 * @return
	 */
	public static Admin getCurrentAdmin(HttpServletRequest request) {
		try {
			loginService = new LoginServiceImpl();
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
	public static List<Rank> getRankListFromSession(HttpServletRequest request, Admin admin){
		loginService = new LoginServiceImpl();
		List<Rank> ranks = loginService.getRankListSession(request, admin);		
		return ranks;
	}
}

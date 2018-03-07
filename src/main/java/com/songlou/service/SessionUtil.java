package com.songlou.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
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
	public static List<Rank> getRankListSession(HttpServletRequest request, Admin admin){
		loginService = new LoginServiceImpl();
		List<Rank> ranks = loginService.getRankListSession(request, admin);
		
		//java lambda���ʽʹ������
		/*List<Rank> rootRanks = ranks.stream().filter(x -> x.getParentId() == 0).collect(Collectors.toList());
		for(Rank rank : rootRanks){
			List<Rank> tempRanks = ranks.stream().filter(x -> x.getParentId() == rank.getId()).collect(Collectors.toList());
			
			String a = "";
		}*/
		
		return ranks;
	}
	
	/**
	 * ��ȡ���༯��
	 * @param ranks
	 * @param parentId
	 * @return
	 */
	public static List<Rank> getChildRanks(List<Rank> ranks, int parentId){
		List<Rank> childRanks = ranks.stream().filter(x -> x.getParentId() == parentId && x.getLeftShow() == 1).collect(Collectors.toList());
		return childRanks;
	}
	
	/**
	 * ��ȡ��Ȩ��
	 * @param ranks
	 * @return
	 */
	public static List<Rank> getRootRanks(List<Rank> ranks){
		List<Rank> rootRanks = ranks.stream().filter(x -> x.getParentId() == 0 && x.getLeftShow() == 1).collect(Collectors.toList());
		return rootRanks;
	}
}

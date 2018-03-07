package com.songlou.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	static
	LoginService loginService;
	
	/**
	 * 获取当前登录用户信息（从cookie获取的）
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
	 * 获取权限列表
	 * @return
	 */
	public static List<Rank> getRankListSession(HttpServletRequest request, Admin admin){
		loginService = new LoginServiceImpl();
		List<Rank> ranks = loginService.getRankListSession(request, admin);
		
		//java lambda表达式使用例子
		/*List<Rank> rootRanks = ranks.stream().filter(x -> x.getParentId() == 0).collect(Collectors.toList());
		for(Rank rank : rootRanks){
			List<Rank> tempRanks = ranks.stream().filter(x -> x.getParentId() == rank.getId()).collect(Collectors.toList());
			
			String a = "";
		}*/
		
		return ranks;
	}
	
	/**
	 * 获取子类集合
	 * @param ranks
	 * @param parentId
	 * @return
	 */
	public static List<Rank> getChildRanks(List<Rank> ranks, int parentId){
		List<Rank> childRanks = ranks.stream().filter(x -> x.getParentId() == parentId && x.getLeftShow() == 1).collect(Collectors.toList());
		return childRanks;
	}
	
	/**
	 * 获取根权限
	 * @param ranks
	 * @return
	 */
	public static List<Rank> getRootRanks(List<Rank> ranks){
		List<Rank> rootRanks = ranks.stream().filter(x -> x.getParentId() == 0 && x.getLeftShow() == 1).collect(Collectors.toList());
		return rootRanks;
	}
}

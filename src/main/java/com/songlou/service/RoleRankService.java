package com.songlou.service;

import java.util.List;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Rank;
/**
 * 角色-权限关系接口
 * @author sbd04462
 *
 */
public interface RoleRankService {
	/**
	 * 新增
	 * @param admin
	 */
	public ResultHelper insert(int roleId, String rankIds);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public List<Rank> selectRanksByRoleId(int roleId);
	
	/**
	 * 根据角色ID删除
	 * @param ids
	 */
	public ResultHelper deleteByRoleId(int roleId);
}

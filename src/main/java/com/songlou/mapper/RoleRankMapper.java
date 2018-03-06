package com.songlou.mapper;

import java.util.List;
import com.songlou.pojo.Rank;
import com.songlou.pojo.RoleRank;
/**
 * 角色-权限关系
 * @author sbd04462
 *
 */
public interface RoleRankMapper {
	/**
	 * 新增
	 * @param admin
	 */
	public void insert(RoleRank roleRank);
	
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
	public void deleteByRoleId(int roleId);
}

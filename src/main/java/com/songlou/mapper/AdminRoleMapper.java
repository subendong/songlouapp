package com.songlou.mapper;

import java.util.List;
import com.songlou.pojo.AdminRole;
import com.songlou.pojo.Role;

public interface AdminRoleMapper {
	/**
	 * 插入数据
	 * @param adminRole
	 */
	public void insert(AdminRole adminRole);
	
	/**
	 * 根据管理员ID查询
	 * @param adminId
	 * @return
	 */
	public List<Role> selectRolesByAdminId(int adminId);
	
	/**
	 * 根据管理员ID删除
	 */
	public void deleteByAdminId(int adminId);
}

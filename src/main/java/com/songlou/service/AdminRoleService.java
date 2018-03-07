package com.songlou.service;

import java.util.List;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Role;

public interface AdminRoleService {
	/**
	 * 插入数据
	 * @param adminRole
	 */
	public ResultHelper insert(int adminId, String roleIds);
	
	/**
	 * 根据管理员ID查询
	 * @param adminId
	 * @return
	 */
	public List<Role> selectRolesByAdminId(int adminId);
	
	/**
	 * 根据管理员ID删除
	 */
	public ResultHelper deleteByAdminId(int adminId);
}

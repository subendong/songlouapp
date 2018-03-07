package com.songlou.mapper;

import java.util.List;
import com.songlou.pojo.AdminRole;
import com.songlou.pojo.Role;

public interface AdminRoleMapper {
	/**
	 * ��������
	 * @param adminRole
	 */
	public void insert(AdminRole adminRole);
	
	/**
	 * ���ݹ���ԱID��ѯ
	 * @param adminId
	 * @return
	 */
	public List<Role> selectRolesByAdminId(int adminId);
	
	/**
	 * ���ݹ���ԱIDɾ��
	 */
	public void deleteByAdminId(int adminId);
}

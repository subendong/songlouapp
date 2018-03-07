package com.songlou.service;

import java.util.List;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Role;

public interface AdminRoleService {
	/**
	 * ��������
	 * @param adminRole
	 */
	public ResultHelper insert(int adminId, String roleIds);
	
	/**
	 * ���ݹ���ԱID��ѯ
	 * @param adminId
	 * @return
	 */
	public List<Role> selectRolesByAdminId(int adminId);
	
	/**
	 * ���ݹ���ԱIDɾ��
	 */
	public ResultHelper deleteByAdminId(int adminId);
}

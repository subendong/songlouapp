package com.songlou.service;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.songlou.instrument.GlobalHelper;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.AdminRole;
import com.songlou.pojo.Role;
/**
 * ����Ա-��ɫ����
 * @author sbd04462
 *
 */
@Service("adminRoleService")
public class AdminRoleServiceImpl implements AdminRoleService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * ����
	 */
	@Override
	public ResultHelper insert(int adminId, String roleIds) {
		//���roleIds���ʾδѡ���κν�ɫ�����߽�����ѡ�еĽ�ɫ��ȡ����
		if(GlobalHelper.IsNullOrEmpty(roleIds)){
			this.deleteByAdminId(adminId);
			return new ResultHelper(0, true, "success", null);
		}
		
		try {
			//����ǰ��֮ǰ�Ķ�ɾ��
			this.deleteByAdminId(adminId);
			
			List<AdminRole> adminRoles = new ArrayList<>();
			String[] arrRoleId = roleIds.split(",");
			for (int i = 0; i < arrRoleId.length; i++) {
				int roleId = Integer.parseInt(arrRoleId[i]);
				AdminRole adminRole = new AdminRole();
				adminRole.setAdminId(adminId);
				adminRole.setRoleId(roleId);
				adminRoles.add(adminRole);
			}
			//Ϊʲô���������ѭ������룿�Լ�ȥ��ɣ�
			for(AdminRole adminRole : adminRoles){
				sqlSessionTemplate.insert("com.songlou.mapper.AdminRoleMapper.insert", adminRole);
			}
			
			return new ResultHelper(0, true, "success", null);
		} catch (Exception e) {
			return new ResultHelper(1, false, "����ʧ��", null);
		}
	}

	/**
	 * ���ݹ���ԱID��ѯ
	 */
	@Override
	public List<Role> selectRolesByAdminId(int adminId) {
		List<Role> roles = sqlSessionTemplate.selectList("com.songlou.mapper.AdminRoleMapper.selectRolesByAdminId", adminId);
		return roles;
	}

	/**
	 * ���ݹ���ԱIDɾ��
	 */
	@Override
	public ResultHelper deleteByAdminId(int adminId) {
		sqlSessionTemplate.delete("com.songlou.mapper.AdminRoleMapper.deleteByAdminId", adminId);
		return new ResultHelper(0, true, "success", null);
	}
}

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
 * 管理员-角色服务
 * @author sbd04462
 *
 */
@Service("adminRoleService")
public class AdminRoleServiceImpl implements AdminRoleService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 新增
	 */
	@Override
	public ResultHelper insert(int adminId, String roleIds) {
		//如果roleIds则表示未选中任何角色，或者将所有选中的角色都取消了
		if(GlobalHelper.IsNullOrEmpty(roleIds)){
			this.deleteByAdminId(adminId);
			return new ResultHelper(0, true, "success", null);
		}
		
		try {
			//插入前把之前的都删掉
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
			//为什么不在上面的循环里插入？自己去想吧！
			for(AdminRole adminRole : adminRoles){
				sqlSessionTemplate.insert("com.songlou.mapper.AdminRoleMapper.insert", adminRole);
			}
			
			return new ResultHelper(0, true, "success", null);
		} catch (Exception e) {
			return new ResultHelper(1, false, "操作失败", null);
		}
	}

	/**
	 * 根据管理员ID查询
	 */
	@Override
	public List<Role> selectRolesByAdminId(int adminId) {
		List<Role> roles = sqlSessionTemplate.selectList("com.songlou.mapper.AdminRoleMapper.selectRolesByAdminId", adminId);
		return roles;
	}

	/**
	 * 根据管理员ID删除
	 */
	@Override
	public ResultHelper deleteByAdminId(int adminId) {
		sqlSessionTemplate.delete("com.songlou.mapper.AdminRoleMapper.deleteByAdminId", adminId);
		return new ResultHelper(0, true, "success", null);
	}
}

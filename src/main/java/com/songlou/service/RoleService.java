package com.songlou.service;

import java.util.List;

import com.songlou.instrument.ResultHelper;
import com.songlou.model.PagingModel;
import com.songlou.model.RoleSearchModel;
import com.songlou.pojo.Role;

/**
 * 管理员
 * @author sbd04462
 *
 */
public interface RoleService {
	/**
	 * 分页查询
	 * @param searchModel
	 * @return
	 */
	public PagingModel<Role> selectPagingData(RoleSearchModel searchModel);
	
	/**
	 * 新增
	 * @param admin
	 */
	public ResultHelper insert(Role role);
	
	/**
	 * 修改
	 * @param admin
	 */
	public ResultHelper update(Role role);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Role selectById(int id);
	
	/**
	 * 删除
	 * @param ids
	 */
	public void batchDelete(String ids);
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> selectAll();
}

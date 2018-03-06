package com.songlou.mapper;

import java.util.List;
import com.songlou.model.RoleSearchModel;
import com.songlou.pojo.Role;
/**
 * 管理员用户
 * @author sbd04462
 *
 */
public interface RoleMapper {
	/**
	 * 分页查询
	 * @param searchModel
	 * @return
	 */
	public List<Role> selectPagingData1(RoleSearchModel searchModel);
	
	/**
	 * 分页查询满足条件的总数
	 * @param searchModel
	 * @return
	 */
	public int selectPagingDataNumber1(RoleSearchModel searchModel);
	
	/**
	 * 新增
	 * @param admin
	 */
	public void insert(Role role);
	
	/**
	 * 修改
	 * @param admin
	 */
	public void update(Role role);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Role selectById(int id);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void batchDelete(String ids);
}

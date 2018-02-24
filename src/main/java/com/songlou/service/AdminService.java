package com.songlou.service;

import com.songlou.model.AdminSearchModel;
import com.songlou.model.PagingModel;
import com.songlou.pojo.Admin;

/**
 * 管理员
 * @author sbd04462
 *
 */
public interface AdminService {
	/**
	 * 分页查询
	 * @param searchModel
	 * @return
	 */
	public PagingModel<Admin> selectPagingData(AdminSearchModel searchModel);
	
	/**
	 * 新增
	 * @param admin
	 */
	public void insert(Admin admin);
	
	/**
	 * 修改
	 * @param admin
	 */
	public void update(Admin admin);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Admin selectById(int id);
	
	/**
	 * 删除
	 * @param admin
	 */
	public void delete(String ids);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void batchDelete(String ids);
}

package com.songlou.mapper;

import java.util.List;
import com.songlou.model.AdminSearchModel;
import com.songlou.pojo.Admin;
/**
 * 管理员用户
 * @author sbd04462
 *
 */
public interface AdminMapper {
	/**
	 * 分页查询
	 * @param searchModel
	 * @return
	 */
	public List<Admin> selectPagingData1(AdminSearchModel searchModel);
	
	/**
	 * 分页查询满足条件的总数
	 * @param searchModel
	 * @return
	 */
	public int selectPagingDataNumber1(AdminSearchModel searchModel);
	
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
	public void delete(Admin admin);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void batchDelete(String ids);
}

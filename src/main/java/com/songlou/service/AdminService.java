package com.songlou.service;

import com.songlou.model.AdminSearchModel;
import com.songlou.model.PagingModel;
import com.songlou.pojo.Admin;

/**
 * ����Ա
 * @author sbd04462
 *
 */
public interface AdminService {
	/**
	 * ��ҳ��ѯ
	 * @param searchModel
	 * @return
	 */
	public PagingModel<Admin> selectPagingData(AdminSearchModel searchModel);
	
	/**
	 * ����
	 * @param admin
	 */
	public void insert(Admin admin);
	
	/**
	 * �޸�
	 * @param admin
	 */
	public void update(Admin admin);
	
	/**
	 * ����ID��ѯ
	 * @param id
	 * @return
	 */
	public Admin selectById(int id);
	
	/**
	 * ɾ��
	 * @param admin
	 */
	public void delete(String ids);
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void batchDelete(String ids);
}

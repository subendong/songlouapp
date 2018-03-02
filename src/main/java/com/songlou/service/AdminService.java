package com.songlou.service;

import com.songlou.instrument.ResultHelper;
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
	public ResultHelper insert(Admin admin);
	
	/**
	 * �޸�
	 * @param admin
	 */
	public ResultHelper update(Admin admin);
	
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
	
	/**
	 * ���������������ѯ
	 * @param admin
	 * @return
	 */
	public ResultHelper select(Admin admin);
}

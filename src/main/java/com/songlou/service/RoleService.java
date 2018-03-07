package com.songlou.service;

import java.util.List;

import com.songlou.instrument.ResultHelper;
import com.songlou.model.PagingModel;
import com.songlou.model.RoleSearchModel;
import com.songlou.pojo.Role;

/**
 * ����Ա
 * @author sbd04462
 *
 */
public interface RoleService {
	/**
	 * ��ҳ��ѯ
	 * @param searchModel
	 * @return
	 */
	public PagingModel<Role> selectPagingData(RoleSearchModel searchModel);
	
	/**
	 * ����
	 * @param admin
	 */
	public ResultHelper insert(Role role);
	
	/**
	 * �޸�
	 * @param admin
	 */
	public ResultHelper update(Role role);
	
	/**
	 * ����ID��ѯ
	 * @param id
	 * @return
	 */
	public Role selectById(int id);
	
	/**
	 * ɾ��
	 * @param ids
	 */
	public void batchDelete(String ids);
	
	/**
	 * ��ѯ���н�ɫ
	 * @return
	 */
	public List<Role> selectAll();
}

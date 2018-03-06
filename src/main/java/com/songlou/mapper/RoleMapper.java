package com.songlou.mapper;

import java.util.List;
import com.songlou.model.RoleSearchModel;
import com.songlou.pojo.Role;
/**
 * ����Ա�û�
 * @author sbd04462
 *
 */
public interface RoleMapper {
	/**
	 * ��ҳ��ѯ
	 * @param searchModel
	 * @return
	 */
	public List<Role> selectPagingData1(RoleSearchModel searchModel);
	
	/**
	 * ��ҳ��ѯ��������������
	 * @param searchModel
	 * @return
	 */
	public int selectPagingDataNumber1(RoleSearchModel searchModel);
	
	/**
	 * ����
	 * @param admin
	 */
	public void insert(Role role);
	
	/**
	 * �޸�
	 * @param admin
	 */
	public void update(Role role);
	
	/**
	 * ����ID��ѯ
	 * @param id
	 * @return
	 */
	public Role selectById(int id);
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void batchDelete(String ids);
}

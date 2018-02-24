package com.songlou.mapper;

import java.util.List;
import com.songlou.model.AdminSearchModel;
import com.songlou.pojo.Admin;
/**
 * ����Ա�û�
 * @author sbd04462
 *
 */
public interface AdminMapper {
	/**
	 * ��ҳ��ѯ
	 * @param searchModel
	 * @return
	 */
	public List<Admin> selectPagingData1(AdminSearchModel searchModel);
	
	/**
	 * ��ҳ��ѯ��������������
	 * @param searchModel
	 * @return
	 */
	public int selectPagingDataNumber1(AdminSearchModel searchModel);
	
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
	public void delete(Admin admin);
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void batchDelete(String ids);
}

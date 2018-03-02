package com.songlou.service;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songlou.instrument.Md5Helper;
import com.songlou.instrument.ResultHelper;
import com.songlou.model.AdminSearchModel;
import com.songlou.model.PagingModel;
import com.songlou.pojo.Admin;

/**
 * ����Ա������
 * @author sbd04462
 *
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * ��ҳ��ѯ
	 */
	@Override
	public PagingModel<Admin> selectPagingData(AdminSearchModel searchModel) {
		PagingModel<Admin> pagingModel = new PagingModel<Admin>();
		List<Admin> admins = sqlSessionTemplate.selectList("com.songlou.mapper.AdminMapper.selectPagingData", searchModel);
		int number = sqlSessionTemplate.selectOne("com.songlou.mapper.AdminMapper.selectPagingDataNumber", searchModel);
		pagingModel.setDatas(admins);
		pagingModel.setTotalRecord(number);
		pagingModel.setTotalPage((int) Math.ceil((double)number / searchModel.getPageSize()));
		pagingModel.setPageIndex(searchModel.getPageIndex());
		pagingModel.setPageSize(searchModel.getPageSize());

		return pagingModel;
	}

	/**
	 * ����
	 */
	@Override
	public ResultHelper insert(Admin admin) {
		if(admin.getPassword() == null || admin.getPassword().isEmpty()){
			return new ResultHelper(1, false, "���벻��Ϊ��", null);
		}
		
		String password = Md5Helper.MD5(admin.getPassword());
		admin.setPassword(password);
		sqlSessionTemplate.insert("com.songlou.mapper.AdminMapper.insert", admin);
		return new ResultHelper(0, true, "success", null);
	}

	/**
	 * �޸�
	 */
	@Override
	public ResultHelper update(Admin admin) {
		if(admin.getPassword() != null && !admin.getPassword().isEmpty()){
			String password = Md5Helper.MD5(admin.getPassword());
			admin.setPassword(password);
		}
		
		sqlSessionTemplate.update("com.songlou.mapper.AdminMapper.update", admin);
		return new ResultHelper(0, true, "success", null);
	}

	/**
	 * ����ID��ѯ
	 */
	@Override
	public Admin selectById(int id) {
		Admin admin = sqlSessionTemplate.selectOne("com.songlou.mapper.AdminMapper.selectById", id);
		return admin;
	}

	/**
	 * ɾ��
	 * �˷�����Ҫ��η������ݿ⣬һ��һ����ɾ��
	 */
	@Override
	public void delete(String ids) {
		String[] arr = ids.split(",");
		Admin admin = new Admin();
		int id = 0;
		if (arr.length == 1) {
			id = Integer.parseInt(arr[0]);
			admin.setId(id);
			sqlSessionTemplate.delete("com.songlou.mapper.AdminMapper.delete", admin);
		} else if (arr.length > 1) {
			for (int i = 0; i < arr.length; i++) {
				id = Integer.parseInt(arr[i]);
				admin.setId(id);
				sqlSessionTemplate.delete("com.songlou.mapper.AdminMapper.delete", admin);
			}
		}
	}

	/**
	 * ����ɾ��
	 * �˷�������id���ϣ�һ����ɾ��
	 * ��id���������´����������ַ�����Ҫת����������Ŀ����Ϊ�˰�ȫ����Ϊɾ����ʱ������in
	 */
	@Override
	public void batchDelete(String ids) {
		/*String[] arr = ids.split(",");
		List<Integer> lists = new ArrayList<Integer>();
		
		int id = 0;
		if (arr.length == 1) {
			id = Integer.parseInt(arr[0]);
			lists.add(id);
		}
		else if (arr.length > 1) {
			for (int i = 0; i < arr.length; i++) {
				id = Integer.parseInt(arr[i]);
				lists.add(id);
			}
		}
		
		sqlSessionTemplate.delete("com.songlou.mapper.AdminMapper.batchDelete", lists);*/
		
		String[] arr = ids.split(",");
		List<Integer> lists = new ArrayList<Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			int id = Integer.parseInt(arr[i]);
			lists.add(id);
		}
		
		sqlSessionTemplate.delete("com.songlou.mapper.AdminMapper.batchDelete", lists);
	}

	/**
	 * ���������������ѯ
	 */
	@Override
	public ResultHelper select(Admin admin) {
		//����¼��Ϣ�Ƿ���ȷ
		admin = sqlSessionTemplate.selectOne("com.songlou.mapper.AdminMapper.selectLogin", admin);
		if(admin == null){
			return new ResultHelper(1, false, "error", null);
		}
		return new ResultHelper(0, true, "success", null);
	}
}

package com.songlou.service;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public void insert(Admin admin) {
		sqlSessionTemplate.insert("com.songlou.mapper.AdminMapper.insert", admin);
	}

	/**
	 * �޸�
	 */
	@Override
	public void update(Admin admin) {
		sqlSessionTemplate.update("com.songlou.mapper.AdminMapper.update", admin);
	}

	/**
	 * ����ID��ѯ
	 */
	@Override
	public Admin selectById(int id) {
		Admin admin = sqlSessionTemplate.selectOne("com.songlou.mapper.AdminMapper.selectById", id);
		return admin;
	}
}

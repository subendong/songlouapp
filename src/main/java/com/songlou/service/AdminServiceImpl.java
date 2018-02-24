package com.songlou.service;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.songlou.model.AdminSearchModel;
import com.songlou.model.PagingModel;
import com.songlou.pojo.Admin;

/**
 * 管理员服务类
 * @author sbd04462
 *
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 分页查询
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
	 * 新增
	 */
	@Override
	public void insert(Admin admin) {
		sqlSessionTemplate.insert("com.songlou.mapper.AdminMapper.insert", admin);
	}

	/**
	 * 修改
	 */
	@Override
	public void update(Admin admin) {
		sqlSessionTemplate.update("com.songlou.mapper.AdminMapper.update", admin);
	}

	/**
	 * 根据ID查询
	 */
	@Override
	public Admin selectById(int id) {
		Admin admin = sqlSessionTemplate.selectOne("com.songlou.mapper.AdminMapper.selectById", id);
		return admin;
	}

	/**
	 * 删除
	 * 此方法需要多次访问数据库，一条一条的删除
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
	 * 批量删除
	 * 此方法根据id集合，一次性删除
	 * 对id进行了重新处理，不能用字符串，要转换成整数，目的是为了安全，因为删除的时候用了in
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
}

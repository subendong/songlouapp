package com.songlou.service;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.songlou.instrument.ResultHelper;
import com.songlou.model.PagingModel;
import com.songlou.model.RoleSearchModel;
import com.songlou.pojo.Role;

/**
 * 管理员服务类
 * @author sbd04462
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 分页查询
	 */
	@Override
	public PagingModel<Role> selectPagingData(RoleSearchModel searchModel) {
		PagingModel<Role> pagingModel = new PagingModel<Role>();
		List<Role> roles = sqlSessionTemplate.selectList("com.songlou.mapper.RoleMapper.selectPagingData", searchModel);
		int number = sqlSessionTemplate.selectOne("com.songlou.mapper.RoleMapper.selectPagingDataNumber", searchModel);
		pagingModel.setDatas(roles);
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
	public ResultHelper insert(Role role) {
		sqlSessionTemplate.insert("com.songlou.mapper.RoleMapper.insert", role);
		return new ResultHelper(0, true, "success", null);
	}

	/**
	 * 修改
	 */
	@Override
	public ResultHelper update(Role role) {
		sqlSessionTemplate.update("com.songlou.mapper.RoleMapper.update", role);
		return new ResultHelper(0, true, "success", null);
	}

	/**
	 * 根据ID查询
	 */
	@Override
	public Role selectById(int id) {
		Role role = sqlSessionTemplate.selectOne("com.songlou.mapper.RoleMapper.selectById", id);
		return role;
	}

	/**
	 * 批量删除
	 * 此方法根据id集合，一次性删除
	 * 对id进行了重新处理，不能用字符串，要转换成整数，目的是为了安全，因为删除的时候用了in
	 */
	@Override
	public void batchDelete(String ids) {
		String[] arr = ids.split(",");
		List<Integer> lists = new ArrayList<Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			int id = Integer.parseInt(arr[i]);
			lists.add(id);
		}
		
		sqlSessionTemplate.delete("com.songlou.mapper.RoleMapper.batchDelete", lists);
	}
}

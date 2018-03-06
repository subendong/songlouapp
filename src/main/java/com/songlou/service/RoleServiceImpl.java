package com.songlou.service;

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
	 * 删除
	 */
	@Override
	public void batchDelete(String ids) {
		String[] arr = ids.split(",");
		
		for (int i = 0; i < arr.length; i++) {
			int id = Integer.parseInt(arr[i]);
			sqlSessionTemplate.delete("com.songlou.mapper.RoleMapper.delete", id);
			sqlSessionTemplate.delete("com.songlou.mapper.RoleRankMapper.deleteByRoleId", id);
		}
	}
}

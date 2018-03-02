package com.songlou.service;

import java.util.List;

import com.songlou.instrument.ResultHelper;
import com.songlou.model.PagingModel;
import com.songlou.model.RankSearchModel;
import com.songlou.pojo.Rank;

/**
 * 权限
 * @author sbd04462
 *
 */
public interface RankService {
	/**
	 * 新增
	 * @param rank
	 */
	public ResultHelper add(Rank rank);
	
	/**
	 * 修改
	 * @param rank
	 */
	public ResultHelper update(Rank rank);
	
	/**
	 * 分页查询
	 * @param searchModel
	 * @return
	 */
	public PagingModel<Rank> selectPagingData(RankSearchModel searchModel);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Rank selectById(int id);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<Rank> selectAll();
	
	/**
	 * 删除
	 * @param rank
	 */
	public ResultHelper delete(int id);
}

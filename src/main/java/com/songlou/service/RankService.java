package com.songlou.service;

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
	public void insert(Rank rank);
	
	/**
	 * 分页查询
	 * @param searchModel
	 * @return
	 */
	public PagingModel<Rank> selectPagingData(RankSearchModel searchModel);
}

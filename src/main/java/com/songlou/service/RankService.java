package com.songlou.service;

import java.util.HashMap;
import java.util.List;

import com.songlou.model.PagingModel;
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
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PagingModel<Rank> selectPagingData(int pageIndex, int pageSize);
}

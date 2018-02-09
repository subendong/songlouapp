package com.songlou.service;

import java.util.HashMap;
import java.util.List;

import com.songlou.model.PagingModel;
import com.songlou.pojo.Rank;

/**
 * Ȩ��
 * @author sbd04462
 *
 */
public interface RankService {
	/**
	 * ����
	 * @param rank
	 */
	public void insert(Rank rank);
	
	/**
	 * ��ҳ��ѯ
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PagingModel<Rank> selectPagingData(int pageIndex, int pageSize);
}

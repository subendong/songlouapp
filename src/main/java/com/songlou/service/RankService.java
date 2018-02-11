package com.songlou.service;

import com.songlou.model.PagingModel;
import com.songlou.model.RankSearchModel;
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
	 * @param searchModel
	 * @return
	 */
	public PagingModel<Rank> selectPagingData(RankSearchModel searchModel);
}

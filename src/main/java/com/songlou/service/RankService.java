package com.songlou.service;

import java.util.List;

import com.songlou.instrument.ResultHelper;
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
	public ResultHelper add(Rank rank);
	
	/**
	 * �޸�
	 * @param rank
	 */
	public ResultHelper update(Rank rank);
	
	/**
	 * ��ҳ��ѯ
	 * @param searchModel
	 * @return
	 */
	public PagingModel<Rank> selectPagingData(RankSearchModel searchModel);
	
	/**
	 * ����ID��ѯ
	 * @param id
	 * @return
	 */
	public Rank selectById(int id);
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	public List<Rank> selectAll();
	
	/**
	 * ɾ��
	 * @param rank
	 */
	public ResultHelper delete(int id);
}

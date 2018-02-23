package com.songlou.mapper;

import java.util.List;
import com.songlou.model.RankSearchModel;
import com.songlou.pojo.Rank;
/**
 * Ȩ��
 * @author sbd04462
 *
 */
public interface RankMapper {
	/**
	 * ����
	 * @param rank
	 */
	public void insert(Rank rank);
	
	/**
	 * �޸�
	 * @param rank
	 */
	public void update(Rank rank);
	
	/**
	 * ��ҳ��ѯ
	 * @param searchModel
	 * @return
	 */
	public List<Rank> selectPagingData(RankSearchModel searchModel);
	
	/**
	 * ��ҳ��ѯ��������������
	 * @param searchModel
	 * @return
	 */
	public int selectPagingDataNumber(RankSearchModel searchModel);
	
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
	public void delete(Rank rank);
}

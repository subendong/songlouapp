package com.songlou.mapper;

import java.util.HashMap;
import java.util.List;
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
	 * ��ҳ��ѯ
	 * @param hashmap
	 * @return
	 */
	public List<Rank> selectPagingData(HashMap<?, ?> hashmap);
	
	/**
	 * ��ҳ��ѯ��������������
	 * @param hashmap
	 * @return
	 */
	public int selectPagingDataNumber(HashMap<?, ?> hashmap);
}

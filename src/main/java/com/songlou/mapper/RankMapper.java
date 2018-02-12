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
	 * �޸�
	 * @param rank
	 */
	public void update(Rank rank);
	
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
}

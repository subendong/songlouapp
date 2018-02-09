package com.songlou.mapper;

import java.util.HashMap;
import java.util.List;
import com.songlou.pojo.Rank;
/**
 * 权限
 * @author sbd04462
 *
 */
public interface RankMapper {
	/**
	 * 新增
	 * @param rank
	 */
	public void insert(Rank rank);
	
	/**
	 * 分页查询
	 * @param hashmap
	 * @return
	 */
	public List<Rank> selectPagingData(HashMap<?, ?> hashmap);
	
	/**
	 * 分页查询满足条件的总数
	 * @param hashmap
	 * @return
	 */
	public int selectPagingDataNumber(HashMap<?, ?> hashmap);
}

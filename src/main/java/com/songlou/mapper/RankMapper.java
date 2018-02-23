package com.songlou.mapper;

import java.util.List;
import com.songlou.model.RankSearchModel;
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
	 * 修改
	 * @param rank
	 */
	public void update(Rank rank);
	
	/**
	 * 分页查询
	 * @param searchModel
	 * @return
	 */
	public List<Rank> selectPagingData(RankSearchModel searchModel);
	
	/**
	 * 分页查询满足条件的总数
	 * @param searchModel
	 * @return
	 */
	public int selectPagingDataNumber(RankSearchModel searchModel);
	
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
	public void delete(Rank rank);
}

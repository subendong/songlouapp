package com.songlou.service;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songlou.common.ResultHelper;
import com.songlou.model.PagingModel;
import com.songlou.model.RankSearchModel;
import com.songlou.pojo.Rank;

@Service("rankService")
public class RankServiceImpl implements RankService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/*
	 * 新增权限成功之后
	 * 1.需要更新rootId。如果parentId为0，rootId为其本身；如果parentId不为0，rootId位其父类的rootId
	 * 2.需要更新depth（权限深度），如果parentId为0，depth为1；如果parentId不为0，depth位其父类的depth+1
	 * 3.更新同级排序
	 * 4.更新全局排序（同rootId）
	 * 通过以上两点需求，我们不难得出，在插入成功后，必须要查询其父类，然后再做相关处理
	 */
	@Override
	public ResultHelper add(Rank rank) {
		// 调用mybatis插件往数据库插入数据
		sqlSessionTemplate.insert("com.songlou.mapper.RankMapper.insert", rank);
		// 根据parentId，查找出父类
		Rank parentRank = selectById(rank.getParentId());
		// 重新给rootId和depth赋值
		int rootId = rank.getParentId() == 0 ? rank.getId() : parentRank.getRootId();
		int depth = rank.getParentId() == 0 ? 1 : parentRank.getDepth() + 1;
		rank.setRootId(rootId);
		rank.setDepth(depth);
		//重新给innerOrder和outerOrder赋值
		int innerOrder = sqlSessionTemplate.selectOne("com.songlou.mapper.RankMapper.selectMaxInnerOrder", rank.getParentId());
		int outerOrder = sqlSessionTemplate.selectOne("com.songlou.mapper.RankMapper.selectMaxOuterOrder", rank.getRootId());
		rank.setInnerOrder(innerOrder + 1);
		rank.setOuterOrder(outerOrder + 1);		
		sqlSessionTemplate.update("com.songlou.mapper.RankMapper.update", rank);
		
		return new ResultHelper(0, true, "添加成功", null);
	}

	/**
	 * 修改
	 */
	@Override
	public ResultHelper update(Rank rank) {
		//根据rankId获取数据库中的数据，将数据库中的innerOrder和outerOrder赋值给rank
		Rank dbRank = selectById(rank.getId());		
		if(rank.getParentId() != dbRank.getParentId()){
			return new ResultHelper(1, false, "已存在权限不能修改父级权限", null);
		}
		
		// 根据parentId，查找出父类
		Rank parentRank = selectById(rank.getParentId());
		// 重新跟rootId和depth赋值
		int rootId = rank.getParentId() == 0 ? rank.getId() : parentRank.getRootId();
		int depth = rank.getParentId() == 0 ? 1 : parentRank.getDepth() + 1;
		rank.setRootId(rootId);
		rank.setDepth(depth);
		//将数据库中的innerOrder和outerOrder赋值给rank
		rank.setInnerOrder(dbRank.getInnerOrder());
		rank.setOuterOrder(dbRank.getOuterOrder());	
		// 更新数据
		sqlSessionTemplate.update("com.songlou.mapper.RankMapper.update", rank);
		
		return new ResultHelper(0, true, "", null);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PagingModel<Rank> selectPagingData(RankSearchModel searchModel) {
		// 用HashMap也行
		/*
		 * HashMap<String, Object> hm = new HashMap<String, Object>();
		 * hm.put("pageIndex", searchModel.getPageIndex()); hm.put("pageSize",
		 * searchModel.getPageSize()); if(searchModel.getRankName() != null){
		 * hm.put("rankName", searchModel.getRankName()); }
		 */

		PagingModel<Rank> pagingModel = new PagingModel<Rank>();
		List<Rank> ranks = sqlSessionTemplate.selectList("com.songlou.mapper.RankMapper.selectPagingData", searchModel);
		int number = sqlSessionTemplate.selectOne("com.songlou.mapper.RankMapper.selectPagingDataNumber", searchModel);
		pagingModel.setDatas(ranks);
		pagingModel.setTotalRecord(number);
		pagingModel.setTotalPage((int) Math.ceil((double)number / searchModel.getPageSize()));
		pagingModel.setPageIndex(searchModel.getPageIndex());
		pagingModel.setPageSize(searchModel.getPageSize());

		return pagingModel;
	}

	/**
	 * 根据ID查询
	 */
	@Override
	public Rank selectById(int id) {
		Rank rank = sqlSessionTemplate.selectOne("com.songlou.mapper.RankMapper.selectById", id);
		return rank;
	}

	/**
	 * 查询所有数据
	 */
	@Override
	public List<Rank> selectAll() {
		List<Rank> ranks = sqlSessionTemplate.selectList("com.songlou.mapper.RankMapper.selectAll");
		return ranks;
	}

	/**
	 * 删除
	 * 删除后需要重新对同级的权限和同rootId的权限重新排序
	 * @param rank
	 */
	@Override
	public void delete(int id) {
		Rank rank = selectById(id);
		//对排序大于该记录的同级权限的排序做-1处理
		sqlSessionTemplate.update("com.songlou.mapper.RankMapper.updateInnerOrder", rank);
		//对排序大于该记录的同rootId权限的排序做-1处理
		sqlSessionTemplate.update("com.songlou.mapper.RankMapper.updateOuterOrder", rank);
		//最后做删除操作
		sqlSessionTemplate.delete("com.songlou.mapper.RankMapper.delete", rank);
	}
}

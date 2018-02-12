package com.songlou.service;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	 * 通过以上两点需求，我们不难得出，在插入成功后，必须要查询其父类，然后再做相关处理
	 */
	@Override
	public void insert(Rank rank) {
		//调用mybatis插件往数据库插入数据
		sqlSessionTemplate.insert("insert", rank);
		//根据parentId，查找出父类
		Rank parentRank = selectById(rank.getParentId());
		//重新跟rootId和depth赋值
		int rootId = rank.getParentId() == 0 ? rank.getId() : parentRank.getRootId();
		int depth = rank.getParentId() == 0 ? 1 : parentRank.getDepth() + 1;
		rank.setRootId(rootId);
		rank.setDepth(depth);
		sqlSessionTemplate.update("update", rank);
	}
	
	/**
	 * 修改
	 */
	@Override
	public void update(Rank rank){
		//根据parentId，查找出父类
		Rank parentRank = selectById(rank.getParentId());
		//重新跟rootId和depth赋值
		int rootId = rank.getParentId() == 0 ? rank.getId() : parentRank.getRootId();
		int depth = rank.getParentId() == 0 ? 1 : parentRank.getDepth() + 1;
		rank.setRootId(rootId);
		rank.setDepth(depth);
		//更新数据
		sqlSessionTemplate.update("update", rank);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PagingModel<Rank> selectPagingData(RankSearchModel searchModel) {
		//用HashMap也行
/*		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("pageIndex", searchModel.getPageIndex());
		hm.put("pageSize", searchModel.getPageSize());
		if(searchModel.getRankName() != null){
			hm.put("rankName", searchModel.getRankName());
		}*/
		
		PagingModel<Rank> pagingModel = new PagingModel<Rank>();
		List<Rank> ranks = sqlSessionTemplate.selectList("selectPagingData", searchModel);
		int number = sqlSessionTemplate.selectOne("selectPagingDataNumber", searchModel);
		pagingModel.setDatas(ranks);
		pagingModel.setTotalRecord(number);
		pagingModel.setTotalPage((int)Math.ceil(number / searchModel.getPageSize()));
		pagingModel.setPageIndex(searchModel.getPageIndex());
		pagingModel.setPageSize(searchModel.getPageSize());
		
		return pagingModel;
	}

	
	/**
	 * 根据ID查询
	 */
	@Override
	public Rank selectById(int id) {
		Rank rank = sqlSessionTemplate.selectOne("selectById", id);
		return rank;
	}
	
	/**
	 * 查询所有数据
	 */
	public List<Rank> selectAll(){
		List<Rank> ranks = sqlSessionTemplate.selectList("selectAll");
		return ranks;
	}
}

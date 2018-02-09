package com.songlou.service;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songlou.model.PagingModel;
import com.songlou.pojo.Rank;

@Service("rankService")
public class RankServiceImpl implements RankService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	/*
	 * 新增权限成功之后
	 * 1.需要更新rootId。如果parentId为0，rootId为其本身；如果parentId不为0，rootId位其父类的rootId
	 * 2.需要更新depth（权限深度），如果parentId为0，depth为1；如果parentId不为0，depth位其父类的depth+1
	 * 通过以上两点需求，我们不难得出，在插入成功后，必须要查询其父类，然后再做相关处理
	 */
	public void insert(Rank rank) {
		sqlSessionTemplate.insert("insert", rank);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PagingModel<Rank> selectPagingData(int pageIndex, int pageSize) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put("pageIndex", pageIndex);
		hm.put("pageSize", pageSize);
		
		PagingModel<Rank> pagingModel = new PagingModel<>();
		List<Rank> ranks = sqlSessionTemplate.selectList("selectPagingData", hm);
		int number = sqlSessionTemplate.selectOne("selectPagingDataNumber", hm);
		pagingModel.setDatas(ranks);
		pagingModel.setTotalRecord(number);
		pagingModel.setTotalPage((int)Math.ceil(number / pageSize));
		pagingModel.setPageIndex(pageIndex);
		pagingModel.setPageSize(pageSize);
		
		return pagingModel;
	}
}

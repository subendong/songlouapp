package com.songlou.service;

import java.util.HashMap;
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

	@Override
	/*
	 * ����Ȩ�޳ɹ�֮��
	 * 1.��Ҫ����rootId�����parentIdΪ0��rootIdΪ�䱾�����parentId��Ϊ0��rootIdλ�丸���rootId
	 * 2.��Ҫ����depth��Ȩ����ȣ������parentIdΪ0��depthΪ1�����parentId��Ϊ0��depthλ�丸���depth+1
	 * ͨ�����������������ǲ��ѵó����ڲ���ɹ��󣬱���Ҫ��ѯ�丸�࣬Ȼ��������ش���
	 */
	public void insert(Rank rank) {
		sqlSessionTemplate.insert("insert", rank);
	}

	/**
	 * ��ҳ��ѯ
	 */
	@Override
	public PagingModel<Rank> selectPagingData(RankSearchModel searchModel) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("pageIndex", searchModel.getPageIndex());
		hm.put("pageSize", searchModel.getPageSize());
		if(searchModel.getRankName() != null){
			hm.put("rankName", searchModel.getRankName());
		}
		
		PagingModel<Rank> pagingModel = new PagingModel<>();
		List<Rank> ranks = sqlSessionTemplate.selectList("selectPagingData", hm);
		int number = sqlSessionTemplate.selectOne("selectPagingDataNumber", hm);
		pagingModel.setDatas(ranks);
		pagingModel.setTotalRecord(number);
		pagingModel.setTotalPage((int)Math.ceil(number / searchModel.getPageSize()));
		pagingModel.setPageIndex(searchModel.getPageIndex());
		pagingModel.setPageSize(searchModel.getPageSize());
		
		return pagingModel;
	}
}

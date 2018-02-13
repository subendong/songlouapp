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
	 * ����Ȩ�޳ɹ�֮��
	 * 1.��Ҫ����rootId�����parentIdΪ0��rootIdΪ�䱾�����parentId��Ϊ0��rootIdλ�丸���rootId
	 * 2.��Ҫ����depth��Ȩ����ȣ������parentIdΪ0��depthΪ1�����parentId��Ϊ0��depthλ�丸���depth+1
	 * ͨ�����������������ǲ��ѵó����ڲ���ɹ��󣬱���Ҫ��ѯ�丸�࣬Ȼ��������ش���
	 */
	@Override
	public void insert(Rank rank) {
		// ����mybatis��������ݿ��������
		sqlSessionTemplate.insert("insert", rank);
		// ����parentId�����ҳ�����
		Rank parentRank = selectById(rank.getParentId());
		// ���¸�rootId��depth��ֵ
		int rootId = rank.getParentId() == 0 ? rank.getId() : parentRank.getRootId();
		int depth = rank.getParentId() == 0 ? 1 : parentRank.getDepth() + 1;
		rank.setRootId(rootId);
		rank.setDepth(depth);
		sqlSessionTemplate.update("update", rank);
	}

	/**
	 * �޸�
	 */
	@Override
	public void update(Rank rank) {
		// ����parentId�����ҳ�����
		Rank parentRank = selectById(rank.getParentId());
		// ���¸�rootId��depth��ֵ
		int rootId = rank.getParentId() == 0 ? rank.getId() : parentRank.getRootId();
		int depth = rank.getParentId() == 0 ? 1 : parentRank.getDepth() + 1;
		rank.setRootId(rootId);
		rank.setDepth(depth);
		// ��������
		sqlSessionTemplate.update("update", rank);
	}

	/**
	 * ��ҳ��ѯ
	 */
	@Override
	public PagingModel<Rank> selectPagingData(RankSearchModel searchModel) {
		// ��HashMapҲ��
		/*
		 * HashMap<String, Object> hm = new HashMap<String, Object>();
		 * hm.put("pageIndex", searchModel.getPageIndex()); hm.put("pageSize",
		 * searchModel.getPageSize()); if(searchModel.getRankName() != null){
		 * hm.put("rankName", searchModel.getRankName()); }
		 */

		PagingModel<Rank> pagingModel = new PagingModel<Rank>();
		List<Rank> ranks = sqlSessionTemplate.selectList("selectPagingData", searchModel);
		int number = sqlSessionTemplate.selectOne("selectPagingDataNumber", searchModel);
		pagingModel.setDatas(ranks);
		pagingModel.setTotalRecord(number);
		pagingModel.setTotalPage((int) Math.ceil(number / searchModel.getPageSize()));
		pagingModel.setPageIndex(searchModel.getPageIndex());
		pagingModel.setPageSize(searchModel.getPageSize());

		return pagingModel;
	}

	/**
	 * ����ID��ѯ
	 */
	@Override
	public Rank selectById(int id) {
		Rank rank = sqlSessionTemplate.selectOne("selectById", id);
		return rank;
	}

	/**
	 * ��ѯ��������
	 */
	@Override
	public List<Rank> selectAll() {
		List<Rank> ranks = sqlSessionTemplate.selectList("selectAll");
		return ranks;
	}

	/**
	 * ɾ��
	 * 
	 * @param rank
	 */
	@Override
	public void delete(String ids) {
		String[] arr = ids.split(",");
		Rank rank = new Rank();
		int id = 0;
		int result = 0;
		if (arr.length == 1) {
			id = Integer.parseInt(arr[0]);
			rank.setId(id);
			result = sqlSessionTemplate.delete("delete", rank);
		} else if (arr.length > 1) {
			for (int i = 0; i < arr.length; i++) {
				id = Integer.parseInt(arr[i]);
				rank.setId(id);
				result = sqlSessionTemplate.delete("delete", rank);
			}
		}
	}
}

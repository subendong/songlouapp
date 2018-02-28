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
	 * ����Ȩ�޳ɹ�֮��
	 * 1.��Ҫ����rootId�����parentIdΪ0��rootIdΪ�䱾�����parentId��Ϊ0��rootIdλ�丸���rootId
	 * 2.��Ҫ����depth��Ȩ����ȣ������parentIdΪ0��depthΪ1�����parentId��Ϊ0��depthλ�丸���depth+1
	 * 3.����ͬ������
	 * 4.����ȫ������ͬrootId��
	 * ͨ�����������������ǲ��ѵó����ڲ���ɹ��󣬱���Ҫ��ѯ�丸�࣬Ȼ��������ش���
	 */
	@Override
	public ResultHelper add(Rank rank) {
		// ����mybatis��������ݿ��������
		sqlSessionTemplate.insert("com.songlou.mapper.RankMapper.insert", rank);
		// ����parentId�����ҳ�����
		Rank parentRank = selectById(rank.getParentId());
		// ���¸�rootId��depth��ֵ
		int rootId = rank.getParentId() == 0 ? rank.getId() : parentRank.getRootId();
		int depth = rank.getParentId() == 0 ? 1 : parentRank.getDepth() + 1;
		rank.setRootId(rootId);
		rank.setDepth(depth);
		//���¸�innerOrder��outerOrder��ֵ
		int innerOrder = sqlSessionTemplate.selectOne("com.songlou.mapper.RankMapper.selectMaxInnerOrder", rank.getParentId());
		int outerOrder = sqlSessionTemplate.selectOne("com.songlou.mapper.RankMapper.selectMaxOuterOrder", rank.getRootId());
		rank.setInnerOrder(innerOrder + 1);
		rank.setOuterOrder(outerOrder + 1);		
		sqlSessionTemplate.update("com.songlou.mapper.RankMapper.update", rank);
		
		return new ResultHelper(0, true, "��ӳɹ�", null);
	}

	/**
	 * �޸�
	 */
	@Override
	public ResultHelper update(Rank rank) {
		//����rankId��ȡ���ݿ��е����ݣ������ݿ��е�innerOrder��outerOrder��ֵ��rank
		Rank dbRank = selectById(rank.getId());		
		if(rank.getParentId() != dbRank.getParentId()){
			return new ResultHelper(1, false, "�Ѵ���Ȩ�޲����޸ĸ���Ȩ��", null);
		}
		
		// ����parentId�����ҳ�����
		Rank parentRank = selectById(rank.getParentId());
		// ���¸�rootId��depth��ֵ
		int rootId = rank.getParentId() == 0 ? rank.getId() : parentRank.getRootId();
		int depth = rank.getParentId() == 0 ? 1 : parentRank.getDepth() + 1;
		rank.setRootId(rootId);
		rank.setDepth(depth);
		//�����ݿ��е�innerOrder��outerOrder��ֵ��rank
		rank.setInnerOrder(dbRank.getInnerOrder());
		rank.setOuterOrder(dbRank.getOuterOrder());	
		// ��������
		sqlSessionTemplate.update("com.songlou.mapper.RankMapper.update", rank);
		
		return new ResultHelper(0, true, "", null);
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
	 * ����ID��ѯ
	 */
	@Override
	public Rank selectById(int id) {
		Rank rank = sqlSessionTemplate.selectOne("com.songlou.mapper.RankMapper.selectById", id);
		return rank;
	}

	/**
	 * ��ѯ��������
	 */
	@Override
	public List<Rank> selectAll() {
		List<Rank> ranks = sqlSessionTemplate.selectList("com.songlou.mapper.RankMapper.selectAll");
		return ranks;
	}

	/**
	 * ɾ��
	 * ɾ������Ҫ���¶�ͬ����Ȩ�޺�ͬrootId��Ȩ����������
	 * @param rank
	 */
	@Override
	public void delete(int id) {
		Rank rank = selectById(id);
		//��������ڸü�¼��ͬ��Ȩ�޵�������-1����
		sqlSessionTemplate.update("com.songlou.mapper.RankMapper.updateInnerOrder", rank);
		//��������ڸü�¼��ͬrootIdȨ�޵�������-1����
		sqlSessionTemplate.update("com.songlou.mapper.RankMapper.updateOuterOrder", rank);
		//�����ɾ������
		sqlSessionTemplate.delete("com.songlou.mapper.RankMapper.delete", rank);
	}
}

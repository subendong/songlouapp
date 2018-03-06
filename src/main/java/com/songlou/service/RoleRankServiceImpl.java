package com.songlou.service;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.songlou.instrument.GlobalHelper;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Rank;
import com.songlou.pojo.RoleRank;
/**
 * ��ɫ-Ȩ�޹�ϵ����
 * @author sbd04462
 *
 */
@Service("roleRankService")
public class RoleRankServiceImpl implements RoleRankService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * ����
	 */
	@Override
	public ResultHelper insert(int roleId, String rankIds) {
		if(GlobalHelper.IsNullOrEmpty(rankIds)){
			return new ResultHelper(1, false, "����ѡ��Ȩ��", null);
		}
		
		try {
			//����ǰ��֮ǰ�Ķ�ɾ��
			sqlSessionTemplate.delete("com.songlou.mapper.RoleRankMapper.deleteByRoleId", roleId);
			
			List<RoleRank> roleRanks = new ArrayList<>();
			String[] arrRankId = rankIds.split(",");
			for (int i = 0; i < arrRankId.length; i++) {
				int rankId = Integer.parseInt(arrRankId[i]);
				RoleRank roleRank = new RoleRank();
				roleRank.setRoleId(roleId);
				roleRank.setRankId(rankId);
				roleRanks.add(roleRank);
			}
			//Ϊʲô���������ѭ������룿�Լ�ȥ��ɣ�
			for(RoleRank roleRank : roleRanks){
				sqlSessionTemplate.insert("com.songlou.mapper.RoleRankMapper.insert", roleRank);
			}
			
			return new ResultHelper(0, true, "success", null);
		} catch (Exception e) {
			return new ResultHelper(1, false, "����ʧ��", null);
		}
	}
	
	/**
	 * ���ݽ�ɫID��ѯ
	 */
	@Override
	public List<Rank> selectRanksByRoleId(int roleId) {
		List<Rank> ranks = sqlSessionTemplate.selectList("com.songlou.mapper.RoleRankMapper.selectRanksByRoleId", roleId);
		return ranks;
	}
	
	/**
	 * ���ݽ�ɫIDɾ��
	 */
	@Override
	public ResultHelper deleteByRoleId(int roleId) {
		sqlSessionTemplate.delete("com.songlou.mapper.RoleRankMapper.deleteByRoleId", roleId);
		return new ResultHelper(0, true, "success", null);
	}
}

package com.songlou.mapper;

import java.util.List;
import com.songlou.pojo.Rank;
import com.songlou.pojo.RoleRank;
/**
 * ��ɫ-Ȩ�޹�ϵ
 * @author sbd04462
 *
 */
public interface RoleRankMapper {
	/**
	 * ����
	 * @param admin
	 */
	public void insert(RoleRank roleRank);
	
	/**
	 * ����ID��ѯ
	 * @param id
	 * @return
	 */
	public List<Rank> selectRanksByRoleId(int roleId);
	
	/**
	 * ���ݽ�ɫIDɾ��
	 * @param ids
	 */
	public void deleteByRoleId(int roleId);
}

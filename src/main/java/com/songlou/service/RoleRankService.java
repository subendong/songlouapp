package com.songlou.service;

import java.util.List;
import com.songlou.instrument.ResultHelper;
import com.songlou.pojo.Rank;
/**
 * ��ɫ-Ȩ�޹�ϵ�ӿ�
 * @author sbd04462
 *
 */
public interface RoleRankService {
	/**
	 * ����
	 * @param admin
	 */
	public ResultHelper insert(int roleId, String rankIds);
	
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
	public ResultHelper deleteByRoleId(int roleId);
}

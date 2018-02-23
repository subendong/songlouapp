package com.songlou.model;

/**
 * 权限搜索类
 * @author sbd04462
 *
 */
public class RankSearchModel extends BaseSearchModel{
	private String rankName;//权限名称
	
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
}

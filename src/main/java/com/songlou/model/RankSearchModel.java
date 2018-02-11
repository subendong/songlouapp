package com.songlou.model;

/**
 * È¨ÏÞËÑË÷Àà
 * @author sbd04462
 *
 */
public class RankSearchModel {
	private int pageSize;
	private String rankName;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	@Override
	public String toString() {
		return "RankSearchModel [pageSize=" + pageSize + ", rankName=" + rankName + "]";
	}
}

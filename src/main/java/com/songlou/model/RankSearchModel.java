package com.songlou.model;

/**
 * 权限搜索类
 * @author sbd04462
 *
 */
public class RankSearchModel {
	private int pageIndex;//当前页码
	private int pageSize;//每页大小
	private String rankName;//权限名称
	
	public int getPageIndex() {
		if(pageIndex < 1){
			pageIndex = 1;
		}
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
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
}

package com.songlou.model;

/**
 * Ȩ��������
 * @author sbd04462
 *
 */
public class RankSearchModel {
	private int pageIndex;//��ǰҳ��
	private int pageSize;//ÿҳ��С
	private String rankName;//Ȩ������
	
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

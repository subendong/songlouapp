package com.songlou.model;

/**
 * ��������
 * @author sbd04462
 *
 */
public class BaseSearchModel {
	private int pageIndex;//��ǰҳ��
	private int pageSize;//ÿҳ��С
	
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
}

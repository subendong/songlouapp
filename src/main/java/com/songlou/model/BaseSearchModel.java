package com.songlou.model;

/**
 * 搜索基类
 * @author sbd04462
 *
 */
public class BaseSearchModel {
	private int pageIndex;//当前页码
	private int pageSize;//每页大小
	
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

package com.songlou.model;

import java.util.List;
import java.util.Map;

/*
 * ��ҳ��
 * �ձ���
 * 2018-2-8 17:44:44
 */
public class PagingModel<T> {
	//ҳ��
	private int pageIndex;
	//ÿҳ��С
	private int pageSize;
	//�ܼ�¼��
	private int totalRecord;
	//��ҳ��
	private int totalPage;
	//��ҳ���ص�����
	private List<T> datas;
	
	public int getPageIndex() {
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
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	@Override
	public String toString() {
		return "PagingModel [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", totalRecord=" + totalRecord
				+ ", totalPage=" + totalPage + ", datas=" + datas + "]";
	}
	
}
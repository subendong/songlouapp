package com.songlou.pojo;

public class Rank {
	private int id;
	private int rootId;//��ID
	private int parentId;//��ID
	private String rankName;//Ȩ������
	private String controller;//����������
	private String action;//action����
	private int leftShow;//�Ƿ�����߲˵�����ʾ
	private int showOrder;//��ʾ˳��
	private int depth;//��ȣ�����Ȩ�ޣ�
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRootId() {
		return rootId;
	}
	public void setRootId(int rootId) {
		this.rootId = rootId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getController() {
		return controller;
	}
	public void setController(String control) {
		this.controller = control;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getLeftShow() {
		return leftShow;
	}
	public void setLeftShow(int leftShow) {
		this.leftShow = leftShow;
	}
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	@Override
	public String toString() {
		return "Rank [id=" + id + ", rootId=" + rootId + ", parentId=" + parentId + ", rankName=" + rankName
				+ ", control=" + controller + ", action=" + action + ", leftShow=" + leftShow + ", showOrder=" + showOrder
				+ ", depth=" + depth + "]";
	}
}

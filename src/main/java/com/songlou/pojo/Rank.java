package com.songlou.pojo;

public class Rank {
	private int id;
	private int rootId;//��ID
	private int parentId;//��ID
	private String rankName;//Ȩ������
	private String controller;//����������
	private String action;//action����
	private int leftShow;//�Ƿ�����߲˵�����ʾ
	private int innerOrder;//ͬ������ԽСԽ��ǰ
	private int outerOrder;//ͬrootId����ԽСԽ��ǰ
	private int depth;//��ȣ�����Ȩ�ޣ�
	private int child;//��Ȩ������
	
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
	public void setController(String controller) {
		this.controller = controller;
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
	public int getInnerOrder() {
		return innerOrder;
	}
	public void setInnerOrder(int innerOrder) {
		this.innerOrder = innerOrder;
	}
	public int getOuterOrder() {
		return outerOrder;
	}
	public void setOuterOrder(int outerOrder) {
		this.outerOrder = outerOrder;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getChild() {
		return child;
	}
	public void setChild(int child) {
		this.child = child;
	}
	
	@Override
	public String toString() {
		return "Rank [id=" + id + ", rootId=" + rootId + ", parentId=" + parentId + ", rankName=" + rankName
				+ ", controller=" + controller + ", action=" + action + ", leftShow=" + leftShow + ", innerOrder="
				+ innerOrder + ", outerOrder=" + outerOrder + ", depth=" + depth + "]";
	}
	
	@Override
	public boolean equals(Object obj) {  
    	Rank rank = (Rank) obj;
    	return this.id == rank.id;
	}
}

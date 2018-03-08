package com.songlou.pojo;

public class Rank {
	private int id;
	private int rootId;//根ID
	private int parentId;//父ID
	private String rankName;//权限名称
	private String controller;//控制器名称
	private String action;//action名称
	private int leftShow;//是否在左边菜单栏显示
	private int innerOrder;//同级排序，越小越靠前
	private int outerOrder;//同rootId排序，越小越靠前
	private int depth;//深度（几级权限）
	private int child;//子权限数量
	
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

package com.songlou.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.songlou.pojo.Rank;

public class LeftMenuService {
	
	/**
	 * 获取子类集合
	 * @param ranks
	 * @param parentId
	 * @return
	 */
	private static List<Rank> getChildRanks(List<Rank> ranks, int parentId){
		List<Rank> childRanks = ranks.stream().filter(x -> x.getParentId() == parentId && x.getLeftShow() == 1).collect(Collectors.toList());
		return childRanks;
	}
	
	/**
	 * 获取根权限
	 * @param ranks
	 * @return
	 */
	private static List<Rank> getRootRanks(List<Rank> ranks){
		List<Rank> rootRanks = ranks.stream().filter(x -> x.getParentId() == 0 && x.getLeftShow() == 1).collect(Collectors.toList());
		return rootRanks;
	}
	
	/**
	 * 获取根导航的html，
	 * @param ranks
	 * @param request
	 * @return
	 */
	public static String getMenuHtml(List<Rank> ranks, HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class=\"nav side-menu\">");
		List<Rank> rootRanks = getRootRanks(ranks);
		for(Rank rank : rootRanks){
			sb.append("<li><a> " + rank.getRankName() + " <span class=\"fa fa-chevron-down\"></span></a>");
			sb.append(getChildHtml(rank, ranks, request));
			sb.append("</li>");
		}
		sb.append("</ul>");
		
		return sb.toString();
	}
	
	/**
	 * 递归获取子导航的html
	 * @param rank当前权限
	 * @param ranks所有权限
	 * @param request
	 * @return
	 */
	private static String getChildHtml(Rank rank, List<Rank> ranks, HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class=\"nav child_menu\">");
		List<Rank> childRanks = LeftMenuService.getChildRanks(ranks, rank.getId());
		for(Rank childRank : childRanks){
			//别删，如果想无限展示权限级别，这段代码可用
			/*if(childRank.getChild() > 0){
				sb.append("<li>");
				sb.append("<a>" + childRank.getRankName() +"<span class=\"fa fa-chevron-down\"></span></a>");
				sb.append(getChildHtml(childRank, ranks, request));
				sb.append("</li>");
			}
			else{
				sb.append("<li><a href=\"" + request.getContextPath() + "/" + childRank.getController() + "/" + childRank.getAction() + "\">" + childRank.getRankName() + "</a></li>");
			}*/
			sb.append("<li><a href=\"" + request.getContextPath() + "/" + childRank.getController() + "/" + childRank.getAction() + "\">" + childRank.getRankName() + "</a></li>");
		}
		sb.append("</ul>");
		
		return sb.toString();
	}
}

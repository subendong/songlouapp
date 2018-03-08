package com.songlou.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.songlou.pojo.Rank;

public class LeftMenuService {
	
	/**
	 * ��ȡ���༯��
	 * @param ranks
	 * @param parentId
	 * @return
	 */
	private static List<Rank> getChildRanks(List<Rank> ranks, int parentId){
		List<Rank> childRanks = ranks.stream().filter(x -> x.getParentId() == parentId && x.getLeftShow() == 1).collect(Collectors.toList());
		return childRanks;
	}
	
	/**
	 * ��ȡ��Ȩ��
	 * @param ranks
	 * @return
	 */
	private static List<Rank> getRootRanks(List<Rank> ranks){
		List<Rank> rootRanks = ranks.stream().filter(x -> x.getParentId() == 0 && x.getLeftShow() == 1).collect(Collectors.toList());
		return rootRanks;
	}
	
	/**
	 * ��ȡ��������html��
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
	 * �ݹ��ȡ�ӵ�����html
	 * @param rank��ǰȨ��
	 * @param ranks����Ȩ��
	 * @param request
	 * @return
	 */
	private static String getChildHtml(Rank rank, List<Rank> ranks, HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class=\"nav child_menu\">");
		List<Rank> childRanks = LeftMenuService.getChildRanks(ranks, rank.getId());
		for(Rank childRank : childRanks){
			//��ɾ�����������չʾȨ�޼�����δ������
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

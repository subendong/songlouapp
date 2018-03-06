package com.songlou.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songlou.annotation.NeedLogin;
import com.songlou.instrument.ResultHelper;
import com.songlou.model.RankTreeModel;
import com.songlou.pojo.Rank;
import com.songlou.service.RankService;
import com.songlou.service.RoleRankService;

/**
 * 
 * @author sbd04462
 *
 */
@Controller
@RequestMapping("/rolerank")
public class RoleRankController {
	@Autowired
    private RankService rankService;
	@Autowired
    private RoleRankService roleRankService;
	
	/**
	 * 新增-保存
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@ResponseBody
	@RequestMapping(value = "/set", method=RequestMethod.POST)
    public ResultHelper insert(int roleId, String rankIds){
		ResultHelper resultHelper = roleRankService.insert(roleId, rankIds);
    	return resultHelper;
	}

	/**
	 * 权限树
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/tree")
	public ModelAndView tree(int roleId){
		List<Rank> ranks = rankService.selectAll();
		List<Rank> selectedRanks = roleRankService.selectRanksByRoleId(roleId);
		List<RankTreeModel> rankTrees = this.getRankTrees(ranks, selectedRanks);
		
		String strRankTrees = "";
		try {
			strRankTrees = new ObjectMapper().writeValueAsString(rankTrees);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("rolerank/tree");
		mav.addObject("strRankTrees", strRankTrees);
		mav.addObject("roleId", roleId);
		
        return mav;
	}
	
	/**
	 * 私有方法-将权限列表转化成视图需要的集合
	 * @param ranks
	 * @return
	 */
	private List<RankTreeModel> getRankTrees(List<Rank> ranks, List<Rank> selectedRanks){
		List<RankTreeModel> rankTrees = new ArrayList<RankTreeModel>();
		for(Rank rank : ranks){
			RankTreeModel rankTree = new RankTreeModel();
			rankTree.setId(rank.getId());
			rankTree.setpId(rank.getParentId());
			rankTree.setName(rank.getRankName());
			rankTree.setOpen(false);
			if(this.contains(selectedRanks, rank)){
				rankTree.setChecked(true);
			}
			rankTrees.add(rankTree);
		}
		
		return rankTrees;
	}
	
	/**
	 * 是否包含指定项
	 * @param selectedRanks以获取的所有权限
	 * @param rank某个权限
	 * @return
	 */
	private boolean contains(List<Rank> ranks, Rank rank){
		for(Rank r : ranks){
			if(r.getId() == rank.getId()){
				return true;
			}
		}
		return false;
	}
}

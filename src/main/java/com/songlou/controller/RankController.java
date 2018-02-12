package com.songlou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.songlou.model.PagingModel;
import com.songlou.model.RankSearchModel;
import com.songlou.pojo.Rank;
import com.songlou.service.RankService;

@Controller
@RequestMapping("/rank")
public class RankController {
	@Autowired
    private RankService rankService;

	/**
	 * 新增页面-视图
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(){
		List<Rank> ranks = rankService.selectAll();
		ModelAndView mav = new ModelAndView("rank/add");
		mav.addObject("ranks", ranks);
        return mav;
	}
	
	/**
	 * 新增页面-保存
	 * @param rank
	 * @return
	 */
	@RequestMapping(value = "/add", method=RequestMethod.POST)
    public @ResponseBody String insert(Rank rank){
/*		Rank rank = new Rank();
		rank.setParentId(0);
		rank.setRankName("测试");
		rank.setController("rank");
		rank.setAction("insert");
		rank.setLeftShow(1);
		rank.setShowOrder(1);
		rank.setDepth(1);*/
		
		//验证先不加，到时候用模型绑定的验证来处理
		
		
		rankService.insert(rank);

    	return Integer.toString(rank.getId());
    }
	
	/**
	 * 管理首页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("rank/index");
        return mav;
	}

	/**
	 * 异步获取列表
	 * 请求参数获取的几种方法 ：https://www.cnblogs.com/xiaoxi/p/5695783.html
	 */
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	public ModelAndView list(RankSearchModel searchModel){
		searchModel.setPageSize(15);
		PagingModel<Rank> pagingModel = rankService.selectPagingData(searchModel);
        ModelAndView mav = new ModelAndView("rank/list");
        mav.addObject("pagingModel", pagingModel);
        
        return mav;
	}
}

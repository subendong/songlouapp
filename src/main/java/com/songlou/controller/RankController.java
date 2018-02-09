package com.songlou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.songlou.model.PagingModel;
import com.songlou.pojo.Rank;
import com.songlou.service.RankService;

import ch.qos.logback.classic.spi.PackagingDataCalculator;

@Controller
@RequestMapping("/rank")
public class RankController {
	@Autowired
    private RankService rankService;

	@RequestMapping("/insert")
    public @ResponseBody String test(){
		Rank rank = new Rank();
		rank.setParentId(0);
		rank.setRankName("≤‚ ‘");
		rank.setControl("rank");
		rank.setAction("insert");
		rank.setLeftShow(1);
		rank.setShowOrder(1);
		rank.setDepth(1);
		
		rankService.insert(rank);

    	return Integer.toString(rank.getId());
    }

	/**
	 * ¡–±Ì
	 */
	@RequestMapping("/list")
	public ModelAndView list(){
		int pageIndex = 1;
		int pageSize = 15;
		
		PagingModel<Rank> pagingModel = rankService.selectPagingData(pageIndex, pageSize);
        ModelAndView mav = new ModelAndView("rank");
        mav.addObject("pageingModel", pagingModel);
        
        return mav;
	}
}

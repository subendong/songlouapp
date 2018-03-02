package com.songlou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.songlou.annotation.NeedLogin;
import com.songlou.instrument.ResultHelper;
import com.songlou.model.PagingModel;
import com.songlou.model.RankSearchModel;
import com.songlou.pojo.Rank;
import com.songlou.service.RankService;

/**
 * http://localhost:8080/songlouapp/rank/index
 * @author sbd04462
 *
 */
@Controller
@RequestMapping("/rank")
public class RankController {
	@Autowired
    private RankService rankService;

	/**
	 * ����-��ͼ
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/add")
	public ModelAndView add(){
		Rank rank = new Rank();
		List<Rank> ranks = rankService.selectAll();
		ModelAndView mav = new ModelAndView("rank/edit");
		mav.addObject("model", rank);
		mav.addObject("ranks", ranks);
        return mav;
	}
	
	/**
	 * ����-����
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	@ResponseBody
    public ResultHelper add(Rank rank){
/*		Rank rank = new Rank();
		rank.setParentId(0);
		rank.setRankName("����");
		rank.setController("rank");
		rank.setAction("insert");
		rank.setLeftShow(1);
		rank.setShowOrder(1);
		rank.setDepth(1);*/
		
		//��֤�Ȳ��ӣ���ʱ����ģ�Ͱ󶨵���֤������
		
		
		ResultHelper resultHelper = rankService.add(rank);

    	return resultHelper;
    }

	/**
	 * �޸�-��ͼ
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/edit")
	public ModelAndView edit(int id){
		Rank rank = rankService.selectById(id);
		List<Rank> ranks = rankService.selectAll();
		ModelAndView mav = new ModelAndView("rank/edit");
		mav.addObject("model", rank);
		mav.addObject("ranks", ranks);
        return mav;
	}
	
	/**
	 * �޸�-����
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	@ResponseBody
    public ResultHelper edit(Rank rank){
		ResultHelper resultHelper = rankService.update(rank);
    	return resultHelper;
    }
	
	/**
	 * ɾ��
	 * ע�⣺��ɾ����ʱ����������࣬������ֱ��ɾ��
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/delete")
	@ResponseBody
	public ResultHelper delete(int id){
		ResultHelper resultHelper = rankService.delete(id);
		
		return resultHelper;
	}
	
	/**
	 * �б�ҳ
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("rank/index");
        return mav;
	}

	/**
	 * �첽��ȡ�б�
	 * ���������ȡ�ļ��ַ��� ��https://www.cnblogs.com/xiaoxi/p/5695783.html
	 */
	@NeedLogin
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	public ModelAndView list(RankSearchModel searchModel){
		searchModel.setRankName(searchModel.getRankName().trim());
		searchModel.setPageSize(15);
		PagingModel<Rank> pagingModel = rankService.selectPagingData(searchModel);
        ModelAndView mav = new ModelAndView("rank/list");
        mav.addObject("pagingModel", pagingModel);
        
        return mav;
	}
}

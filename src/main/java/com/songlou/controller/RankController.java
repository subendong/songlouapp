package com.songlou.controller;

import java.util.List;

import org.omg.CORBA.Request;
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
	 * ����-��ͼ
	 * @return
	 */
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
	@RequestMapping(value = "/add", method=RequestMethod.POST)
    public @ResponseBody String insert(Rank rank){
/*		Rank rank = new Rank();
		rank.setParentId(0);
		rank.setRankName("����");
		rank.setController("rank");
		rank.setAction("insert");
		rank.setLeftShow(1);
		rank.setShowOrder(1);
		rank.setDepth(1);*/
		
		//��֤�Ȳ��ӣ���ʱ����ģ�Ͱ󶨵���֤������
		
		
		rankService.insert(rank);

    	return Integer.toString(rank.getId());
    }

	/**
	 * �޸�-��ͼ
	 * @return
	 */
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
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
    public @ResponseBody String edit(Rank rank){
		rankService.update(rank);
    	return Integer.toString(rank.getId());
    }
	
	/**
	 * ɾ��
	 * @param rank
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody String delete(String ids){
		rankService.delete(ids);
		
		return "ɾ���ɹ�";
	}
	
	/**
	 * �б�ҳ
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("rank/index");
        return mav;
	}

	/**
	 * �첽��ȡ�б�
	 * ���������ȡ�ļ��ַ��� ��https://www.cnblogs.com/xiaoxi/p/5695783.html
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

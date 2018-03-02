package com.songlou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.songlou.annotation.NeedLogin;
import com.songlou.instrument.ResultHelper;
import com.songlou.model.AdminSearchModel;
import com.songlou.model.PagingModel;
import com.songlou.pojo.Admin;
import com.songlou.service.AdminService;

/**
 * http://localhost:8080/songlouapp/admin/index
 * @author sbd04462
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
    private AdminService adminService;
	
	/**
	 * 列表页
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("admin/index");
        return mav;
	}

	/**
	 * 异步获取列表
	 * @return
	 */
	@NeedLogin
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	public ModelAndView index(AdminSearchModel searchModel){
		searchModel.setUsername(searchModel.getUsername().trim());
		searchModel.setPageSize(15);
		PagingModel<Admin> pagingModel = adminService.selectPagingData(searchModel);
        ModelAndView mav = new ModelAndView("admin/list");
        mav.addObject("pagingModel", pagingModel);
        
        return mav;
	}

	/**
	 * 新增-视图
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/add")
	public ModelAndView add(){
		Admin admin = new Admin();
		ModelAndView mav = new ModelAndView("admin/edit");
		mav.addObject("model", admin);
        return mav;
	}
	
	/**
	 * 新增-保存
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@ResponseBody
	@RequestMapping(value = "/add", method=RequestMethod.POST)
    public ResultHelper insert(Admin admin){
		ResultHelper resultHelper = adminService.insert(admin);
    	return resultHelper;
	}
	
	/**
	 * 修改-视图
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/edit")
	public ModelAndView edit(int id){
		Admin  admin = adminService.selectById(id);
		ModelAndView mav = new ModelAndView("admin/edit");
		mav.addObject("model", admin);
        return mav;
	}
	
	/**
	 * 修改-保存
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	@ResponseBody
    public ResultHelper edit(Admin admin){
		ResultHelper resultHelper = adminService.update(admin);
		return resultHelper;
    }
	
	/**
	 * 删除
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/delete")
	public @ResponseBody String delete(String ids){
		//adminService.delete(ids);
		adminService.batchDelete(ids);
		return "删除成功";
	}
}

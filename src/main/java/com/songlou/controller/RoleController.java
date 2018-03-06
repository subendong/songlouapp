package com.songlou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.songlou.annotation.NeedLogin;
import com.songlou.instrument.ResultHelper;
import com.songlou.model.PagingModel;
import com.songlou.model.RoleSearchModel;
import com.songlou.pojo.Role;
import com.songlou.service.RoleService;

/**
 * http://localhost:8080/songlouapp/role/index
 * @author sbd04462
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
    private RoleService roleService;
	
	/**
	 * �б�ҳ
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("role/index");
        return mav;
	}

	/**
	 * �첽��ȡ�б�
	 * @return
	 */
	@NeedLogin
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	public ModelAndView index(RoleSearchModel searchModel){
		searchModel.setName(searchModel.getName().trim());
		searchModel.setPageSize(15);
		PagingModel<Role> pagingModel = roleService.selectPagingData(searchModel);
        ModelAndView mav = new ModelAndView("role/list");
        mav.addObject("pagingModel", pagingModel);
        
        return mav;
	}

	/**
	 * ����-��ͼ
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/add")
	public ModelAndView add(){
		Role role = new Role();
		ModelAndView mav = new ModelAndView("role/edit");
		mav.addObject("model", role);
        return mav;
	}
	
	/**
	 * ����-����
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@ResponseBody
	@RequestMapping(value = "/add", method=RequestMethod.POST)
    public ResultHelper insert(Role role){
		ResultHelper resultHelper = roleService.insert(role);
    	return resultHelper;
	}
	
	/**
	 * �޸�-��ͼ
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/edit")
	public ModelAndView edit(int id){
		Role  role = roleService.selectById(id);
		ModelAndView mav = new ModelAndView("role/edit");
		mav.addObject("model", role);
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
    public ResultHelper edit(Role role){
		ResultHelper resultHelper = roleService.update(role);
		return resultHelper;
    }
	
	/**
	 * ɾ��
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/delete")
	public @ResponseBody String delete(String ids){
		roleService.batchDelete(ids);
		return "ɾ���ɹ�";
	}
}

package com.songlou.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.songlou.model.RoleSetModel;
import com.songlou.pojo.Admin;
import com.songlou.pojo.Role;
import com.songlou.service.AdminRoleService;
import com.songlou.service.AdminService;
import com.songlou.service.RoleService;

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
	@Autowired
    private RoleService roleService;
	@Autowired
    private AdminRoleService adminRoleService;
	
	/**
	 * �б�ҳ
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("admin/index");
        return mav;
	}

	/**
	 * �첽��ȡ�б�
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
	 * ����-��ͼ
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
	 * ����-����
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
	 * �޸�-��ͼ
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
	 * �޸�-����
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
	 * ɾ��
	 * @param rank
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/delete")
	public @ResponseBody String delete(String ids){
		//adminService.delete(ids);
		adminService.batchDelete(ids);
		return "ɾ���ɹ�";
	}
	
	//���²����ǹ���Ա-��ɫ�õ��ģ��������������������
	//***************************************************************
	
	/**
	 * ���ý�ɫ
	 * @param adminId
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/setroles")
	public ModelAndView setRoles(int adminId){
		//��ȡ���н�ɫ�͵�ǰ�û�
		List<Role> roles = roleService.selectAll();
		List<Role> selectedRoles = adminRoleService.selectRolesByAdminId(adminId);
		//��Ҫ�½�һ����ͼģ�ͣ����˰���Role���������⣬����Ҫ�����Ƿ�ѡ��
		List<RoleSetModel> roleSets = new ArrayList<>();
		for(Role role : roles){
			RoleSetModel roleSet = new RoleSetModel();
			roleSet.setId(role.getId());
			roleSet.setName(role.getName());
			if(selectedRoles.contains(role)){
				roleSet.setChecked(true);
			}
			roleSets.add(roleSet);
		}
		
		ModelAndView mav = new ModelAndView("adminrole/setroles");
		mav.addObject("adminId", adminId);
		mav.addObject("roleSets", roleSets);
        return mav;
	}
	
	/**
	 * ����-����
	 * @return
	 */
	@NeedLogin
	@ResponseBody
	@RequestMapping(value = "/setroles", method=RequestMethod.POST)
	public ResultHelper setRoles(int adminId, String roleIds){
		ResultHelper resultHelper = adminRoleService.insert(adminId, roleIds);
    	return resultHelper;
	}
	
	//***************************************************************
}

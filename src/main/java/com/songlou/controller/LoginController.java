package com.songlou.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.songlou.common.ResultHelper;
import com.songlou.pojo.Admin;
import com.songlou.service.LoginService;

/**
 * http://localhost:8080/songlouapp/login/index
 * @author sbd04462
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
    private LoginService loginService;	
	
	/**
	 * µÇÂ¼ÊÓÍ¼
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView index(){			
		ModelAndView mav = new ModelAndView("login/index");
		return mav;
	}
	
	/**
	 * µÇÂ¼-Âß¼­
	 * @param admin
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index", method=RequestMethod.POST)
	@ResponseBody
	public ResultHelper index(Admin admin, HttpServletResponse response){
		ResultHelper resultHelper = loginService.Login(admin, response);
		return resultHelper;
	}
}

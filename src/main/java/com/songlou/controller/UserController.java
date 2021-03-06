package com.songlou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.songlou.pojo.User;
import com.songlou.service.UserService;

//http://localhost:8080/songlouapp/user/index
//http://localhost:8080/songlouapp/css/index.css
//http://localhost:8080/songlouapp/js/index.js
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService userService;

    @RequestMapping("/index")
    public ModelAndView index() {
        User user = userService.getByUserId(1);
        
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", user);
        
        return mav;
    }
    
    @RequestMapping("/test")
    public @ResponseBody String test(){
    	return "helloxxx";
    }
}

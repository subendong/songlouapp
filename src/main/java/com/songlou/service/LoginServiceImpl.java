package com.songlou.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.songlou.common.ResultHelper;
import com.songlou.pojo.Admin;

/**
 * 登录服务类
 * @author sbd04462
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 登录
	 */
	@Override
	public ResultHelper Login(Admin admin, HttpServletResponse response) {
		admin = sqlSessionTemplate.selectOne("com.songlou.mapper.AdminMapper.selectLogin", admin);
		if(admin == null){
			return new ResultHelper(1, false, "error", null);
		}
		//写cookie
		Cookie cookie = new Cookie("songlouappmanage", admin.toString());  
        cookie.setMaxAge(30 * 60);// 设置为30min  
        cookie.setPath("/"); 
        response.addCookie(cookie);
        
		return new ResultHelper(0, true, "success", null);
	}
}

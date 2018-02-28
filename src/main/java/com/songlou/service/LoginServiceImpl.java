package com.songlou.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.songlou.common.ResultHelper;
import com.songlou.pojo.Admin;

/**
 * ��¼������
 * @author sbd04462
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * ��¼
	 */
	@Override
	public ResultHelper Login(Admin admin, HttpServletResponse response) {
		admin = sqlSessionTemplate.selectOne("com.songlou.mapper.AdminMapper.selectLogin", admin);
		if(admin == null){
			return new ResultHelper(1, false, "error", null);
		}
		//дcookie
		Cookie cookie = new Cookie("songlouappmanage", admin.toString());  
        cookie.setMaxAge(30 * 60);// ����Ϊ30min  
        cookie.setPath("/"); 
        response.addCookie(cookie);
        
		return new ResultHelper(0, true, "success", null);
	}
}

package com.songlou.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songlou.common.DesHelper;
import com.songlou.common.Md5Helper;
import com.songlou.common.ResultHelper;
import com.songlou.model.SiteModel;
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
		//对明文的password进行md5加密
		String password = Md5Helper.MD5(admin.getPassword());
		admin.setPassword(password);
		//检查登录信息是否正确
		admin = sqlSessionTemplate.selectOne("com.songlou.mapper.AdminMapper.selectLogin", admin);
		if(admin == null){
			return new ResultHelper(1, false, "error", null);
		}
		//仅将id,username,password加密后保存在cookie中
		Admin tempAdmin = new Admin();
		tempAdmin.setId(admin.getId());
		tempAdmin.setUsername(admin.getUsername());
		tempAdmin.setPassword(admin.getPassword());
		//将当前用户序列化成json格式
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(tempAdmin);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//对JSON字符串进行 加密
		try {
			json = new DesHelper().encrypt(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//写cookie
		Cookie cookie = new Cookie(SiteModel.cookieKey, json);  
        cookie.setMaxAge(30 * 60);// 设置为30min  
        cookie.setPath("/"); 
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        
		return new ResultHelper(0, true, "success", null);
	}
}

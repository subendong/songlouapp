package com.songlou.service;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songlou.instrument.DesHelper;
import com.songlou.instrument.GlobalHelper;
import com.songlou.instrument.Md5Helper;
import com.songlou.instrument.ResultHelper;
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
			return new ResultHelper(1, false, "账号或密码错误", null);
		}
		//仅将id,username,password,photo加密后保存在cookie中
		Admin tempAdmin = new Admin();
		tempAdmin.setId(admin.getId());
		tempAdmin.setUsername(admin.getUsername());
		tempAdmin.setPassword(admin.getPassword());
		tempAdmin.setPhoto(admin.getPhoto());
		//将当前用户序列化成json格式
		String json = "";
		try {
			json = new ObjectMapper().writeValueAsString(tempAdmin);
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

	/**
	 * 从cookie中获取当前用户信息
	 */
	@Override
	public ResultHelper getAdminFromCookie(HttpServletRequest request) {
		String cookieValue = GlobalHelper.getCookie(request, SiteModel.cookieKey);
		
		//判断cookie是否有效
        if (GlobalHelper.IsNullOrEmpty(cookieValue)) {
            return new ResultHelper(1, false, "您已经太长时间没有操作,请刷新页面", null);
        }
        
        //解密
        String strJson = null;
		try {
			strJson = new DesHelper().decrypt(cookieValue);
		} catch (Exception e1) {
			return new ResultHelper(1, false, "解密时发生错误", null);
		}
        
        //反序列化
        Admin admin = null;
		try {
			admin = new ObjectMapper().readValue(strJson, Admin.class);
		} catch (JsonProcessingException e) {
			return new ResultHelper(1, false, "反序列化时出错", null);
		} catch (IOException e) {
			return new ResultHelper(1, false, "反序列化时出错", null);
		}
		
		return new ResultHelper(0, true, "success", admin);
	}
}

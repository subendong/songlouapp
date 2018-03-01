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
		//�����ĵ�password����md5����
		String password = Md5Helper.MD5(admin.getPassword());
		admin.setPassword(password);
		//����¼��Ϣ�Ƿ���ȷ
		admin = sqlSessionTemplate.selectOne("com.songlou.mapper.AdminMapper.selectLogin", admin);
		if(admin == null){
			return new ResultHelper(1, false, "error", null);
		}
		//����id,username,password���ܺ󱣴���cookie��
		Admin tempAdmin = new Admin();
		tempAdmin.setId(admin.getId());
		tempAdmin.setUsername(admin.getUsername());
		tempAdmin.setPassword(admin.getPassword());
		//����ǰ�û����л���json��ʽ
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(tempAdmin);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//��JSON�ַ������� ����
		try {
			json = new DesHelper().encrypt(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//дcookie
		Cookie cookie = new Cookie(SiteModel.cookieKey, json);  
        cookie.setMaxAge(30 * 60);// ����Ϊ30min  
        cookie.setPath("/"); 
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        
		return new ResultHelper(0, true, "success", null);
	}
}

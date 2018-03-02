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
			return new ResultHelper(1, false, "�˺Ż��������", null);
		}
		//����id,username,password,photo���ܺ󱣴���cookie��
		Admin tempAdmin = new Admin();
		tempAdmin.setId(admin.getId());
		tempAdmin.setUsername(admin.getUsername());
		tempAdmin.setPassword(admin.getPassword());
		tempAdmin.setPhoto(admin.getPhoto());
		//����ǰ�û����л���json��ʽ
		String json = "";
		try {
			json = new ObjectMapper().writeValueAsString(tempAdmin);
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

	/**
	 * ��cookie�л�ȡ��ǰ�û���Ϣ
	 */
	@Override
	public ResultHelper getAdminFromCookie(HttpServletRequest request) {
		String cookieValue = GlobalHelper.getCookie(request, SiteModel.cookieKey);
		
		//�ж�cookie�Ƿ���Ч
        if (GlobalHelper.IsNullOrEmpty(cookieValue)) {
            return new ResultHelper(1, false, "���Ѿ�̫��ʱ��û�в���,��ˢ��ҳ��", null);
        }
        
        //����
        String strJson = null;
		try {
			strJson = new DesHelper().decrypt(cookieValue);
		} catch (Exception e1) {
			return new ResultHelper(1, false, "����ʱ��������", null);
		}
        
        //�����л�
        Admin admin = null;
		try {
			admin = new ObjectMapper().readValue(strJson, Admin.class);
		} catch (JsonProcessingException e) {
			return new ResultHelper(1, false, "�����л�ʱ����", null);
		} catch (IOException e) {
			return new ResultHelper(1, false, "�����л�ʱ����", null);
		}
		
		return new ResultHelper(0, true, "success", admin);
	}
}

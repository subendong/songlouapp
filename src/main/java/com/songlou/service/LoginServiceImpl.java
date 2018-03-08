package com.songlou.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import com.songlou.pojo.Rank;
import com.songlou.pojo.Role;

import javax.servlet.http.HttpSession;

/**
 * 登录服务类
 * @author sbd04462
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private RoleRankService roleRankService;

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
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 */
	public ResultHelper logout(HttpServletRequest request, HttpServletResponse response){
		//将cookie设置为立即过期
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies){
                //如果找到同名cookie，就将value设置为null，将存活时间设置为0，再替换掉原cookie，这样就相当于删除了。
                if(cookie.getName().equals(SiteModel.cookieKey)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
		//删除存放用户权限列表的session
		if(request.getSession().getAttribute(SiteModel.sessionKey) != null){
			request.getSession().setAttribute(SiteModel.sessionKey, null);
		}
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
	
	/**
	 * 获取权限列表
	 */
	@SuppressWarnings("unchecked")
	public List<Rank> getRankListSession(HttpServletRequest request, Admin admin){
		HttpSession session = request.getSession();
		if(session.getAttribute(SiteModel.sessionKey) == null){
			List<Rank> ranks = new ArrayList<Rank>();
			//查询当前管理员/用户所拥有的角色
			List<Role> roles = adminRoleService.selectRolesByAdminId(admin.getId());
			//查询角色拥有的权限
			for(Role role : roles){
				List<Rank> rs = roleRankService.selectRanksByRoleId(role.getId());
				ranks.addAll(rs);
			}
			session.setAttribute(SiteModel.sessionKey, ranks);
		}
		
		List<Rank> ranks = (ArrayList<Rank>)session.getAttribute(SiteModel.sessionKey);
		
		return ranks;
	}
}

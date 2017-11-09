package com.songlou.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songlou.pojo.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// mybatis sql模板的命名空间
	//private static final String NAMESPACE = "com.songlou.mapper.UserMapper";

	@Override
	public User getByUserId(int id) {
		//因为在UserMapper.xml里面配置了命名空间，所以在这里没有必要在加上了
		//User user = sqlSessionTemplate.selectOne(NAMESPACE + ".getByUserId", 1);
		User user = sqlSessionTemplate.selectOne("getByUserId", 1);
		return user;
	}
}

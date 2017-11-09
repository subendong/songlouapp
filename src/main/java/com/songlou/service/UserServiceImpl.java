package com.songlou.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songlou.pojo.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// mybatis sqlģ��������ռ�
	//private static final String NAMESPACE = "com.songlou.mapper.UserMapper";

	@Override
	public User getByUserId(int id) {
		//��Ϊ��UserMapper.xml���������������ռ䣬����������û�б�Ҫ�ڼ�����
		//User user = sqlSessionTemplate.selectOne(NAMESPACE + ".getByUserId", 1);
		User user = sqlSessionTemplate.selectOne("getByUserId", 1);
		return user;
	}
}

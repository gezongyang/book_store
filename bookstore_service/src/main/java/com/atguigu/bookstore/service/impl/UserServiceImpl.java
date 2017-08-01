package com.atguigu.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bookstore.beans.Resource;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.AuthorityResourceMapper;
import com.atguigu.bookstore.dao.ResourceMapper;
import com.atguigu.bookstore.dao.UserAuthorityMapper;
import com.atguigu.bookstore.dao.UserMapper;
import com.atguigu.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired 
	private UserMapper userMapper;
	
	@Autowired
	private UserAuthorityMapper userAuthorityDao;
	
	@Autowired
	private AuthorityResourceMapper authorityResourceDao;
	
	@Autowired
	private ResourceMapper resourceDao; 
	 
	@Override
	public User login(User user) {
		
		return userMapper.getUser(user);
	}

	@Override
	public boolean register(User user) {
		return userMapper.checkUsername(user);
	}

	@Override
	public void savaUser(User user) {
		
		String role = user.getRole();		
		//查询角色对应的资源
		List<Resource> resourceByAuth = authorityResourceDao.selectResourceByRole(role);
		//查询最大权限位
		int maxPos = resourceDao.selectMaxPos();
		//声明一个权限码的数组
		int [] codeArr = new int[maxPos + 1];
		
		for (Resource resource : resourceByAuth) {
			int resPos = resource.getResPos();
			int resCode = resource.getResCode();
			int codeOld = codeArr[resPos];
			int codeNew = codeOld | resCode ;
			codeArr[resPos] = codeNew;
		}
		//把权限码数组转为字符串存储
		StringBuffer str = new StringBuffer();
		
		for (int i = 0; i < codeArr.length; i++) {
			if (i == codeArr.length-1) {
				
				str.append(codeArr[i]);
			} else {
				
				str.append(codeArr[i]).append(",");
			}
		}
		
		user.setCodeArray(str.toString());

		userMapper.saveUser(user);
	}

	@Override
	public User getUserByName(String username) {
		return userMapper.getUserByUserName(username);
	}

	@Override
	public boolean isExist(String userName, String password) {
		
		return userMapper.isExist(userName,password);
	}

	@Override
	public User getUserByUserNameAndPassword(String userName, String password) {
		return userMapper.selectUserByUserNameAndPassword(userName,password);
	}

	@Override
	public void saveLoginUser(String username,String tableName) {
		userMapper.saveCurrentUser(username,tableName); 
	}

    
}

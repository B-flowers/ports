package com.ports.service.impl;

import java.util.List;
import java.util.Map;

import com.ports.bean.PageModel;
import com.ports.bean.Users;
import com.ports.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImpl {

	@Autowired
	private UsersDao usersDao;

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	public void updateBySelective(Users users) {
		usersDao.updateBySelective(users);
	}

	public Users queryById(String id) {
		return usersDao.queryById(id);
	}

	public void deleteById(String id) {
		usersDao.deleteById(id);
	}

	public long userCount(Map<String, String> pageMap) {
		return usersDao.getCount(pageMap);
	}

	public int add(Users user) {
		return usersDao.add(user);
	}

	public List<Users> selectPage(PageModel<Users> page) {
		return usersDao.getPage(page);
	}
	public int updateById(Users user){
		return usersDao.updateById(user);
	};
}

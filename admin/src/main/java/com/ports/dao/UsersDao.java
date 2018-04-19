package com.ports.dao;

import java.util.List;
import java.util.Map;

import com.ports.bean.PageModel;
import com.ports.bean.Users;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersDao {

	public int add(Users users);

	public List<Users> getPage(PageModel<Users> page);

	public long getCount(Map<String, String> pageMap);

	public void deleteById(String id);

	public Users queryById(String id);

	public int updateById(Users users);

	public int updateBySelective(Users users);

}

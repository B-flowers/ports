package com.ports.dao;

import com.ports.bean.PageModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseDao {
	public <T> int add(T t);

	public void deleteById(String id);
	
	public <T> T queryById(String id);
	
	public <T> int update(T t);
	
	public <T> int updateBySelective(T t);
	
	public <T> List<T> getPage(PageModel<T> page);

	public long getCount(Map<String, String> pageMap);
}

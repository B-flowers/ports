package com.ports.service;

import com.ports.bean.PageModel;
import com.ports.dao.BaseDao;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
	public abstract void setBaseDao(BaseDao baseDao);

	public void add(T t)  throws Exception;
	
	public void update(T t)  throws Exception;
	
	public void deleteById(String... ids) throws Exception;

	public T queryById(String id) throws Exception;

	public List<T> getPage(PageModel<T> page) throws Exception;

	//public long getCount(Map<String, String> pageMap) throws Exception;

	public int updateBySelective(T t) throws Exception;
	public long getCount(Map<String, String> pageMap) throws Exception;

}

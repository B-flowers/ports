package com.ports.service.impl;

import com.ports.bean.PageModel;
import com.ports.dao.BaseDao;
import com.ports.service.BaseService;

import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	public BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void add(T t) throws Exception {
		baseDao.add(t);
		
	}

	@Override
	public void update(T t) throws Exception {
		baseDao.update(t);
		
	}

	@Override
	public void deleteById(String... ids) throws Exception {
		if(ids == null || ids.length < 1){
			return;
		}
		for(String id : ids ){
			baseDao.deleteById(id);
		}
		
	}

	@Override
	public T queryById(String id) throws Exception {
		return baseDao.queryById(id);
	}

	@Override
	public List<T> getPage(PageModel<T> page) throws Exception {
		return baseDao.getPage(page);
	}

	@Override
	public long getCount(Map<String, String> pageMap) throws Exception {
		return baseDao.getCount(pageMap);
	}
	
	@Override
	public int updateBySelective(T t) throws Exception {
		return baseDao.updateBySelective(t);
	}


}

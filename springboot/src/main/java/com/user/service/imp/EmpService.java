package com.user.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.user.dao.EmpDao;
import com.user.entity.Emp;
import com.user.service.IEmpService;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
@CacheConfig(cacheNames="empinfo")
public class EmpService implements IEmpService {

	
	@Autowired
	private EmpDao empDao;
	
	@Override
	@Cacheable
	public List<Emp> findallemp() {
		return empDao.findallemp();
	}

}

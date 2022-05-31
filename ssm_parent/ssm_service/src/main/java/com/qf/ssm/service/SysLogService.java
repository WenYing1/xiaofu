package com.qf.ssm.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.ssm.dao.ISysLogDao;
import com.qf.ssm.domain.SysLog;

@Service
public class SysLogService implements ISysLogService {

	@Autowired
	private ISysLogDao sysLogDao;
	
	public void insert(SysLog sysLog) {
		String string = UUID.randomUUID().toString();
		sysLog.setId(string);
		sysLogDao.insert(sysLog);
		
	}

	public List<SysLog> findAll() {
	List<SysLog> logs=	sysLogDao.findAll();
		return logs;
	}

}

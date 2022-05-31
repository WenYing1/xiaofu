package com.qf.ssm.service;

import java.util.List;

import com.qf.ssm.domain.SysLog;

public interface ISysLogService {

	void insert(SysLog sysLog);

	List<SysLog> findAll();

}

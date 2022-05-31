package com.qf.ssm.controller;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qf.ssm.domain.SysLog;
import com.qf.ssm.service.ISysLogService;

@Aspect
@Component
@RequestMapping("/sysLog")
public class SysLogController {
     @Autowired
	 private ISysLogService logService;
	 private Date visiTime;
	 private Class clazz;
	 private Method method;
	 private String name;
	 @Autowired
	 private HttpServletRequest request;
	 @Before("execution(* com.qf.ssm.controller.*.*(..))")
	 public void beforeLog(JoinPoint jp) throws NoSuchMethodException, SecurityException {
		visiTime = new Date();
		clazz= jp.getTarget().getClass();
	    name = jp.getSignature().getName();
	    Object[] args = jp.getArgs();
	    if(args==null||args.length<=0) {
	    method=	clazz.getMethod(name);
	    }else {
	    	Class[] clazzs = new Class[args.length];
	    	for (int i = 0; i <args.length; i++) {
				clazzs[i]=args[i].getClass();
			}
	    method=	clazz.getMethod(name, clazzs);
	    }
	    
		 
		 
	 }
	 
	 @After("execution(* com.qf.ssm.controller.*.*(..))")
	 public void afterLog() {
		 SysLog sysLog = new SysLog();
		 String ip = request.getRemoteAddr();
		 String url =null;
		 String username=null;
		 long executionTime;
		 if(clazz.isAnnotationPresent(RequestMapping.class)) {
		RequestMapping requestMapping=	 (RequestMapping) clazz.getAnnotation(RequestMapping.class);
		String classUrl =requestMapping.value()[0];
		if(method.isAnnotationPresent(RequestMapping.class)) {
			RequestMapping annotation = method.getAnnotation(RequestMapping.class);
			String methodUrl=annotation.value()[0];
			url =classUrl+methodUrl;
			
			SecurityContext context = SecurityContextHolder.getContext();
			username = context.getAuthentication().getName();
			executionTime=new Date().getTime()-visiTime.getTime();
			sysLog.setExecutionTime(executionTime);
			sysLog.setIp(ip);
			sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
			sysLog.setUrl(url);
			sysLog.setUsername(username);
			sysLog.setVisitTime(visiTime);
			logService.insert(sysLog);
		}
		
		
			 }
		 
		 
		 
	 }
	 @RequestMapping("findAll")
	 public ModelAndView findAll() {
		 ModelAndView mav =new ModelAndView();
	List<SysLog> lists=	 logService.findAll();
	mav.addObject("sysLogs", lists);
	mav.setViewName("syslog-list");
	return mav;
	 }
}

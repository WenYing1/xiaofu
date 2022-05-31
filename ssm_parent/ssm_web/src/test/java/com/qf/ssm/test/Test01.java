package com.qf.ssm.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.qf.ssm.service.IProductService;

public class Test01 {
	
    @Test
    public void test(){
    	BCryptPasswordEncoder bpe =new BCryptPasswordEncoder();
    	String encode = bpe.encode("12345");
    	System.out.println(encode);
    }
}

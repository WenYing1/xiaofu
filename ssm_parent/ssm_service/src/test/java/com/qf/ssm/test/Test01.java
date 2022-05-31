package com.qf.ssm.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test01 {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bpe =new BCryptPasswordEncoder();
		String encode = bpe.encode("12345");
		System.out.println(encode);
		
	}
}

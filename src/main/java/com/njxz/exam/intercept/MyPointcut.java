package com.njxz.exam.intercept;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcut {
	@Pointcut(value = "execution(* com.njxz.exam.controller.*.*(..))")
	public void allClass() {
	}

	
}

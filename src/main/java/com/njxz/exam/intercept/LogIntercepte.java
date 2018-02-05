package com.njxz.exam.intercept;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.aspectj.lang.JoinPoint;

import com.njxz.exam.util.Logable;

@Aspect
@Component
public class LogIntercepte extends Logable{
	
	//@Before(value="MyPointcut.allClass()")
	public void beforeMethodLog(JoinPoint jp){
		info("执行"+jp.getSignature().getName()+"方法");
		if(jp.getArgs().length>0){
			info(jp.getSignature().getName()+"类的方法参数："+Arrays.toString(jp.getArgs()));
		}
	}

}

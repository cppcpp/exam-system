package com.njxz.exam.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({  java.lang.annotation.ElementType.TYPE,  java.lang.annotation.ElementType.ANNOTATION_TYPE })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidNumSubNumValidator.class })
@Documented
public @interface ValidNumSubNum {
	 String message() default "{knowledgePoints.kNumkSubNum.isExits}";

	 
	 	//这两个属性必须写
	    Class<?>[] groups() default { };

	    Class<? extends Payload>[] payload() default { };

}

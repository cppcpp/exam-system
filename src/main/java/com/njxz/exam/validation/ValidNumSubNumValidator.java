package com.njxz.exam.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.njxz.exam.modle.KnowledgePoints;
import com.njxz.exam.service.KnowledgePointsService;

public class ValidNumSubNumValidator implements ConstraintValidator<ValidNumSubNum, KnowledgePoints>{

	@Autowired
	KnowledgePointsService kPService;

	public void initialize(ValidNumSubNum constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}
	public boolean isValid(KnowledgePoints kp, ConstraintValidatorContext context) {
		if(kp==null||kp.getkNum()==null||kp.getkSubNum()==null||kp.getSubjectId()==null)
			return true;
		
		//联合主键已经存在
		if(kPService.isExitKnowPoint(kp.getkNum(), kp.getkSubNum(), kp.getSubjectId())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate( "{knowledgePoints.kNumkSubNum.isExits}" )
           .addConstraintViolation();
			return false;
		}
		return true;
	    	
	   
	}
}

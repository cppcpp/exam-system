package com.njxz.exam.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

public class ErrorUtil extends Logable {
	public static boolean addErrorToView(BindingResult bindingResult, ModelAndView mav) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				// 输出错误信息
				error(objectError.getDefaultMessage());
			}
			mav.addObject("allErrors", allErrors);
			return false;
		}
		return true;
	}
}

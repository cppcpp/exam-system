package com.njxz.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/databaseBabckup")
public class DatabaseController {
	
	@RequestMapping(value="/all")
	public String dataBaseupAllPage() {
		
		return "databaseBackup";
	}
}

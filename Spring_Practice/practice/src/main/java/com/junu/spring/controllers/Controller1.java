package com.junu.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controller1 {
	
	@GetMapping(path="/test1")
	public String forwardView() {
		return "test1";
	}
	
	@PostMapping(path="/calculate")
	public String forwarResult(@RequestParam(name="num1", required=false, defaultValue="0")int num1, 
							   @RequestParam(name="num2", required=false, defaultValue="0")int num2,
							   ModelMap modelMap) {
		
		int result = num1+num2;
		
		modelMap.addAttribute("num1", num1);
		modelMap.addAttribute("num2", num2);
		modelMap.addAttribute("result", result);
		
		return "result";
	}
	
}	


package com.junu.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.junu.webmvc.dto.User;

@Controller 
public class UserController {

	@RequestMapping(path="/userForm",method=RequestMethod.GET)
	public String userForm() {
		return "userForm";
	}
	
	@RequestMapping(path="/regist",method=RequestMethod.POST)
	public String regist(@ModelAttribute User user) { //user dto객체에 알아서 요청을 통해 전달받은 값을, 객체 내의 인스턴스에 할당해줌
		System.out.println("사용자가 입력한 user 정보이며 해당 정보를 이용하는 코드가 와야 함");
		System.out.println(user);
		return "regist"; // view name을 regist로 넘김
	}
}

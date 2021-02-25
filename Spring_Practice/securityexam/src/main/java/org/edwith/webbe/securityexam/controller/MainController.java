package org.edwith.webbe.securityexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/main")
	@ResponseBody
	public String main() {
		return "main page";
	}
	
	//실제 컨트롤러 동작시켜보면 아래 url로 접속할 경우 403 메시지(Access Denied)를 확인할 수 있음
	//인증/인가를 거치지 않았기 때문
	@RequestMapping("/securepage")
	@ResponseBody
	public String securitypage() {
		return "secure page";
	}
}

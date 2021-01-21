package com.junu.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //해당 클래스가 Controller임을 명시해야, ComponentScan시 해당 클래스를 컨트롤러로 스캔
public class PlusController {
	
	@GetMapping(path="/plusform") //GET방식으로 요청 받은 것을 처리, @RequestParam 사용해보기
	public String plusForm() {
		//view를 찾아서, view에 대한 정보를 넘기는 메소드
		return "plusForm"; //요청이 들어왔을 때 보여줄 view의 이름.. 즉 jsp 파일의 이름인 plusForm을 리턴하는 것
	}
	
	@PostMapping(path="/plus") //POST 방식으로 받은 요청 처리
	public String plus(@RequestParam(name="value1", required=true)int value1, @RequestParam(name="value2", required=true)int value2, ModelMap modelMap) {
		//요청시 넘겨받은 param의 이름과, 매개변수로 사용할 변수 이름을 서로 매핑시켜 사용
		
		/*
		 * Controller 메소드들의 매개변수 타입들
		 * HttpServletRequest, HttpServletResponse, HttpSession
		 * MultipartRequest, MultipartHttpServletReq ... (파일첨부 기능 사용할 경우)
		 * 
		 * */
		
		int result = value1+value2;
		//HttpRequestServlet을 사용하기 보다는, 스프링에서 제공하는 ModelMap을 사용하면 서블릿에 덜 종속됨
		//어차피 알아서 매핑해줌
		modelMap.addAttribute("value1",value1); //key,value 형식
		modelMap.addAttribute("value2",value2);
		modelMap.addAttribute("result",result);
		return "plusResult";
	}
}

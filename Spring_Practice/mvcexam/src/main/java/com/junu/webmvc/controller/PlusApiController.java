package com.junu.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.junu.webmvc.dto.PlusResult;

@Controller
public class PlusApiController {
	
	@GetMapping("/api/plus")
	@ResponseBody//ResponseBody 어노테이션이 붙을 경우 컨트롤러 메소드가 뷰이름(String)을 리턴하는 것이 아닌, 객체를 리턴하게 되며 이는 리턴할 객체를 출력하라는 것을 의미함
	public PlusResult plus(@RequestParam("value1") int value1, @RequestParam("value2")int value2) {
		PlusResult plusResult = new PlusResult();
		plusResult.setValue1(value1);
		plusResult.setValue2(value2);
		plusResult.setResult(value1+value2);
		return plusResult; //리턴할 객체
	}
	
	/*
	 * 만일, 여기서 PlusResult에 대한 컨버터(Converter)가 없을 경우
	 * 
	 * No converter found for return value of type:~ 와 같은 에러 메시지가 뜸
	 * 
	 * 이는 DispatcherServlet이 컨트롤러 메소드를 실행할 때, 해당 메소드가 리턴한 객체를 변환하려고 하는데
	 * 컨버터가 없기 때문에 변환을 할 수 없는 것임.
	 * 
	 * 따라서, 오류가 발생하지 않도록 미리 MessageConverter 객체를 미리 Bean으로 등록해줘야 함
	 * 그렇게 해야 Web API가 JSON이나 XML 데이터를 표현가능한 형태로 결과를 출력하게 됨.
	 * 
	 * pom.xml에 먼저 PlusResult를 JSON 메시지로 변환하기 위한 라이브러리를 추가하도록 하자.
	 * 이후, 다시 ?뒤에 value1, value2 값을 덧붙인 url로 접속하면
	 * JSON 형태로 데이터가 출력됨을 확인할 수 있음
	 * 
	 * 
	 * */
}

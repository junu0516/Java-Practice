package com.junu.spring.guestbook.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.junu.spring.guestbook.dto.Guestbook;
import com.junu.spring.guestbook.service.GuestbookService;

@RestController
@RequestMapping(path="/guestbooks")
public class GuestbookApiController {
	
	@Autowired
	GuestbookService guestbookService;
	
	@GetMapping //path가 따로 없음.. 위에 RequestMapping 어노테이션에 명시했기 때문
	//다시 말해, /guestbooks로 요청이 들어오면서 content-type이 application/json인 경우에는 아래의 메소드를 실행하게 될 것(GET방식일 때)
	public Map<String,Object> list(@RequestParam(name="start",required=false,defaultValue="0")int start){
		/*MessageConverter을 내부적으로 사용해서 반환하게 될 Map타입 객체를 JSON으로 변환해서 전송을 하게 될 것*/
		
		List<Guestbook> list = guestbookService.getGuestboks(start); 
		
		int count = guestbookService.getCount();
		int pageCount = count/GuestbookService.LIMIT;
		if(count % GuestbookService.LIMIT>0) {
			pageCount++;
		}
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i=0;i<pageCount;i++) {
			pageStartList.add(i*GuestbookService.LIMIT);
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("list",list);
		map.put("count",count);
		map.put("pageStartList",pageStartList);
		
		return map; //결과값으로 map 객체를 반환
	}
	
	//POST 요청이 들어온다면? -> @PostMapping 어노테이션이 붙은 메소드를 호출
	@PostMapping
	public Guestbook write(@RequestBody Guestbook guestbook, HttpServletRequest request) {
		//역시 Guestbook 타입의 객체를 반환하는데, 마찬가지로 JSON으로 변환되어 클라이언트에게 전송될 것
		String clientIp = request.getRemoteAddr();
		
		//id가 입력된 guestbook이 반환된다.
		Guestbook resultGuestbook = guestbookService.addGuestbook(guestbook, clientIp);
		return resultGuestbook;
	}
	
	@DeleteMapping("/{id}") //HTTP 메소드 중 DELETE에 해당
	//path 정보를 명시하는데, id 정보를 Path Variable로 받아줌
	public Map<String, String> delete(@PathVariable(name="id")Long id, HttpServletRequest request){
		String clientIp = request.getRemoteAddr();
		
		int deleteCount = guestbookService.deleteGuestbook(id, clientIp);
		return Collections.singletonMap("success", deleteCount>0?"true":"false"); 
	}
	
}

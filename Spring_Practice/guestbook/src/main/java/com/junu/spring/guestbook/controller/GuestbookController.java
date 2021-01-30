package com.junu.spring.guestbook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.junu.spring.guestbook.dto.Guestbook;
import com.junu.spring.guestbook.service.GuestbookService;

@Controller
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;
	
	@GetMapping(path="/list")
	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start, ModelMap model) {
		//start로 시작하는 방명록 목록 구하기
		List<Guestbook> list = guestbookService.getGuestboks(start);
		
		//전체 페이지수 구하기(페이징 처리)
		//ex. url에 list?start=0, list?start=5와 같은 형식으로 걸리도록 하는 것
		int count = guestbookService.getCount();
		int pageCount = count / GuestbookService.LIMIT;
		if(count % GuestbookService.LIMIT > 0) {
			pageCount++;
		}
		
		//페이지 수만큼 start의 값을 리스트로 저장
		//예를 들어 페이지의 수가 3일 경우 -> 0,5,10 이렇게 저장되는 것
		List<Integer> pageStartList = new ArrayList<>();
		for(int i=0;i<pageCount;i++) {
			pageStartList.add(i * GuestbookService.LIMIT);
		}
		
		model.addAttribute("list",list);
		model.addAttribute("count",count);
		model.addAttribute("pageStartList", pageStartList);
		
		return "list";
	}
	
	@PostMapping(path="/write")
	public String write(@ModelAttribute Guestbook guestbook, HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		System.out.println("clinetIp : "+clientIp);
		guestbookService.addGuestbook(guestbook, clientIp);
		
		return "redirect:list";
	}
	
}

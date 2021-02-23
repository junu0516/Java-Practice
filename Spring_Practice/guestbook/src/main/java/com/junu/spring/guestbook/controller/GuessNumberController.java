package com.junu.spring.guestbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuessNumberController {

		@GetMapping("/guess")
		public String guess(@RequestParam(name="number", required=false)Integer number, HttpSession session, ModelMap model) {
			String message = null;
			
			if(number==null) {
				session.setAttribute("count", 0);
				session.setAttribute("randomNumber", (int)(Math.random()*100)+1);
				message = "숫자 맞추기";
			}else {
				int count = (Integer)session.getAttribute("count");
				int randomNumber = (Integer)session.getAttribute("randomNumber");
				
				if(number<randomNumber) {
					message = "입력값이 너무 작습니다.";
					count++;
					session.setAttribute("count", count);
				}else if(number>randomNumber) {
					message = "입력값이 너무 큽니다.";
					count++;
					session.setAttribute("count", count);
				}else {
					count++;
					message="입력값이 "+number+"과 일치합니다.(총 "+count+"번 시도)";
					session.removeAttribute("count");
					session.removeAttribute("randomNumber");
				}
			}
			
			model.addAttribute("message",message);
			
			return "guess";
		}
}

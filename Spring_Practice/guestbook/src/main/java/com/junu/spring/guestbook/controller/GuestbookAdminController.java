package com.junu.spring.guestbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GuestbookAdminController {
	
	@GetMapping(path="/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name="password",required=true)String password, 
						HttpSession session, 
						RedirectAttributes redirectAttr) {
		
		String correctPassword = "1234";
		if(password.equals(correctPassword)) {
			session.setAttribute("isAdmin", "true");
		}else {
			redirectAttr.addFlashAttribute("errorMessage","Incorrect Password"); //DispatcherServlet이 관리하는 FlashMap에 값을 저장
			return "redirect:/loginForm";
		}
		
		
		return "redirect:/list";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		
		session.removeAttribute("isAdmin");
		return "redirect:/list";
	}
}

package com.junu.spring.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.junu.spring.guestbook.dto.User;

@Controller
@SessionAttributes("user")
public class LoginController {
	
	@PostMapping("/dologin")
	public String doLogin(@ModelAttribute("user")User user, Model model) {
		return "";
	}
}

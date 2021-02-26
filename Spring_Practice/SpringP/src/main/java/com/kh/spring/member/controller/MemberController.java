package com.kh.spring.member.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.common.exception.CommonException;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.service.MemberServiceImpl;
import com.kh.spring.member.model.vo.Member;

/*
 * 빈 스캐닝을 통해 자동으로 
 * 어노테이션을 명시한 클래스들이 빈으로 등록됨
 * 
 * Controller, Service, Repository 등등은 Component를 기능에 따라 분류한 것
 * 모두 다 ComponentScan의 대상
 * 
 * */

@SessionAttributes({"loginUser"}) //Model에 loginUser라는 key값으로 객체가 추가되면, 자동으로 세션에 추가하라는 어노테이션
@Controller //해당 클래스가 컨트롤러로 기능할 것임을 명시
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//파라미터(요청시 전달값)를 전송 받는 방법 
	
	/*
	 * 1. HttpServletRequest를 통해 전송받기
	 * 	-> 기존의 servlet/jsp 방식을 말함
	 * 
	 * */
	
	/*
	
	@RequestMapping(value="login.me", method=RequestMethod.POST) //RequestMapping 어노테이션의 파라미터로, 매핑할 url을 넣어줌(HandlerMapping 등록)
	//따라서 login.me로 요청이 들어오면 DispatcherServlet이 HandlerMapping을 통해 어떤 컨트롤러로 매핑시킬지 후, 컨트롤러 클래스에서 loginMember 메소드를 호출하여 요청을 처리하는 것
	public String loginMember(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		System.out.println("로그인 -> ID : "+userId+", PWD : "+userPwd);
		return "main"; //요청 처리 결과를 적용할 view 파일의 이름을 리턴(문자열)
		//리턴되는 문자열은 servlet-context.xml 파일에 기술된 ViewResolver에 의해 사용자가 보게될 뷰로 포워딩됨
	}	
	
	*/
	
	/*
	 * 2. @RequestParam 어노테이션을 통해, 요청시 들어온 파라미터값을 가져오기 
	 * */
	/*
	@PostMapping(value="login.me")
	public String loginMembe(@RequestParam("userId")String userId, @RequestParam("userPwd")String userPwd) {
		System.out.println("@RequestParam 어노테이션 활용");
		System.out.println("로그인 -> ID : "+userId+", PWD : "+userPwd);
		
		return "main";
	}
	*/
	
	/*
	 * 3. @RequestParam 어노테이션을 생략할 수도 있음
	 * 
	 * */
	
	/*
	@PostMapping(value="login.me")
	public String loginMembe(String userId, String userPwd) {
		System.out.println("@RequestParam 어노테이션 생략");
		System.out.println("로그인 -> ID : "+userId+", PWD : "+userPwd);
		
		return "main";
	}
	*/
	
	/*
	 * 4. @ModelAttribute 어노테이션 활용 
	 * 	-> RequestParam과 달리, 객체 타입으로 파라미터를 받아올 때 사용 
	 * */
	/*
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public String loginMember(@ModelAttribute Member member) {
		System.out.println("@ModelAttribute 확인");
		System.out.println("ID : "+member.getUserId());
		System.out.println("PWD : "+member.getUserPwd());
		
		return "main";
	}
	*/
	
	/*
	 * 5. @ModelAttribute 생략하고 객체 전달 받는 방식
	 * 
	 * */
	/*
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public String loginMember(Member member, HttpSession session) throws Exception {
	
		System.out.println("ID : "+member.getUserId());
		System.out.println("PWD : "+member.getUserPwd());	
		
		Member loginUser = memberService.loginMember(member);
		
		if(loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			System.out.println(loginUser);
			return "redirect:/"; //index.jsp로 재연결
		}else {
			return "common/errorPage";
		}
	}
	*/
	
	
	// 응답 데이터가 있는 경우
	/*
	 * 1. Model 이라는 객체를 사용하는 방법
	 * */
	/*
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public String loginMember(Member member, HttpSession session, Model model) {
		Member loginUser;
		
		try {
			loginUser = memberService.loginMember(member);
			
			if(loginUser == null) {
				throw new Exception("login failed");
			}
			
			session.setAttribute("loginUser", loginUser);
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			//Key,Value 한 쌍으로 Model에 속성 추가함
			model.addAttribute("msg","login failed");
			return "common/errorPage";
		}
	}
	*/
	
	/*
	 * 2. ModelAndView 객체를 사용하는 방법
	 * */
	/*
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public ModelAndView loginMember(Member member, HttpSession session, ModelAndView mv) {
		
		Member loginUser;
		try {
		
			loginUser = memberService.loginMember(member);
			
			if(loginUser == null) {
				throw new Exception("login failed");
			}
			
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg","login failed");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}	
	*/
	
	// 로그아웃
	/*
	@RequestMapping(value="logout.me")
	public String logoutMember(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	*/
	
	/*
	 * 3. SessionAttribute 어노테이션 사용(클래스 선언부 상단에 추가)
	 * */
	/*
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public String loginMember(Member member, Model model) {
		
		Member loginUser;
		
		try {
			loginUser = memberService.loginMember(member);
			
			if(loginUser == null) {
				throw new Exception("login failed");
			}
			
			model.addAttribute("loginUser", loginUser);
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			//Key,Value 한 쌍으로 Model에 속성 추가함
			model.addAttribute("msg","login failed");
			return "common/errorPage";
		}
	}
	*/
	
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public String loginMember(Member member, Model model) {
		Member loginUser;
		
		try {
			loginUser = memberService.loginMember(member,bCryptPasswordEncoder);
			
			model.addAttribute("loginUser", loginUser);
			return "redirect:/";
			
		} catch (Exception e) {
			e.printStackTrace();
			//Key,Value 한 쌍으로 Model에 속성 추가함
			model.addAttribute("msg","login failed");
			return "common/errorPage";
		}		
	}
	
	@RequestMapping(value="logout.me")
	public String logoutMember(SessionStatus status) {
		status.setComplete(); //세션을 무효화하는 것이 아닌, 상태관리를 통한 접근
		return "redirect:/";
	}
	
	@RequestMapping(value="enrollForm.me")
	public String enrollForm() {
		return "member/memberEnrollForm";
	}
	
	@RequestMapping(value="insert.me", method=RequestMethod.POST)
	public String insertMember(@ModelAttribute Member member, @RequestParam("post")String post,
															  @RequestParam("address1")String address1,
															  @RequestParam("address2")String address2,
															  HttpSession session) throws Exception {
		
		member.setAddress(post+"/"+address1+"/"+address2);
		System.out.println(member);
		
		//암호화 작업
		System.out.println("암호화 이전 비밀번호 "+member.getUserPwd());
		String encPwd = bCryptPasswordEncoder.encode(member.getUserPwd());
		System.out.println("암호화 이후 비밀번호 "+encPwd);
		
		//암호화된 패스워드로 교체
		member.setUserPwd(encPwd);
		
		int result = memberService.insertMember(member);
		
		if(result>0) {
			session.setAttribute("msg", "회원가입 성공");
			return "redirect:/";
		}else {
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping(value="myPage.me")
	public String updateForm() {
		
		return "member/memberUpdateForm";
	}
	
	@RequestMapping(value="update.me", method=RequestMethod.POST)
	public String updateMember(@ModelAttribute Member member, @RequestParam("post")String post,
															  @RequestParam("address1")String address1,
															  @RequestParam("address2")String address2, Model model) {
		
		member.setAddress(post+"/"+address1+"/"+address2);
		int result = memberService.updateMember(member);
		
		if(result>0) {
			model.addAttribute("loginUser",member);
			return "member/memberUpdateForm";
		}else {
			throw new CommonException("회원정보 수정 실패");
			
		}
	}
	
	@RequestMapping(value="delete.me")
	public String deleteMember(String userId) {
		
		int result = memberService.deleteMember(userId);
		
		if(result>0) {
			return "redirect:logout.me";
		}else {
			throw new CommonException("회원탈퇴 실패");
			
		}
	}
	
	@RequestMapping(value="checkPwd.me",method=RequestMethod.POST)
	@ResponseBody
	public String checkPwd(@RequestParam("inputPwd")String inputPwd, HttpServletResponse response, HttpSession session) throws Exception {
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		PrintWriter out = response.getWriter();

		if(bCryptPasswordEncoder.matches(inputPwd,loginUser.getUserPwd())) {
			out.print("success");
		}else {
			out.print("fail");
		}	
		
		return null;
	}
	
	@RequestMapping(value="updatePwd.me", method=RequestMethod.POST)
	public String updatePassword(@RequestParam("newPwd")String newPwd, HttpSession session) {
		
		System.out.println("update 호출");
		Member loginUser = (Member)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		String encPwd = bCryptPasswordEncoder.encode(newPwd);
		
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(encPwd);
		
		int result = memberService.updatePassword(member);
		if(result>0) {
			loginUser.setUserPwd(encPwd);
			return "redirect:/myPage.me";
		}else {
			throw new CommonException("비밀번호 변경 실패");
		}
		
	}
	
	@RequestMapping("idCheck.me")
	@ResponseBody
	public String checkId(String userId) {
		
		int count = memberService.idCheck(userId);
		
		return String.valueOf(count);
	}
	
}

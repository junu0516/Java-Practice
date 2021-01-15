package com.junu.springboot;

import com.junu.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@RunWith(SpringRunner.class) //테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴(스프링 부트 테스트와 JUnit 사이의 연결자 역할)
@WebMvcTest(controllers = HelloController.class) //스프링 테스트 어노테이션 중 Web(Spring MVC)에 집중
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; //웹 API를 테스트할 때 사용(HTTP GET, POST 등에 대한 테스트), 스프링 MVC 테스트의 시작점이 됨

    @Test
    public void hello_returned() throws Exception{
        String hello = "hello";

        /*
        * MockMvc를 통해 /hello url로 GET 요청 보냄
        * mvc.perform의 결과를 검증, HTTP Header의 Status 검증(200,404,500 ...)
        * mvc.perform의 결과를 검증, 응답 본문의 내용을 검증(Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는 지를 검증하는 것)
        * */
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @Test
    public void helloDto_returned() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")//경로가 제대로 설정되지 않으면 404 Not Found 뜸에 주의
                .param("name",name)
                .param("amount",String.valueOf(amount))) //API 테스트할 때 사용될 요청 파라미터를 설정, String만 허용됨
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) //JSON 응답값을 필드별로 검증, $를 기주능로 필드명 암시
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}

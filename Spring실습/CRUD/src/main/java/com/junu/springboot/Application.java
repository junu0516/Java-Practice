package com.junu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication/*어노테이션으로 인해 스프링 부트의 자동 설정, 스프링 빈 읽기와 생성이 모두 자동으로 설정됨*/
/*SpringBootApplication이 있는 위치부터 설정을 읽기 때문에 Application 클래스는 항상 프로젝트의 최상단에 위치*/
public class Application { /*앞으로 만들 프로젝트의 메인 클래스*/
    public static void main(String[] args){
        SpringApplication.run(Application.class,args); //만약 localhost:8080/hello로 접속했을 때 로그인 팝업이 뜨면 오라클 실행 종료할 것!(포트번호 충돌나는 상황인 것)
        /*
        * SpringApplication.run
        * 내장 WAS를 실행(외부에 WAS를 두지 않고 어플리케이션을 실행할 때 내부에서 WAS를 실행)
        * 서버에 항상 톰캣을 설치할 필요 없이 스프링 부트로 만들어진 Jar 파일을 실행하기 때문에 더 편리
        * (외장 WAS를 사용할 경우 모든 서버는 WAS의 종류와 버전, 설정을 일치시켜야 하는 번거로움이 있음)
        * */
    }
}

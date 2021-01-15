package com.junu.springboot.web.dto;

import com.junu.springboot.web.dto.HelloResponseDto;
import org.junit.Test;



import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void lombokTest(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        //assertj라는 테스트 검증 라이브러리의 검증 메소드
        //검증하고 싶은 대상을 매개변수로 받음, isEqualTo와 같이 메소드를 이어서 사용
        assertThat(dto.getName()).isEqualTo(name); 
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}

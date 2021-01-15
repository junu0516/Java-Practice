package com.junu.springboot.web.dto;

import com.junu.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
//Dto에서 Request 데이터를 받는 역할 수행
/*
* Entity 클래스와 유사한 형재이지만,
* Entity 클래스를 Request/Response 클래스로 사용할 수 없음
*    -> Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이기 때문(수많은 서비스 클래스, 비즈니스 로직이 Entity 클래스를 기준으로 동작)
*    -> Entity를 기준으로 테이블이 생성되고, 스키마가 변경됨 / Entity가 변경되면 여러 클래스에 영향을 끼침
*    -> 보통, Request/Response용 Dto는 MVC의 View를 위한 것이기 때문에 자주 변경이 필요함 ...
*    -> 따라서 Request/Response로 쓰면서 화면 하나 변경하기 위해서 테이블과 연결된 Entity를 변경하는 것은 너무 과한 것
*
* 결국, View Layer와 DB Layer 간의 분리를 철저히 하는 것이 좋다는 것!
* */
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

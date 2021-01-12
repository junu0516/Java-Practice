package com.junu.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired //IoC 컨테이너 안에 존재하는 Bean을 자동으로 주입
    PostsRepository postsRepository;

    @After //junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void selectList(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        //테이블 posts 에 있는 insert/update 쿼리를 실행
        //id값이 있으면 update가, 없으면 insert 쿼리가 실행됨
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("junu0516@yonsei.ac.kr")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); //테이블에 존재하는 모든 데이터를 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
package com.junu.springboot.web;

import com.junu.springboot.service.posts.PostsService;
import com.junu.springboot.web.dto.PostsResponseDto;
import com.junu.springboot.web.dto.PostsSaveRequestDto;
import com.junu.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor //final이 선언된 모든 필드를 인자값으로 하는 생성자를 해당 롬복 어노테이션이 대신 생성해줌
//생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과를 볼 수 있음
@RestController
//Controller에서 API 요청을 받는 것
/*
*
* 다시 언급하지만 롬복을 사용하는 이유는,
* 클래스 간 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 줄이는 것
* 
*  */
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}

package com.junu.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JDBC 사용시의 DAO와 비슷한 역할로, JPA에서는 Repository라고 부르며 인터페이스로 생성함
@Repository
public interface PostsRepository extends JpaRepository<Posts,Long> {
    //제네릭 명시는 <Entity 클래스명, PK 타입>으로 선언 -> 기본적인 CRUD 메소드 자동으로 생성됨

}
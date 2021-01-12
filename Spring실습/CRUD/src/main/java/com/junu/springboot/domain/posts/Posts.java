package com.junu.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*주요 어노테이션은 클래스에 가깝게 두는 것이 좋음*/
@Getter //롬복의 어노테이션
@NoArgsConstructor //롬복의 어노테이션
@Entity //JPA의 어노테이션, 테이블과 링크될 클래스임을 나타냄
public class Posts {
    //Posts 클래스 : 실제 DB의 테이블과 매칭될 클래스(Entity)

    @Id //해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙을 나타냄
    /*springboot 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야 auto_increment가 됨*/
    private Long id; //PK는 복잡한 형태보다 단순한 Long으로 해야 나중에 외래키와 매칭할 때 편함

    @Column(length = 500, nullable = false) //테이블의 칼럼을 나타내며, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 되긴 함
    //기본값 외에 추가로 변경이 필요한 옵션이 있을 경우 해당 어노테이션을 사용(ex 칼럽의 탕비을 바꾸거나 사이즈를 늘리고자 할 경우)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성, 생성자의 상단에 선언할 경우 생성자에 포함된 필드만 빌더에 포함됨
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /*
     * 추가
     * 일반적인 자바 클래스와 달리 여기서는 getter 메소드만 있고, setter 메소드는 없음
     * 나중에 코드가 너무 복잡해지는 것을 방지하고자 Entity 클래스에서는 setter 메소드를 만들지 않음
     * 대신, 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가함
     *
     * 그렇다면 setter 없이 값을 채우고자 할 경우에는?
     * --> 생성자를 통해 최종값을 채운 후 DB에 삽입하는 것이 기본적이며,
     *     변경이 필요한 경우에는 해당 이벤트에 맞는 메소드를 호출하여 변경
     * */
}
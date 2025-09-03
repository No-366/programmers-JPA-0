package com.back.domain.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter // 게터와 세터를 자동으로 만들어준다 : 이름을 만드는 규칙 -> 앞에 get 이나 set 이 붙고 대문자로 시작
@Setter
@ToString
@Entity //-> JPA가 이 어노테이션을 보고 해당 클래스와 닮은 테이블을 만들어준다
//@AllArgsConstructor
@RequiredArgsConstructor // 고정이 필요한 필드 변수에 final 키워드를 붙이면 생성자로 만들어준다
public class Post {
    @Id // -> JPA야 id를 PK로 삼고
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) // -> JPA야 autoIncrement를 걸어라
    private int id;
    private final String title;
    @Column(columnDefinition = "TEXT")
    private final String content;

    //public Post() {} // 아래와 같이 특수한 생성자를 만들면 자바가 더이상 기본 생성자를 따로 만들어주지 않기 때문에

    // 이렇게 직접 생성자를 정의하지 않고 @AllArgsConstructor을 이용해서 자동으로 생성할 수도 있다
    public Post() {
        this.title = "고정된 값";
        this.content = "고정된 값";
    }

}


package com.back.domain.post.repository;

import com.back.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//이것은 스프링과 통합이 되어있기 때문에 자동으로 Bean에 등록된다
public interface PostRepository extends JpaRepository<Post, Integer> {
                                                     // < 다룰 데이터 객체 타입, 다룰 객체의 Id 타입(PK역할)  >


}

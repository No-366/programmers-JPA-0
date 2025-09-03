package com.back.domain.repository;

import com.back.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
                                                     // < 다룰 데이터 객체 타입, 다룰 객체의 Id 타입(PK역할)  >
}

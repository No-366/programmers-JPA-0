package com.back.domain.post.repository;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // 어떤 yml을 사용할지 선택
public class PostRepositoryTest {

    // 테스트에서는 생성자주입을 쓸 수 없음 -> 룰이 그렇다 함
    // Autowired를 통해 필드주입을 해야함
    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("2번 글 조회")
    @Transactional
    @Rollback
    void t1(){
        Post post1 = postRepository.findById(2).get();

        // 스프링부트에서는 의존성 추가 하지 않고도 기본으로 제공 됨
        assertThat(post1.getId()).isEqualTo(2);
        assertThat(post1.getTitle()).isEqualTo("테스트용 제목2");
        assertThat(post1.getContent()).isEqualTo("테스트용 내용2");
    }

    @Test
    @DisplayName("글 생성")
    @Transactional
    @Rollback
    void t2(){

        Post newPost = new Post("new 제목", "new 내용");

        assertThat(newPost.getId()).isNull();

        Post savePost = postRepository.save(newPost);

        assertThat(savePost.getId()).isNotNull();
        assertThat(newPost.getTitle()).isEqualTo("new 제목");
        assertThat(newPost.getContent()).isEqualTo("new 내용");

    }

//    @Test
//    @DisplayName("글 생성2")
//    void t3(){
//
//        Post newPost = new Post("new 제목", "new 내용");
//
//        assertThat(newPost.getId()).isNull();
//
//        Post savePost = postRepository.save(newPost);
//
//        assertThat(savePost.getId()).isNotNull();
//        assertThat(newPost.getTitle()).isEqualTo("new 제목");
//        assertThat(newPost.getContent()).isEqualTo("new 내용");
//
//    }

    @Test
    @DisplayName("글 개수 조회")
    void t4(){
        long cnt = postRepository.count();
        assertThat(cnt).isEqualTo(2L);
    }

}

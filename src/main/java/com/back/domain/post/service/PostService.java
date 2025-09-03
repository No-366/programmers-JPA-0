package com.back.domain.post.service;


import com.back.domain.post.entity.Post;
import com.back.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostService {

    //@Autowired 생성자로 이미 스프링에 연결되어있으므로 Autowired 에너테이션은 필요 없다
    private final PostRepository postRepository;

    public void write(String title, String content) {

        if(title.length()<10){
            throw new IllegalArgumentException("Title must be at least 10 characters long.");
        }

        Post post = new Post(title, content);
        postRepository.save(post);
    }

    public Optional<Post> getPost(int id) {
        return postRepository.findById(id); // findById() 메서드는 Optional을 반환한다
    }

    public long getTotalCount(){
        return postRepository.count();
    }
}

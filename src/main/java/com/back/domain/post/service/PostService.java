package com.back.domain.post.service;


import com.back.domain.post.entity.Post;
import com.back.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostService {

    //@Autowired 생성자가 하나만 있는 경우에는 Autowired를 쓰지 않아도 스프링이 자동으로 주입해준다
    private final PostRepository postRepository;

    public void write(String title, String content) {

//        if(title.length()<10){
//            throw new IllegalArgumentException("Title must be at least 10 characters long.");
//        }

        Post post = new Post(title, content);
        postRepository.save(post);
    }

    public void modify(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content);

        boolean isChanged = false;

        if(!post.getTitle().equals(title)){
            post.setTitle(title);
            isChanged = true;
        }

        if(isChanged){
            post.setModifyDate(LocalDateTime.now());
        }
        postRepository.save(post);
    }

    public Optional<Post> getPost(int id) {
        return postRepository.findById(id); // findById() 메서드는 Optional을 반환한다
    }

    public long getTotalCount(){
        return postRepository.count();
    }

    public void delete(Post post){
        postRepository.delete(post);
    }
}

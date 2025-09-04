package com.back.global.initData;

import com.back.domain.post.entity.Post;
import com.back.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/*
    [ BaseInitData 클래스 ( 이름은 그냥 임의 설정 ) ]
    스프링부트 애플리케이션이 실행된 “이후”에 초기 동작을 수행하는 설정 클래스 (스프링 부트 시작점은 아님)
    스프링 컨테이너 초기화가 끝난 뒤 실행되는 콜백 인터페이스
    보통 초기 데이터 세팅(DB에 샘플 데이터 넣기 등)에 사용됨

    [ ApplicationRunner 구현체]
    스프링 부트 애플리케이션이 완전히 시작된 후 실행되는 인터페이스입니다

    run() 메서드를 구현하여 애플리케이션 시작 시 필요한 초기화 작업을 수행할 수 있습니다

    여러 개의 ApplicationRunner 빈을 등록할 수 있으며, @Order 어노테이션으로 실행 순서를 지정할 수 있습니다

    CommandLineRunner와 유사하지만, ApplicationArguments를 통해 더 다양한 형태의 커맨드 라인 인자를 처리할 수 있습니다

    주로 데이터 초기화, 외부 서비스 연결, 캐시 설정 등 애플리케이션 시작 시 필요한 작업에 사용됩니다
* */

// 그냥 이 포맷은 받아들이라고 하심 from 강사님

@Service// Configuration와 Service는 Component를 기반으로 만든것임
@RequiredArgsConstructor
public class BaseInitData {//초기 데이터 세팅용 코드

//    @Autowired // 이 애너테이션을 통해 스프링으로부터 Bean 객체를 주입받는다
//    private PostRepository postRepository;

    //@Autowired
    private final PostService postService;

    @Autowired
    @Lazy
    private BaseInitData self;

//    //생성자 주입 // Autowired가 이 역할을 대신 해줌
//    public BaseInitData(PostService postService) {
//        this.postService = postService;
//    }


    @Bean // -> 이게 있어야 스프링이 initDataRunner()의 리턴 객체를 Bean으로 등록한다
    ApplicationRunner initDataRunner(){ //어플리케이션이 실행될 때
        return args -> {
            System.out.println("어플리케이션을 실행하였습니다");

            self.work1();
            self.work2();

//            new Thread(()->{
//                self.work3();
//            }).start();
            self.work4();


        };
    }

    // 초기에 아무 데이터가 없을 경우 샘플 데이처을 넣는 메서드
    // 데이터가 이미 들어있을 경우 실행 안함
    // 생성
    @Transactional
    void work1(){

        if(postService.getTotalCount() > 0){
            return;
        }

        // 서비스를 도입해서 비즈니스 로직을 재사용
        postService.write("제목1", "내용1");
        postService.write("제목2", "내용2");

//        Post post1 = new Post("제목1", "내용1");
//        //비즈니스 로직
//        postRepository.save(post1);
//
//        Post post2 = new Post("제목2", "내용2");
//        //비즈니스 로직
//        postRepository.save(post2);
    }
    //조회
    @Transactional(readOnly = true)
    void work2(){

        Optional<Post> opPost =  postService.getPost(1); // 없을 수도 있으니까 Optional 타입 사용
        //postRepository.findById(8); // select * from post where id=1;
    }

    //삭제
    @Transactional
    void work3(){
        Post post1 = postService.getPost(1).get();
        Post post2 = postService.getPost(2).get();

        postService.delete(post1);

        if(true) throw new RuntimeException("테스트용 예외 발생");

        postService.delete(post2);
    }

    //수정
    void work4(){
        Post post1 = postService.getPost(1).get();

        postService.modify(post1, "제목1 - 수정", "내용1-수정");

    }

}

/*
* 사용자와 상호작용 : 컨트롤러
* 비즈니스 로직 : 서비스
* 데이터 저장, 가져오기 : 레포지토리
*
* */
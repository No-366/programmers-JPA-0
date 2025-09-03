package com.back.global.initData;

import com.back.domain.post.entity.Post;
import com.back.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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

@Configuration
public class BaseInitData {//초기 데이터 세팅용 코드

    @Autowired // 이 애너테이션을 통해 스프링으로부터 Bean 객체를 주입받는다
    private PostRepository postRepository;


    @Bean // -> 이게 있어야 스프링이 initDataRunner()의 리턴 객체를 Bean으로 등록한다
    ApplicationRunner initDataRunner(){ //어플리케이션이 실행될 때
        return args -> {
            System.out.println("초기 데이터를 로딩합니다.");

            Post post = new Post("제목2", "내용2");
//            post.setTitle("제목 1");
//            post.setContent("내용 1");
            postRepository.save(post);

            //postRepository.count();

            postRepository.findById(1); // select * from post where id=1;
        };
    }
}

/*
* 사용자와 상호작용 : 컨트롤러
* 비즈니스 로직 : 서비스
* 데이터 저장, 가져오기 : 레포지토리
*
* */
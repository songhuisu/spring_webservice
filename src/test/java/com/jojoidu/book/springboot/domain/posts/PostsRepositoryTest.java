package com.jojoidu.book.springboot.domain.posts;

import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * springData Jpa 테스트 코드
 *
 * @After
 * Junit에서 단위테스트가 끝날 때마다 수행되는 메소드를 지정
 * 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기위해 사용
 *
 * 여러테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아있어 다음 테스트 실행 시 테스트 실패
 *
 * postRepository.save
 * 테이블 posts에 insert/update 쿼리를 실행
 * id값이 있다면 update, 없다면  insert 쿼리 실행
 *
 * postRepository.findAll
 * 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
 *
 * */

@RunWith(SpringRunner.class)
@SpringBootTest     // H2데이터베이스 자동 실행
public class PostsRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After //1
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepository.save(Posts.builder()     //2
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        //when
        List<Posts> postsList = postRepository.findAll();   //3

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){

        //given
        LocalDateTime now = LocalDateTime.of(2023,4,13,0,0,0);
        postRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}

package com.jojoidu.book.springboot.service.posts;

import com.jojoidu.book.springboot.domain.posts.PostRepository;
import com.jojoidu.book.springboot.domain.posts.Posts;
import com.jojoidu.book.springboot.web.dto.PostSaveRequestDto;
import com.jojoidu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoidu.book.springboot.web.dto.PostsResponseDto;
import com.jojoidu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


/**
 * @RequiredArgsConstructor
 * 생성자 어디?
 * final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성
 *
 * 생성자를 직접 안쓰고 롬복 어노테이션 쓴 이유는 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움 해결
 * */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postRepository;

    @Transactional
    public long save(PostSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해딩 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }       //findAllDesc 메소드의 @Transaction 옵션이 추가 readOnly남기면 트랜잭션 범위는 유지되고 조회기능만 남겨 조회속도 개선 CURD전혀 없는 서비스 메소드 사용 추천
            //.map(PostsListResponseDto::new) 실제로는 .map(posts -> new PostsListResponseDto(posts) postsRepository결과로 넘어온 posts의stream을 map을 통해 PostsListResponseDto변환 ->list

    @Transactional
    public void delete(Long id){
        Posts posts = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" +id));

        postRepository.delete(posts);
    }
}

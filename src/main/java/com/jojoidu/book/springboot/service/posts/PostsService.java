package com.jojoidu.book.springboot.service.posts;

import com.jojoidu.book.springboot.domain.posts.PostRepository;
import com.jojoidu.book.springboot.domain.posts.Posts;
import com.jojoidu.book.springboot.web.dto.PostSaveRequestDto;
import com.jojoidu.book.springboot.web.dto.PostsResponseDto;
import com.jojoidu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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
}

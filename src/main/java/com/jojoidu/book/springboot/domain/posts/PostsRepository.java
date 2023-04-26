package com.jojoidu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao라고 불리는 DB Layer 접근자
 * JPA에선 레포지토리라고 부르며 인터페이스 생성
 *
 * JpaRepository<Entity클래스, PK타입> 상속하면 기본적인 CRUD메소드가 자동으로 생성
 *
 * Entity클래스와 기본 Entity Repository는 함께 위치
 * 밀접한 관계이고, Entity 클래스는 기본 Repository 없이는 제대로 역할 할 수 없음
 *
 * 도메인 패키지에서 함께 관리
 *
 * index머스테치 1> repository2 > PostsService3
 * */

// Posts클래스로 Database를 접근하게 해줄 JpaRepository 생성
    //
public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();      // springdatajpa 에서 제공하는 기본메소드만으로 해결 가능

}

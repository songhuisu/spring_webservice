package com.jojoidu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JPA Auditing 어노테이션들을 모두 활성화
 * */

//@EnableJpaAuditing  //JPA Auditing 활성화
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 bean 읽기,생성 모두 자동 ,,, 여기부터 설정을 읽어감 항상 프로젝트 상단 위치
public class Application {

    // 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);      // WAS 실행 .. 톰캣 설치x jar파일 (실행가능한 Java패키징 파일)로 실행
        // 내장 WAS를 사용하는 것 권장 was종류와 버전 설정을 일치시킬필요 없음
    }
}
package com.jojoidu.book.springboot.domain.posts;

import com.jojoidu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 주요 어노테이션
 *
 * @Entity 테이블과 링크될 클래스
 * 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭
 * ex) SalesManager.java > sales_manager table
 *
 * @Id 해당 테이블의 PK필드 나타냄
 *
 * @GeneratedValue PK의 생성규칙
 * 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨
 *
 * @Column 테이블의 칼럼을 나타냄
 * 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
 * 문자열의 사이즈를 늘리거나 타입을 변경하거나 등등
 *
 * Entity의 PK는 Long타입의 Auto_increment를 추천 유니크키나, 여러키를 조합한 복합키로 pk를 잡을 경우
 *
 * 유니크한 조건이 변경될 경우 pk전체를 수정해야 하는 일이 발생합니다.
 * 주민등록번호, 복합키 등 유니크 키로 별도로 추가하는 것 추천
 *
 *
 * (Lombok)
 * @NoArgsConstructor 기본 생성자 자동 추가
 * public Posts() {}
 *
 * @Getter 클래스 내 모든 필드의 Getter메소드를 자동 생성
 *
 * @Builder 해당 클래스의 빌더 패턴 클래스를 생성
 * 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
 *
 * Entity 클래스에선 절대 Setter메소드 만들지 않음.
 *
 * */

@Getter     //6(Lombok)
@NoArgsConstructor      //5 (Lombok)
@Entity //1 Entity는 JPA의 어노테이션, @Getter와 @NoArgsConstructor는 롬복의 어노테이션
public class Posts extends BaseTimeEntity {    // 실제 DB테이블과 매칭될 클래스이며 보통 Entity클래스 라고 함. 실제쿼리를 날리는것보단, 이 클래스를 수정을 통해 작업한다.

    @Id //2
    @GeneratedValue(strategy = GenerationType.IDENTITY) //3
    private Long id;

    @Column(length = 500, nullable = false)     //4
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        //7(Lombok)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}

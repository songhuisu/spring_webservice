package com.jojoidu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate 자동관리 역할
 *
 * @MappedSuperclass    JPA Entity 클래스들이 BaseTimeEntity 상속할 경우 필드들도 칼럼으로 인식
 *
 * @EntityListeners     BaseTimeEntity 클래스에 Auditing 기능을 포함
 *
 * @CreateDate     Entity가 생성되어 저장될 때 시간이 자동 저장된다.
 *
 * @LastModifiedDate       조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
 * */
@Getter
@MappedSuperclass   // 1
@EntityListeners(AuditingEntityListener.class)      //2
public class BaseTimeEntity {

    @CreatedDate    //3
    private LocalDateTime createdDate;

    @LastModifiedDate   //4
    private LocalDateTime modifiedDate;


}

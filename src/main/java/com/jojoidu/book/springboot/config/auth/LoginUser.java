package com.jojoidu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target(ElementType.PARAMETER)
 * 이 어노테이션이 생성될 수 있는 위치를 지정
 * PARAMETER로 지정했으니 메소드이 파라미터로 선언된 객체에서만 사용할 수 있음
 * 이 외에도 클래스 선어문에 쓸 수 있는 TYPE등이 있음
 *
 * @interpace이 파일을 어노테이션 클래스로 지정
 * LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면 됨
 * */
@Target(ElementType.PARAMETER)      //1
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {   //2
}

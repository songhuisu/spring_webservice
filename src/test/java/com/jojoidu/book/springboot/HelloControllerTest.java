package com.jojoidu.book.springboot;

import com.jojoidu.book.springboot.config.auth.SecurityConfig;
import com.jojoidu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트 코드 검증 was실행하지 않고 ,,
@RunWith(SpringRunner.class)    // 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴 springbootrunner 실행자 사용 스프링부트 테스트와 JUnit사이 연결
@WebMvcTest(controllers = HelloController.class,
excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes = SecurityConfig.class)
})    // Web(springweb MVC)에 집중할 수 있는 어노테이션 @Controller, @ControllerAdvice 사용 @service, component, repository 사용 x
// 컨트롤러만 사용하기 때문에 선언
public class HelloControllerTest {

    @Autowired      // 스프링이 관리하는 Bean주입 받음
    private MockMvc mvc;        // 웹API 테스트할 때 사용. 테스트 시작점 . HTTP GET,POST api 테스트 할 수 있음

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))      // MockMvc 를 통해 /hello 주소로 HTTP GET요청함
                .andExpect(status().isOk())     //mvc perform 결과 검증 Http Header Status 검증 200,404,500
                .andExpect(content().string(hello)); //응답 본문의 내용을 검증 값 겁증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name",name)                     // API 테스트할때 사용될 요청 파라미터를 설정 String만 허용, 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))       //Json 응답값을 필드로 검증할 수 있는 메소드 $기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));   // name과 amount검증
    }
}

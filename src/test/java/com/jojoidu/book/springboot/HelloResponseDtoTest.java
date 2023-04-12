package com.jojoidu.book.springboot;

import static org.assertj.core.api.Assertions.assertThat;
import com.jojoidu.book.springboot.web.dto.HelloResponseDto;
import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat (dto.getName()).isEqualTo(name);      //테스트 검증 라이브러리의 검증메소드 검증하고싶은 대상을 메소드 인자로 받음
        assertThat (dto.getAmount()).isEqualTo(amount);  //assertj의 동등 비교메소드

    }
}

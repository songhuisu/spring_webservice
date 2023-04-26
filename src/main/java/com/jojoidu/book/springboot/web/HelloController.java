package com.jojoidu.book.springboot.web;


import com.jojoidu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController     //컨트롤러를 Json을 반환하는 컨트롤러로 만듬 @ResponseBody를 각 메소드마다 선언을 한번에
public class HelloController {

    @GetMapping("/hello")   // HTTP Method인 GET요청을 받을 수 있는 API만듬  @RequestMapping(method = ReQuestMethod.GET)
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")   //@RequestParam 외부에서 API로 넘긴 파라미터 가져오는 어노테이션 외부에서 name@RequestParam("name")이란 이름으로 넘긴 파라미터를 메소드 name(String name)에 저장
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}

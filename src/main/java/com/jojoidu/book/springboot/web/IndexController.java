package com.jojoidu.book.springboot.web;

import com.jojoidu.book.springboot.service.posts.PostsService;
import com.jojoidu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")    //머스테치 스타터로 컨트롤러에서 문자열 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 저장 // src/main/resources/templates 파일 확장자 .mustache
    public String index(Model model){  //index 반환하므로 View Resolver가 처리.... Model 서버템플릿엔진에서 사용할 수 있는 객체 저장 .. postsService.findAllDesc()로 가져온 결과를 posts로 index머스테치 전달

        model.addAttribute("posts",postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }

}



package com.xss.xss;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class XssController {

    @GetMapping(path = "/xss-reflected")
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        // 사용자로 부터 받은 키워드를 그대로 HTML 에 삽입한다.
        // 다음과 같은 url 접속 시 url에 삽입된 스크립트가 실행된다.
        // http://localhost:8080/xss-reflected?keyword=<script>alert("당신의 컴퓨터는 해킹당했습니다!")</script>
        model.addAttribute("keyword", keyword);
        return "xss-reflected";
    }
}

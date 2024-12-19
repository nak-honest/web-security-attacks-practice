package com.xss.xss;

import com.xss.xss.dto.XssRequest;
import com.xss.xss.dto.XssResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XssController {
    // </script><script>alert("당신의 컴퓨터는 해킹당했습니다!")</script><script>

    /*
    사용자로 부터 받은 키워드를 그대로 HTML 에 삽입한다.
    다음과 같은 url 접속 시 url에 삽입된 스크립트가 실행된다.
    http://localhost:8080/xss-reflected-for-attack?keyword=<script>alert("당신의 컴퓨터는 해킹당했습니다!")</script>

    로그인 후, 다음 링크로 접속하면 액세스 토큰이 alert 되는 것을 확인할 수 있다.
    http://localhost:8080/xss-reflected-for-attack?keyword=<script>alert(decodeURIComponent(document.cookie.split('; ').find(row => row.startsWith('token='))?.split('=')[1]));</script>
     */
    @GetMapping(path = "/xss-reflected-for-attack")
    public String attackXssReflected(Model model,
                                     @RequestParam(name = "keyword", required = false) String keyword) {

        model.addAttribute("keyword", keyword);
        return "xss-reflected-for-attack";
    }

    @GetMapping(path = "/xss-reflected-for-defend")
    public String defendXssReflected(Model model,
                                     @RequestParam(name = "keyword", required = false) String keyword) {

        model.addAttribute("keyword", keyword);
        return "xss-reflected-for-defend";
    }

    /*
    아래 url로 접속해서 다음을 입력하면 공격에 성공한다.
    <script>alert(decodeURIComponent(document.cookie.split('; ').find(row => row.startsWith('token='))?.split('=')[1]));</script>
     */
    @GetMapping(path = "/xss-reflected-by-json")
    public String xssReflectedByJson() {
        return "xss-reflected-by-json";
    }

    @PostMapping(path = "/xss-attack")
    @ResponseBody
    public ResponseEntity<XssResponse> attackXssReflectedByJson(@RequestBody XssRequest request) {
        System.out.println(request.message());
        return ResponseEntity.ok(new XssResponse(request.message()));
    }
}

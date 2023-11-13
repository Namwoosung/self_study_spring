package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//constroller로서 사용할 class는 @controller로 notation 지정을 해줘야 함
@Controller
public class HelloController {
    @GetMapping("hello") //web application에서 /hello로 get method가 들어오면 아래 함수를 호출해라고 notation
    public String hello(Model model){
        //model은 이 메소드가 실행될 때, spring이 만들어서 넣어줌
        //model의 attribute를 추가, key는 data, value는 hello!!
        model.addAttribute("data", "hello!!");
        //return hello의 의미는 hello.html을 찾아서 redering하라는 의미
        //spirng booter는 resource에 template에 아래에 있는 return 값을 찾아서 redering함
        return "hello";
    }
    //위의 Model은 MVC 모델에서 model, view, controller에서 model에 해당

}

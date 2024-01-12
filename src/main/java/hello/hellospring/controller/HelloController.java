//섹션 2 강의를 위한 폴더
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


    //아래는 parameter를 받는 controller 예제
    //method안에 @RequestParam이라고 적으면 해당 값을 뒤에 name에 저장
    @GetMapping("hello-mvc")
    //파라미터 괄호에 ctrl + p를 입력하면 해당 파리미터 정보 확인 가능
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        //key가 name이고 value는 파라미터로 받은 name의 값이 들어옴
        model.addAttribute("name", name);
        return "hello-template";
    }
    //이제 우리가 localhost:8080/hello-mvc로 requset하면 아래 method에 따라 model이 등록되고,
    //hello-template.html이 view 되는 것
    //근데 우리는 hello-mvc에서 paremeter를 받기로 했으니 그냥 localhost:8080/hello-mvc를 입력하면
    //page가 뜨지 않음
    // => RequestParam의 required의 default는 true임, 이걸 false로 설정하면 반드시 넘겨줄 필요x
    //localhost:8080/hello-mvc?name=spring! 다음과 같이 Http get 방식에서 파라미터를 넘겨줄 수 있음


    //아래 method는 API 방식으로 data를 바로 내려주는 방식
    @GetMapping("hello-string")
    /*ResponseBody의 의미
     HTML에 나오는 body 태그가 아니라
     HTTP에 header 부분고 body부분이 있는데, body부분에 return 값을 직접 넣어주겠다는 의미
     */
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //parameter로 spring을 넘겨주면 hello spring이 body에 들어갈 것
    }
    //MVC와 차이점으로 API방식은 view같은 것이 없고 그냥 return한 문자 값이 그대로 넘어감
    //실제로 웹 브라우저 받은 페이지를 소스보기 해보면 MVC는 HTML로 온 것을 확인 가능하고, API는 문자만 온 것을 확인 가능
    //위의 메소드는 사실 예제를 위해 보여준 거지 실제로는 별 의미 x


    //아래 방식이 일반적인 API방식
    //ctrl + shift + enter = smart 자동완성
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    //localhost:8080/hello-api?name=spring!!이라고 넘겨주면 위 메소드가 실행되면서 hello가 return 됨
    //여기서 data는 json방식으로 전달됨
    //json은 key와 value 쌍으로 이루어진 data 구조
    //원래는 XML방식이었는데 최근에는 대부분 json으로 사용함
    
    //class 내부에 class 선언
    //Hello class는 name이라는 변수를 private으로 갖고 set과 get을 지원
    //Hello를 return하게 되면 key:name value:{set 설정 값} 의 쌍으로 전달 됨
    static class Hello{
        private  String name;

        //method선언시 직접 입력해도 되고, 기본적인 method는
        //alt + insert해서 generator를 켜서 만들수도 있음
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

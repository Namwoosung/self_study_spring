package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    /**
     * 단순 텍스트를 내려주는 방법
     */
    //기존 서블릿에서 사용하던 방식
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.getWriter().write("ok");
    }

    //ResponseEntity를 활용하는 방식
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }


    //@ResponseBody를 활용
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }

    /**
     * JSON을 내려주는 방법
     */
    //ResponseEntity를 활용하는 방식
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("kim");

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //@ResponseBody를 활용
    @ResponseStatus(HttpStatus.OK) //ResponseEntity를 사용하면 상태코드를 지정할 수 있었는데, @ResponseBody는 따로 지정할 수 없으니 다음과 같은 애노테이션이 제공됨
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("kim");

        return helloData;
    }
}

package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // /hello라는 path로 request가 오면 해당 class의 서블릿을 실행
public class HelloServlet extends HttpServlet {
    //servlet이 실행되면 아래의 service method를 실행시킴
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //넘어온 request 정보 접근
        String username = request.getParameter("username"); //request 객체를 통해 간단히 쿼리파라마티 값 접근 가능
        System.out.println("username = " + username);

        //넘겨줄 response 정보 설정
        response.setContentType("text/plane"); //type은 단순한 문자를 전송하겠다.
        response.setCharacterEncoding("utf-8"); //utf-8로 인코딩하겠다.
        //위의 type과 인코딩 정보는 실제 HTTP response 메시지에 헤더부분의 Content-Type 부분에 들어갈 것
        response.getWriter().write("hello " + username); //실제 보낼 data
    }
}

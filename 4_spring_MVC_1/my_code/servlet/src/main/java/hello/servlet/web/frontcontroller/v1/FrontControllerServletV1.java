package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // /front-controller/v1 하위 URL 접근은 모두 해당 서블릿을 실행
public class FrontControllerServletV1 extends HttpServlet {

    //각각의 URL과 해당 URL에 호출되어야 하는 컨트롤러를 저장하는 Map
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        //서블릿 생성 시 호출 가능한 URL과 해당 URL들의 컨트롤러를 mapping해서 저장
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI(); //http 요청으로 온 URI를 저장
        ControllerV1 controller = controllerMap.get(requestURI); //URI의 컨트롤러를 저장

        if(controller == null){ //해당 URL에 맞는 컨트롤러가 없는 경우 404를 response
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response); //선택된 컨트롤러의 process 메소드를 호출
    }
}

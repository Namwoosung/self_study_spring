package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //어떤 버전의 컨트롤러든 올 수 있어야 하므로 특정 컨트롤러 버전의 인터페이스가 아니라 Object를 받을 수 있는 Map 생성
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    //여러 핸들러 어댑터들의 리스트
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();


    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }



    private void initHandlerMappingMap() {
        //handlerMappingMap은 URL과 어떤버전의 컨트롤러든지 모두 mapping가능
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //v4추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //request에 URI에 해당하는 핸들러를 받아옴
        Object handler = getHandler(request);

        if(handler == null){ //해당 URL에 맞는 컨트롤러가 없는 경우 404를 response
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //handler에 해당하는 어댑터를 찾아옴(핸들러를 지원하는 어댑터가 반환될 것)
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        //adapter로 자신의 규격에 맞게 handler를 실행하는 것
        ModelView mv = adapter.handle(request, response, handler);


        //mv에 있는 이름은 논리이름 => viewResolver함수를 통해 논리이름을 물리위치로 mapping
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }



    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI(); //http 요청으로 온 URI를 저장
        return handlerMappingMap.get(requestURI); //URI의 컨트롤러를 저장
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다.= " + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}

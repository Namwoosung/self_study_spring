package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    //각 컨트롤러는 MyView를 반환
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException; //각 컨트롤러에서 수행해야할 작업
}

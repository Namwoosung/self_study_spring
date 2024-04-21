package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //서블릿의 service가 controller 역할을 수행 -> view는 jsp가 담당하므로, 로직을 실행하고 이후 jsp를 호출하는 역할

        String viewPath = "/WEB-INF/views/new-form.jsp"; //view를 담당할 jsp을 path
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); //컨트롤러에서 view로 이동하기 위해 사용
        dispatcher.forward(request, response); //서블릿에서 jsp를 호출
    }
}

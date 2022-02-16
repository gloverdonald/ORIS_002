package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.services.FollowService;
import ru.itis.services.SignInService;
import ru.itis.exceptions.ValidationException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");

        this.signInService = springContext.getBean(SignInService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("sign_in.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserForm form = UserForm.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
        UserDto userDto;

        try {
            userDto = signInService.signIn(form);
        } catch (ValidationException e) {
            response.sendRedirect("/sign-in");
            return;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("user", userDto);
        response.sendRedirect("/profile");
    }
}

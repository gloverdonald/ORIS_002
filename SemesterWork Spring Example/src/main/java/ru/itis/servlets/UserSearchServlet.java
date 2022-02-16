package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import ru.itis.services.FilesService;
import ru.itis.services.UserSearchService;
import ru.itis.services.PostsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/searching-user")
public class UserSearchServlet extends HttpServlet {
    private UserSearchService userSearchService;
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");

        this.userSearchService = springContext.getBean(UserSearchService.class);
        this.objectMapper = springContext.getBean(ObjectMapper.class);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("users", userSearchService.getByFirstOrLastName(request.getParameter("search")));
        objectMapper.writeValue(response.getWriter(), userSearchService.getByFirstOrLastName(request.getParameter("search")));
    }
}

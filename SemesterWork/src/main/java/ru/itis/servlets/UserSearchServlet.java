package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.services.UserSearchService;
import ru.itis.services.PostsService;

import javax.servlet.ServletConfig;
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
        objectMapper = (ObjectMapper) config.getServletContext().getAttribute("objectMapper");
        userSearchService = (UserSearchService) config.getServletContext().getAttribute("userSearchService");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("users", userSearchService.getByFirstOrLastName(request.getParameter("search")));
        objectMapper.writeValue(response.getWriter(), userSearchService.getByFirstOrLastName(request.getParameter("search")));
    }
}

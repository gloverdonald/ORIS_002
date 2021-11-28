package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.services.FollowService;
import ru.itis.services.PostsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/feed")
public class FeedServlet extends HttpServlet {
    private PostsService postsService;

    @Override
    public void init(ServletConfig config) {
        postsService = (PostsService) config.getServletContext().getAttribute("postsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        request.setAttribute("user", userDto);
        request.setAttribute("feedPosts", postsService.getFeed(userDto.getId()));
        request.getRequestDispatcher("/feed.ftl").forward(request, response);
    }
}

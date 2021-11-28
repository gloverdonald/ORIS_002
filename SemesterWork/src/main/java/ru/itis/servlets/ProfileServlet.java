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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private PostsService postsService;
    private FollowService followService;

    @Override
    public void init(ServletConfig config) {
        postsService = (PostsService) config.getServletContext().getAttribute("postsService");
        followService = (FollowService) config.getServletContext().getAttribute("followService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        request.setAttribute("user", userDto);
        request.setAttribute("posts", postsService.getByAuthorId(userDto.getId()));
        request.setAttribute("followingCount", followService.getFollowingCount(userDto.getId()));
        request.setAttribute("followersCount", followService.getFollowersCount(userDto.getId()));
        request.setAttribute("postsCountQ", postsService.getByAuthorId(userDto.getId()).size());
        request.getRequestDispatcher("/profile.ftl").forward(request, response);

    }
}

package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.services.FollowService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/followers")
public class FollowersServlet extends HttpServlet {
    FollowService followService;

    @Override
    public void init(ServletConfig config) {
        followService = (FollowService) config.getServletContext().getAttribute("followService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        Long profile_id = Long.parseLong(request.getParameter("id"));
        //Long profile_id = PostDto.getId();
        List<UserDto> followers = followService.followers(profile_id);


        request.getSession().setAttribute("followers", followers);

        // request.setAttribute("followingCount", followService.getFollowingCount(PostDto.getId()));
        request.setAttribute("followersCount", followService.getFollowersCount(profile_id));



        request.getRequestDispatcher("/followers.ftl").forward(request, response);
    }

}

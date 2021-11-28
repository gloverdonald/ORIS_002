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

@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
    private FollowService followerService;

    @Override
    public void init(ServletConfig config) {
        this.followerService = (FollowService) config.getServletContext().getAttribute("followService");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        Long id = userDto.getId();
        Long following = Long.valueOf(request.getParameter("follow"));
        request.setAttribute("isFollower", "unfollow");

        followerService.follow(id, following);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        Long id = userDto.getId();
        Long following = Long.valueOf(request.getParameter("follow"));
        request.setAttribute("isFollower", "follow");
        followerService.unfollow(id, following);
    }
}

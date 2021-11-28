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

@WebServlet("/following")
public class FollowingServlet extends HttpServlet {
    FollowService followService;

    @Override
    public void init(ServletConfig config) {
        followService = (FollowService) config.getServletContext().getAttribute("followService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        Long profile_id = Long.parseLong(request.getParameter("id"));
        List<UserDto> following = followService.following(profile_id);
        request.getSession().setAttribute("following", following);
        request.setAttribute("followingCount", followService.getFollowingCount(profile_id));
        request.getRequestDispatcher("/following.ftl").forward(request, response);
    }

}

package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.UserDto;
import ru.itis.services.FilesService;
import ru.itis.services.FollowService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.followService = springContext.getBean(FollowService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long profile_id = Long.parseLong(request.getParameter("id"));
        List<UserDto> followers = followService.followers(profile_id);
        request.getSession().setAttribute("followers", followers);
        request.setAttribute("followersCount", followService.getFollowersCount(profile_id));
        request.getRequestDispatcher("/followers.ftl").forward(request, response);
    }
}

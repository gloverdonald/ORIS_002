package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import ru.itis.dto.UserDto;
import ru.itis.services.FollowService;
import ru.itis.services.PostsService;
import ru.itis.services.UserSearchService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@WebServlet("/user-profile")
public class UsersProfileServlet extends HttpServlet {
    private PostsService postsService;
    private UserSearchService userSearchService;
    private FollowService followService;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");

        this.postsService = springContext.getBean(PostsService.class);
        this.userSearchService = springContext.getBean(UserSearchService.class);
        this.followService = springContext.getBean(FollowService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        Long id = Long.parseLong(request.getParameter("id"));
        if (id.equals(userDto.getId())) {
            response.sendRedirect("/profile");
        } else {
            try {
                request.setAttribute("user", userSearchService.getById(id));
                request.setAttribute("posts", postsService.getByAuthorId(id));
                request.setAttribute("followingCount", followService.getFollowingCount(id));
                request.setAttribute("followersCount", followService.getFollowersCount(id));
                List<Long> idList = new ArrayList<>();
                for (UserDto follower : followService.following(userDto.getId())) {
                    idList.add(follower.getId());
                }
                String status;
                if (idList.contains(id)) {
                    status = "unfollow";
                } else {
                    status = "follow";
                }
                request.setAttribute("isFollower", status);
                request.getRequestDispatcher("/userProfile.ftl").forward(request, response);
            } catch (NoSuchElementException | NumberFormatException e) {
                response.getWriter().println("not found");
            }
        }
    }
}

package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import ru.itis.dto.PostDto;
import ru.itis.dto.UserDto;
import ru.itis.services.PostsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/add-post")
public class AddPostServlet extends HttpServlet {
    private PostsService postsService;
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.postsService = springContext.getBean(PostsService.class);
        this.objectMapper = springContext.getBean(ObjectMapper.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        PostDto form = PostDto.builder()
                .content(request.getParameter("content"))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .author(userDto)
                .build();
        PostDto createdPost = postsService.addPost(form);
        objectMapper.writeValue(response.getWriter(), createdPost);
    }
}

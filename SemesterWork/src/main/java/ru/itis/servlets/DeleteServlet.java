package ru.itis.servlets;

import ru.itis.services.PostsService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    PostsService postsService;

    @Override
    public void init(ServletConfig config) {
        postsService = (PostsService) config.getServletContext().getAttribute("postsService");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("delete"));
        postsService.delete(id);
    }
}

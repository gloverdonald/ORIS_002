package ru.itis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/sign-up")
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        JDBCTemplate jdbcTemplate = new JDBCTemplate();
        try {
            jdbcTemplate.save(username, password);
            request.getSession().setAttribute("User", username);
            response.sendRedirect("/profile");
        } catch (org.springframework.dao.DuplicateKeyException e) {
            response.sendRedirect("/sign-up");
        }
    }

}
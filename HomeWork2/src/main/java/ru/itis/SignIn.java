package ru.itis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignIn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        JDBCTemplate jdbcTemplate = new JDBCTemplate();

        if (password.equals(jdbcTemplate.findProfile(username).get())) {
            request.getSession().setAttribute("User", username);
            response.sendRedirect("/profile");
        } else {
            jdbcTemplate.findProfile(username).get();
            response.sendRedirect("/sign-in");
        }
    }
}

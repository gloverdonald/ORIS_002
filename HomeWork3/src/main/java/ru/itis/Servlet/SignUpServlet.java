package ru.itis.Servlet;

import ru.itis.Repositories.SignUpDB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    private SignUpDB signUpDB;
    @Override
    public void init(ServletConfig config) {
        this.signUpDB = (SignUpDB) config.getServletContext().getAttribute("signUp");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            signUpDB.save(username, password);
            request.getSession().setAttribute("User", username);
            response.sendRedirect("/profile");
        } catch (org.springframework.dao.DuplicateKeyException e) {
            response.sendRedirect("/sign-up");
        }
    }
}
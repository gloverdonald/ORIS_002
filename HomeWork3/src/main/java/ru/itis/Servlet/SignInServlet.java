package ru.itis.Servlet;

import ru.itis.Models.User;
import ru.itis.Repositories.SignInDB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private SignInDB signInDB;
    private User user;

    @Override
    public void init(ServletConfig config) {
        this.signInDB = (SignInDB) config.getServletContext().getAttribute("signIn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("User") == null) {
            request.getRequestDispatcher("jsp/signIn.jsp").forward(request, response);
        } else {
            response.sendRedirect("/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String correctPassword;
        Integer avatar = null;
        System.out.println(avatar);
        try {
            user = signInDB.findUser(username).get();
            correctPassword = user.getPassword();
            avatar = user.getAvatarId();
            if (avatar == 0) {
                avatar = null;
            }

        } catch (NoSuchElementException e) {
            correctPassword = null;
        }
        if (password.equals(correctPassword)) {
            request.getSession(false).setAttribute("User", username);
            request.getSession().setAttribute("avatarId", avatar);
            response.sendRedirect("/profile");
        } else {
            if (correctPassword != null) {
                response.sendRedirect("/sign-in");
            } else {
                response.sendRedirect("/sign-up");
            }
        }
    }
}

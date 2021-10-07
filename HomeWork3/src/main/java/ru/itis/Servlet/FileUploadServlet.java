package ru.itis.Servlet;

import ru.itis.Models.FileInfo;
import ru.itis.Services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/file-upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private FileService filesService;

    @Override
    public void init(ServletConfig config) {
        this.filesService = (FileService) config.getServletContext().getAttribute("filesService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/fileUpload.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        String username = (String) request.getSession().getAttribute("User");
        FileInfo fileInfo = filesService.saveFile(part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize(),
                username);
        request.getSession().setAttribute("avatarId", fileInfo.getId());
        response.sendRedirect("/profile");
    }
}
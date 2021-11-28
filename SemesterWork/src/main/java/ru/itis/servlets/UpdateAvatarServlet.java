package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.model.FileInfo;
import ru.itis.services.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/file-upload")
@MultipartConfig
public class UpdateAvatarServlet extends HttpServlet {
    private FilesService filesService;

    @Override
    public void init(ServletConfig config) {
        this.filesService = (FilesService) config.getServletContext().getAttribute("filesService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/fileUpload.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        HttpSession session = request.getSession(true);
        UserDto userDto = (UserDto) session.getAttribute("user");
        FileInfo fileInfo = filesService.saveFileToStorage(
                userDto,
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());
        userDto.setAvatarId(fileInfo.getId());
        session.setAttribute("user", userDto);
        response.sendRedirect("/profile");
    }
}

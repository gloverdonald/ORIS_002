package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.model.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FilesService {
    FileInfo saveFileToStorage(UserDto user, InputStream file, String originalFileName, String contentType, Long size);
    void readFileFromStorage(Long fileId, OutputStream outputStream);
    FileInfo getFileInfo(Long fileId);
}

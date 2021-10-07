package ru.itis.Services;

import ru.itis.Models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    FileInfo saveFile(InputStream file, String originalFileName, String contentType, Long size, String username);

    void readFile(Long fileId, OutputStream outputStream);

    FileInfo getFileInfo(Long fileId);
}

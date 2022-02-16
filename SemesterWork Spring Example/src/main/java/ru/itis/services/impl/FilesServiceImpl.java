package ru.itis.services.impl;

import org.springframework.stereotype.Service;
import ru.itis.dao.FilesRepository;
import ru.itis.dao.UsersRepository;
import ru.itis.dto.UserDto;
import ru.itis.exceptions.NotFoundException;
import ru.itis.model.FileInfo;
import ru.itis.services.FilesService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService {

    String path = "/home/mst/texts/";

    private final FilesRepository filesRepository;
    private final UsersRepository usersRepository;

    public FilesServiceImpl(FilesRepository filesRepository, UsersRepository usersRepository) {
        this.filesRepository = filesRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public FileInfo saveFileToStorage(UserDto user, InputStream inputStream, String originalFileName, String contentType, Long size) {
        FileInfo fileInfo = new FileInfo(
                null,
                originalFileName,
                UUID.randomUUID().toString(),
                size,
                contentType
        );
        try {
            Files.copy(inputStream, Paths.get(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileInfo = filesRepository.save(fileInfo);
            usersRepository.updateAvatarForUser(user.getId(), fileInfo.getId());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return fileInfo;
    }

    @Override
    public void readFileFromStorage(Long fileId, OutputStream outputStream) {
        Optional<FileInfo> optionalFileInfo = filesRepository.findById(fileId);
        FileInfo fileInfo = optionalFileInfo.orElseThrow(() -> new NotFoundException("File not found"));

        File file = new File(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return filesRepository.findById(fileId).orElseThrow(() -> new NotFoundException("File not found"));
    }
}

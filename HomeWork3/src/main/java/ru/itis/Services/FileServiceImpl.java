package ru.itis.Services;

import ru.itis.Exceptions.NotFoundException;
import ru.itis.Exceptions.WrongFileTypeException;
import ru.itis.Models.FileInfo;
import ru.itis.Repositories.FileRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class FileServiceImpl implements FileService{
    String path = "/home/mst/texts";
    private final FileRepository filesRepository;

    public FileServiceImpl(FileRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public FileInfo saveFile(InputStream inputStream, String originalFileName, String contentType, Long size, String username) {
        FileInfo fileInfo = new FileInfo(
                null,
                originalFileName,
                UUID.randomUUID().toString(),
                size,
                contentType
        );
        try {
            Files.copy(inputStream, Paths.get(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileInfo = filesRepository.save(fileInfo,username);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return fileInfo;
    }

    @Override
    public void readFile(Long fileId, OutputStream outputStream) {
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

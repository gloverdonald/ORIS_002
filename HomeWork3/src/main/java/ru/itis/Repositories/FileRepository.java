package ru.itis.Repositories;

import ru.itis.Models.FileInfo;

import java.util.Optional;

public interface FileRepository {
    Optional<FileInfo> findById(Long id);

    FileInfo save(FileInfo item, String username);
}

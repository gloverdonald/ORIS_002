package ru.itis.dao;

import ru.itis.dao.base.CrudRepository;
import ru.itis.model.FileInfo;

public interface FilesRepository extends CrudRepository<FileInfo, Long> {}

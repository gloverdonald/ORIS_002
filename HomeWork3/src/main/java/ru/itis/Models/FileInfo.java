package ru.itis.Models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FileInfo {
    private Long id;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
}

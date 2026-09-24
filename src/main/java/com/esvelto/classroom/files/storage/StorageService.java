package com.esvelto.classroom.files.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String uploadFile(MultipartFile file, String folder);
    void deleteFile(String fileUrl);
}

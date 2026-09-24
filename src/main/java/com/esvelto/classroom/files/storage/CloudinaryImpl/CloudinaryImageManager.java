package com.esvelto.classroom.files.storage.CloudinaryImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.esvelto.classroom.files.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryImageManager implements StorageService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File cannot be empty");
        }

        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", folder,
                            "transformation", new Transformation<>()
                                    .width(300)
                                    .height(300)
                                    .crop("fill")
                                    .gravity("face")
                                    .fetchFormat("auto")
                                    .quality("auto")
                    )
            );

            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error uploading file to storage", e);
        }
    }

    @Override
    public void deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isBlank()) {
            return;
        }

        try {
            String publicId = extractPublicIdFromUrl(fileUrl);
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting file from storage", e);
        }
    }

    private String extractPublicIdFromUrl(String fileUrl) {
        // Extrae el ID público respetando la carpeta (ej. "esvelto/avatars/imagen.jpg" -> "esvelto/avatars/imagen")
        int uploadIndex = fileUrl.indexOf("/upload/");
        if (uploadIndex == -1) return null;

        String pathAfterUpload = fileUrl.substring(uploadIndex + 8);

        // Si la URL incluye versión (ej. v12345678/), se descarta esa parte
        if (pathAfterUpload.startsWith("v")) {
            pathAfterUpload = pathAfterUpload.substring(pathAfterUpload.indexOf('/') + 1);
        }

        int lastDotIndex = pathAfterUpload.lastIndexOf('.');
        return (lastDotIndex != -1) ? pathAfterUpload.substring(0, lastDotIndex) : pathAfterUpload;
    }
}
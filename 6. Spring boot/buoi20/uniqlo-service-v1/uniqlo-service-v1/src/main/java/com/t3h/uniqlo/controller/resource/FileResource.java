package com.t3h.uniqlo.controller.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileResource {

    // Doc duong dan thu muc tu file cau hinh application.properties
    @Value("${storage.upload-dir}")
    private String uploadDir;

    /**
     * API tai len hinh anh.
     * Tra ve duong dan API de truy cap anh (preview).
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File rong");
        }

        try {
            // Tao thu muc neu chua ton tai
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Tao ten file duy nhat bang UUID de tranh trung lap
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            
            // Ghi du lieu file vao o dia
            Files.write(path, file.getBytes());

            // Tra ve URL API de UI co the goi load anh
            return ResponseEntity.ok("/api/files/preview/" + fileName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Loi khi luu file: " + e.getMessage());
        }
    }

    /**
     * API lay file anh de hien thi tren giao dien.
     * Doc file tu thu muc cau hinh va tra ve byte du lieu cung MediaType phu hop.
     */
    @GetMapping("/preview/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                // Tu dong xac dinh kieu file (image/png, image/jpeg, ...)
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

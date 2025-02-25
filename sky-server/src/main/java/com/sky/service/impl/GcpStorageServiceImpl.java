package com.sky.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.sky.service.GcpStorageService;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class GcpStorageServiceImpl implements GcpStorageService {

    private final Storage storage;
    private final String bucketName;

    public GcpStorageServiceImpl(@Value("${gcp.credentials.file}") String credentialsFilePath,
                                 @Value("${gcp.bucket.name}") String bucketName) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsFilePath));
        this.storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        this.bucketName = bucketName;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        // 生成唯一的文件名
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // 創建 BlobId
        BlobId blobId = BlobId.of(bucketName, fileName);

        // 創建 BlobInfo，設置文件的元數據（如 MIME 類型）
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        // 使用 BlobInfo 和文件的字節數組來上傳文件
        Blob blob = storage.create(blobInfo, file.getBytes());

        // 返回文件的 URL
        return "https://storage.googleapis.com/" + bucketName + "/" + fileName;
    }
}

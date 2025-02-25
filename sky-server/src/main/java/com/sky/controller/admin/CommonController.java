package com.sky.controller.admin;

import com.google.cloud.storage.BlobId;
import com.sky.result.Result;
import com.sky.service.GcpStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.storage.Blob;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api("通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private GcpStorageService gcpStorageService;

    @PostMapping("/upload")
    @ApiOperation("文件上傳")
    public Result<String> upload (MultipartFile file) throws IOException {
        try {
            String fileUrl = gcpStorageService.uploadFile(file);
            return Result.success(fileUrl);
        } catch (IOException e) {
            log.error("文件上傳失敗：{}", e);
        }
        return null;
    }
}

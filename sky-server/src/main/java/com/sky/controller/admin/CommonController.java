package com.sky.controller.admin;

import com.google.cloud.storage.BlobId;
import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.service.GcpStorageService;
import com.sky.utils.AliOssUtil;
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
    @Autowired
    private AliOssUtil aliOssUtil;

    // gcp云存儲功能
    @PostMapping("/upload")
    @ApiOperation("文件上傳")
    public Result<String> upload (MultipartFile file) throws IOException {
        try {
            String fileUrl = gcpStorageService.uploadFile(file);
            return Result.success(fileUrl);
        } catch (IOException e) {
            log.error("文件上傳失敗：{}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

    // 阿里雲的存儲功能，暫時懶得去申請帳號
//    @PostMapping("/upload")
//    @ApiOperation("文件上傳")
//    public Result<String> upload (MultipartFile file) throws IOException {
//        try {
//            String originaFilename = file.getOriginalFilename();
//            String extension = originaFilename.substring(originaFilename.lastIndexOf("."));
//            String objectName = UUID.randomUUID().toString() + extension;
//            String fileUrl = aliOssUtil.upload(file.getBytes(), objectName);
//            return Result.success(fileUrl);
//        } catch (IOException e) {
//            log.error("文件上傳失敗：{}", e);
//        }
//        return Result.error(MessageConstant.UPLOAD_FAILED);
//    }
}

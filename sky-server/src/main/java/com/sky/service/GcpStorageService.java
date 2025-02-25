package com.sky.service;

import ch.qos.logback.core.joran.util.beans.BeanDescriptionFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GcpStorageService {

    /**
     * 上傳文件
     *
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-25 18:39:32 Version:1.0
     * change Description:
     */

    String uploadFile(MultipartFile file) throws IOException;

}

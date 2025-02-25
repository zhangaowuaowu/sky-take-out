package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gcp")
@Data
public class GcpProperties {
    private String credentialsFilePath;
    private String bucketName;
}

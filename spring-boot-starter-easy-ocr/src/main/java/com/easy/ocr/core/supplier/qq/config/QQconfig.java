package com.easy.ocr.core.supplier.qq.config;

import com.qcloud.image.ImageClient;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.easy.ocr.qq")
public class QQconfig {

    private String appId ;
    private String secretId ;
    private String secretKey ;
    private String bucketName = "";
    private boolean open=false;
    public static final String domain = ImageClient.NEW_DOMAIN_recognition_image_myqcloud_com;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}

package com.easy.ocr.core.supplier.qq.config;

import com.qcloud.image.ImageClient;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.easy.ocr.qq")
public class QQconfig {

    private String appId = "1259630960";//根据你的帐号信息修改
    private String secretId = "AKIDqVv5z5kixxHpQofwd4GDzZviRu8AnXrv";//根据你的帐号信息修改
    private String secretKey = "5vVptiKjszsVzKVK1ZtukLrBF8mCQO6f";//根据你的帐号信息修改
    private String bucketName = "";//历史遗留字段, 无需修改
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

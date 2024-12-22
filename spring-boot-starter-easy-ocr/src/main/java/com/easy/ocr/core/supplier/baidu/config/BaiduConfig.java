package com.easy.ocr.core.supplier.baidu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.easy.ocr.baidu")
public class BaiduConfig {
    private String appId= "16782092";
    private String apiKey = "GuCsGkBxlgHEeup3hVDaL2fj";
    private String secretKey = "HhkNVBQD9iR6gtjnrSBsajpbVjjKW7Q3";
    private boolean open=false;
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}

package com.easy.ocr.core.supplier.qq;

import com.easy.ocr.core.supplier.qq.config.QQconfig;
import com.qcloud.image.ImageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QQClientUtil {
    @Autowired
    private QQconfig qQconfig;

    public ImageClient getClient() {
        return new ImageClient(qQconfig.getAppId(), qQconfig.getSecretId(), qQconfig.getSecretKey(), qQconfig.domain/*根据文档说明选择域名*/);

    }
}

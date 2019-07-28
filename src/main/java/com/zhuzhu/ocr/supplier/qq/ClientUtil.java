package com.zhuzhu.ocr.supplier.qq;

import com.qcloud.image.ImageClient;
import com.zhuzhu.ocr.supplier.qq.config.QQconfig;

public class ClientUtil {
    private static ImageClient imageClient = null;

    public static ImageClient getClient() {
        if (imageClient == null) {
            imageClient = new ImageClient(QQconfig.appId, QQconfig.secretId, QQconfig.secretKey, QQconfig.domain/*根据文档说明选择域名*/);
        }
        return imageClient;
    }
}

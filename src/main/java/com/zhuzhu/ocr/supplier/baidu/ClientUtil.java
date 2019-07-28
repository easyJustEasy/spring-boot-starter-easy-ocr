package com.zhuzhu.ocr.supplier.baidu;

import com.baidu.aip.ocr.AipOcr;
import com.zhuzhu.ocr.supplier.baidu.config.BaiDuConfig;

public class ClientUtil {
    private static AipOcr imageClient = null;

    public static AipOcr getClient() {
        if (imageClient == null) {
            imageClient = new AipOcr(BaiDuConfig.APP_ID, BaiDuConfig.API_KEY, BaiDuConfig.SECRET_KEY);
        }
        return imageClient;
    }
}

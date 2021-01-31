package com.easy.ocr.core.supplier.baidu;

import com.baidu.aip.ocr.AipOcr;
import com.easy.ocr.core.supplier.baidu.config.BaiduConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaiduClientUtil {
    @Autowired
    private BaiduConfig baiduConfig;

    public AipOcr getClient() {
        return new AipOcr(baiduConfig.getAppId(), baiduConfig.getApiKey(), baiduConfig.getSecretKey());
    }
}

package com.easy.ocr.config;

import com.baidu.aip.ocr.AipOcr;
import com.easy.ocr.core.core.service.BankCardService;
import com.easy.ocr.core.core.service.IdCardService;
import com.easy.ocr.core.core.service.impl.EasyOcrUtil;
import com.easy.ocr.core.supplier.baidu.bank.service.BaiduBankCardService;
import com.easy.ocr.core.supplier.baidu.config.BaiduConfig;
import com.easy.ocr.core.supplier.baidu.idcard.service.BaiduIdCardService;
import com.easy.ocr.core.supplier.qq.bank.service.QQBankCardService;
import com.easy.ocr.core.supplier.qq.config.QQconfig;
import com.easy.ocr.core.supplier.qq.idcard.service.QQIdCardService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties({BaiduConfig.class, QQconfig.class})
@ConditionalOnProperty(
        prefix = "spring.easy.ocr",
        name = "isOpen",
        havingValue = "true"
)
public class AutoInitConfig {
    @Autowired
    private BaiduIdCardService baiduIdCardService;
    @Autowired
    private BaiduBankCardService baiduBankCardService;
    @Autowired
    private QQIdCardService qqIdCardService;
    @Autowired
    private QQBankCardService qqBankCardService;

    @Bean(name = "easyOcrUtil")
    public EasyOcrUtil easyOcrUtil() {
        List<IdCardService> idCardServices = Lists.newArrayList(baiduIdCardService, qqIdCardService);
        List<BankCardService> bankCardServices = Lists.newArrayList(baiduBankCardService, qqBankCardService);
        return new EasyOcrUtil(idCardServices, bankCardServices);
    }

}

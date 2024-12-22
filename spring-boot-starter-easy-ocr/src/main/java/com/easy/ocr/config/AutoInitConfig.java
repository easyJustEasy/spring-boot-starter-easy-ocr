package com.easy.ocr.config;

import com.easy.ocr.core.core.service.BankCardService;
import com.easy.ocr.core.core.service.IdCardService;
import com.easy.ocr.core.core.service.impl.EasyOcrUtil;
import com.easy.ocr.core.supplier.baidu.BaiduClientUtil;
import com.easy.ocr.core.supplier.baidu.bank.service.BaiduBankCardService;
import com.easy.ocr.core.supplier.baidu.config.BaiduConfig;
import com.easy.ocr.core.supplier.baidu.idcard.service.BaiduIdCardService;
import com.easy.ocr.core.supplier.rapidOCR.service.RapidOCRService;
import com.easy.ocr.core.supplier.qq.QQClientUtil;
import com.easy.ocr.core.supplier.qq.bank.service.QQBankCardService;
import com.easy.ocr.core.supplier.qq.config.QQconfig;
import com.easy.ocr.core.supplier.qq.idcard.service.QQIdCardService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnClass({QQClientUtil.class, BaiduClientUtil.class})
@EnableConfigurationProperties({BaiduConfig.class, QQconfig.class})
public class AutoInitConfig {
    @Autowired
    private BaiduConfig baiduConfig;
    @Autowired
    private QQconfig qQconfig;

    @Bean(name = "baiduClientUtil")
    public BaiduClientUtil baiduClientUtil() {
        return new BaiduClientUtil(baiduConfig);
    }

    @Bean(name = "qqClientUtil")
    public QQClientUtil qqClientUtil() {
        return new QQClientUtil(qQconfig);
    }

    @Bean(name = "baiduIdCardService")
    public BaiduIdCardService baiduIdCardService() {
        return new BaiduIdCardService(baiduConfig, baiduClientUtil());
    }

    @Bean(name = "baiduBankCardService")
    public BaiduBankCardService baiduBankCardService() {
        return new BaiduBankCardService(baiduConfig, baiduClientUtil());
    }

    @Bean(name = "qqIdCardService")
    public QQIdCardService qqIdCardService() {
        return new QQIdCardService(qQconfig, qqClientUtil());
    }

    @Bean(name = "qqBankCardService")
    public QQBankCardService qqBankCardService() {
        return new QQBankCardService(qQconfig, qqClientUtil());
    }

    @Bean(name = "localAi")
    public RapidOCRService localAi() {
        return new RapidOCRService();
    }

    @Bean(name = "easyOcrUtil")
    public EasyOcrUtil easyOcrUtil() {
        RapidOCRService rapidOCRService = localAi();
        List<IdCardService> idCardServices = Lists.newArrayList(rapidOCRService);
        List<BankCardService> bankCardServices = Lists.newArrayList(rapidOCRService);
        if (qQconfig.isOpen()) {
            QQIdCardService qqIdCardService = qqIdCardService();
            QQBankCardService qqBankCardService = qqBankCardService();
            bankCardServices.add(qqBankCardService);
            idCardServices.add(qqIdCardService);
        }
        if(baiduConfig.isOpen()){
            BaiduIdCardService baiduIdCardService = baiduIdCardService();
            BaiduBankCardService baiduBankCardService = baiduBankCardService();
            bankCardServices.add(baiduBankCardService);
            idCardServices.add(baiduIdCardService);
        }
        return new EasyOcrUtil(idCardServices, bankCardServices);
    }

}

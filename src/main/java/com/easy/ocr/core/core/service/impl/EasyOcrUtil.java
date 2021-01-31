package com.easy.ocr.core.core.service.impl;


import com.easy.ocr.core.supplier.qq.bank.service.QQBankCardService;
import com.easy.ocr.core.supplier.qq.idcard.service.QQIdCardService;
import com.easy.ocr.core.core.bean.BankCard;
import com.easy.ocr.core.core.bean.IdCard;
import com.easy.ocr.core.core.service.BankCardService;
import com.easy.ocr.core.core.service.IdCardService;
import com.easy.ocr.core.core.util.RandomUtil;
import com.easy.ocr.core.supplier.baidu.bank.service.BaiduBankCardService;
import com.easy.ocr.core.supplier.baidu.idcard.service.BaiduIdCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EasyOcrUtil implements BankCardService, IdCardService {
    private static final Integer DEFAULT_RETRY_TIMES = 3;
    private static Logger logger = LoggerFactory.getLogger(EasyOcrUtil.class);
    private  List<IdCardService> idCardServices;
    private  List<BankCardService> bankCardServices;
    private static final ThreadLocal<Integer> local = new ThreadLocal<>();

    public List<IdCardService> getIdCardServices() {
        return idCardServices;
    }

    public void setIdCardServices(List<IdCardService> idCardServices) {
        this.idCardServices = idCardServices;
    }

    public List<BankCardService> getBankCardServices() {
        return bankCardServices;
    }

    public void setBankCardServices(List<BankCardService> bankCardServices) {
        this.bankCardServices = bankCardServices;
    }

    public EasyOcrUtil(List<IdCardService> idCardServices, List<BankCardService> bankCardServices) {
        this.idCardServices = idCardServices;
        this.bankCardServices=bankCardServices;
    }



    @Override
    public BankCard showBankNo(File img) {
        local.set(0);
        BankCard bankCard = null;
        Integer time = local.get();
        while (time <= DEFAULT_RETRY_TIMES && bankCard == null) {
            BankCardService bankCardService = bankCardServices.get(RandomUtil.random(bankCardServices.size()));
            bankCard = bankCardService.showBankNo(img);
            local.set(time + 1);
            logger.info(String.format("showBankNo try %s times", local.get()));
        }
        return bankCard;
    }

    @Override
    public IdCard showFront(File img) {
        local.set(0);
        IdCard idCard = null;
        Integer time = local.get();
        while (time <= DEFAULT_RETRY_TIMES && idCard == null) {
            IdCardService idCardService = idCardServices.get(RandomUtil.random(idCardServices.size()));
            idCard = idCardService.showFront(img);
            local.set(time + 1);
            logger.info(String.format("showFront try %s times", local.get()));

        }
        return idCard;
    }

    @Override
    public IdCard showBack(File img) {
        local.set(0);
        IdCard idCard = null;
        Integer time = local.get();
        while (time <= DEFAULT_RETRY_TIMES && idCard == null) {
            IdCardService idCardService = idCardServices.get(RandomUtil.random(idCardServices.size()));
            idCard = idCardService.showBack(img);
            local.set(time + 1);
            logger.info(String.format("showBack try %s times", local.get()));

        }
        return idCard;
    }
}

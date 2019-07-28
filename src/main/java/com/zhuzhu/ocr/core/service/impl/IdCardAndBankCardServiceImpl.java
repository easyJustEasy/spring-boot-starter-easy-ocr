package com.zhuzhu.ocr.core.service.impl;


import com.zhuzhu.ocr.core.bean.BankCard;
import com.zhuzhu.ocr.core.bean.IdCard;
import com.zhuzhu.ocr.core.service.BankCardService;
import com.zhuzhu.ocr.core.service.IdCardService;
import com.zhuzhu.ocr.core.util.RandomUtil;
import com.zhuzhu.ocr.supplier.baidu.bank.service.BaiDuBankCardService;
import com.zhuzhu.ocr.supplier.baidu.idcard.service.BaiDuIdCardService;
import com.zhuzhu.ocr.supplier.qq.bank.service.QQBankCardService;
import com.zhuzhu.ocr.supplier.qq.idcard.service.QQIdCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IdCardAndBankCardServiceImpl implements BankCardService, IdCardService {
    private static final Integer DEFAULT_RETRY_TIMES = 3;
    private static Logger logger = LoggerFactory.getLogger(IdCardAndBankCardServiceImpl.class);
    private static List<IdCardService> idCardServices = new ArrayList<>();
    private static List<BankCardService> bankCardServices = new ArrayList<>();

    static {
        idCardServices.add(new QQIdCardService());
        idCardServices.add(new BaiDuIdCardService());
        bankCardServices.add(new QQBankCardService());
        bankCardServices.add(new BaiDuBankCardService());
    }

    private ThreadLocal<Integer> local = new ThreadLocal<>();

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

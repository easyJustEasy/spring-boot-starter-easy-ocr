package com.easy.ocr.core.core.service.impl;


import com.easy.ocr.core.core.bean.BankCard;
import com.easy.ocr.core.core.bean.IdCard;
import com.easy.ocr.core.core.service.BankCardService;
import com.easy.ocr.core.core.service.IdCardService;
import com.easy.ocr.core.core.util.RandomUtil;
import com.github.rholder.retry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class EasyOcrUtil implements BankCardService, IdCardService {
    private static final Integer DEFAULT_RETRY_TIMES = 3;
    private static Logger logger = LoggerFactory.getLogger(EasyOcrUtil.class);
    private List<IdCardService> idCardServices;
    private List<BankCardService> bankCardServices;
    private ThreadLocal<Integer> local = new ThreadLocal<>();


    public EasyOcrUtil(List<IdCardService> idCardServices, List<BankCardService> bankCardServices) {
        this.idCardServices = idCardServices;
        this.bankCardServices = bankCardServices;
    }


    @Override
    public BankCard showBankNo(File img) {
        return doIt(imge -> {
            BankCardService bankCardService = bankCardServices.get(RandomUtil.random(bankCardServices.size()));
            return bankCardService.showBankNo(img);
        }, img);
    }

    @Override
    public IdCard showFront(File img) {
        return doIt((imge) -> {
            IdCardService idCardService = idCardServices.get(RandomUtil.random(idCardServices.size()));
            return idCardService.showFront(imge);
        }, img);
    }

    @Override
    public IdCard showBack(File img) {
        return doIt((imge) -> {
            IdCardService idCardService = idCardServices.get(RandomUtil.random(idCardServices.size()));
            return idCardService.showBack(img);
        }, img);
    }

    private <T> T doIt(Function<File, T> function, File img) {
        // 创建一个重试器，重试器执行的方法，返回值为Boolean类型
        Retryer<T> retryer = RetryerBuilder.<T>newBuilder()
                // 出现异常时，会重试
                .retryIfException()
                // 失败后，隔2秒后重试
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                // 重试3次后，仍未成功，就不再重试
                .withStopStrategy(StopStrategies.stopAfterAttempt(DEFAULT_RETRY_TIMES))
                .build();
        // 使用重试器，执行具体逻辑
        try {
            return retryer.call(() ->function.apply(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

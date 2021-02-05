package com.easy.ocr.core.core.service;


import com.easy.ocr.core.core.bean.BankCard;

import java.io.File;

public interface BankCardService {

    /**
     * 解析银行卡
     * @param img
     * @return
     */
    BankCard showBankNo(File img);
}

package com.zhuzhu.ocr.core.service;


import com.zhuzhu.ocr.core.bean.BankCard;

import java.io.File;

public interface BankCardService {
    BankCard showBankNo(File img);
}

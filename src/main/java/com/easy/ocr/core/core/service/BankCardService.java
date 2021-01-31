package com.easy.ocr.core.core.service;


import com.easy.ocr.core.core.bean.BankCard;

import java.io.File;

public interface BankCardService {
    BankCard showBankNo(File img);
}

package com.easy.ocr.core.core.service;


import com.easy.ocr.core.core.bean.IdCard;

import java.io.File;

public interface IdCardService {

    /**
     * 解析身份证前面
     * @param img
     * @return
     */
    IdCard showFront(File img);

    /**
     * 解析身份证后面
     * @param img
     * @return
     */
    IdCard showBack(File img);
}

package com.zhuzhu.ocr.core.service;


import com.zhuzhu.ocr.core.bean.IdCard;

import java.io.File;

public interface IdCardService {
    IdCard showFront(File img);
    IdCard showBack(File img);
}

package com.easy.ocr.core.core.service;


import com.easy.ocr.core.core.bean.IdCard;

import java.io.File;

public interface IdCardService {
    IdCard showFront(File img);
    IdCard showBack(File img);
}

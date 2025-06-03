package com.easy.ocr.controller;

import com.easy.ocr.core.core.bean.IdCard;
import com.easy.ocr.core.core.service.impl.EasyOcrUtil;
import com.easy.ocr.utl.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private EasyOcrUtil ocrUtil;
     @RequestMapping("/single")
      public IdCard test(MultipartFile  file) throws IOException {
         return ocrUtil.showFront(FileUtil.transfer(file));
     }
}

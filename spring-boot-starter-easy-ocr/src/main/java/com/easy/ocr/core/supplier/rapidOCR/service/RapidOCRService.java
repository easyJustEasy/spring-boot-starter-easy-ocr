package com.easy.ocr.core.supplier.rapidOCR.service;

import com.benjaminwan.ocrlibrary.OcrResult;
import com.easy.ocr.core.core.bean.BankCard;
import com.easy.ocr.core.core.bean.IdCard;
import com.easy.ocr.core.core.service.BankCardService;
import com.easy.ocr.core.core.service.IdCardService;
import com.easy.ocr.core.core.util.BankCheckUtils;
import com.easy.ocr.core.supplier.rapidOCR.bean.RapidOCRResult;
import io.github.mymonstercat.Model;
import io.github.mymonstercat.ocr.InferenceEngine;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RapidOCRService implements BankCardService, IdCardService {
    @Override
    public BankCard showBankNo(File img) {
        RapidOCRResult parse = parse(img, 1);
        return parse == null ? null : parse.getBankCard();
    }

    @Override
    public IdCard showFront(File img) {
        RapidOCRResult parse = parse(img, 2);
        return parse == null ? null : parse.getIdCard();
    }

    @Override
    public IdCard showBack(File img) {
        RapidOCRResult parse = parse(img, 3);
        return parse == null ? null : parse.getIdCard();
    }

    private RapidOCRResult parse(File img, int type) {
        InferenceEngine engine = InferenceEngine.getInstance(Model.ONNX_PPOCR_V3);
        OcrResult ocrResult = engine.runOcr(img.getAbsolutePath());
        return type == 1 ? convertBankCardResult(ocrResult) : type == 2 ? convertIdCardFrontResult(ocrResult) : convertIdCardBackResult(ocrResult);
    }

    private RapidOCRResult convertIdCardBackResult(OcrResult ocrResult) {
        if (ocrResult == null) {
            return null;
        }
        RapidOCRResult result = new RapidOCRResult();
        result.setIdCard(parseIdBackCard(ocrResult.getStrRes()));
        return result;
    }

    private IdCard parseIdBackCard(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("[\\r\\n]+", " ");
        IdCard person = new IdCard();
        person.setValidDate(input.substring(input.indexOf("签发机关") + 4, input.indexOf("有效期限")));
        return person;
    }

    private RapidOCRResult convertBankCardResult(OcrResult ocrResult) {
        if (ocrResult == null) {
            return null;
        }
        RapidOCRResult result = new RapidOCRResult();
        result.setBankCard(parseBankCard(ocrResult.getStrRes()));
        return result;
    }

    private BankCard parseBankCard(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("[\\r\\n]+", " ");
        String pattern = "\\.?(\\d{13,19})\\.?";
        Pattern r = Pattern.compile(pattern);
        // 创建 matcher 对象
        Matcher m = r.matcher(input);
        if (m.find()) {
            BankCard bankCard = new BankCard();
            bankCard.setCardNo(m.group());
            bankCard.setCardName(BankCheckUtils.getNameOfBank(bankCard.getCardNo()));
            return bankCard;
        }
        return null;
    }

    private RapidOCRResult convertIdCardFrontResult(OcrResult ocrResult) {
        if (ocrResult == null) {
            return null;
        }
        RapidOCRResult result = new RapidOCRResult();
        result.setIdCard(parseIdCard(ocrResult.getStrRes()));
        return result;
    }

    private IdCard parseIdCard(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("[\\r\\n]+", " ");
        int idNoIndex = input.indexOf("公民身份号码");
        int idNoLength = 6;
        if(idNoIndex<0){
            idNoIndex = input.indexOf("公民身份证号码");
            idNoLength = 7;
        }
        IdCard person = new IdCard();
        person.setName(input.substring(input.indexOf("姓名") + 2, input.indexOf("性别")));
        person.setCardNo(input.substring(idNoIndex+idNoLength));
        person.setAddress(input.substring(input.indexOf("住址") + 2, idNoIndex));
        person.setBirth(input.substring(input.indexOf("出生") + 2, input.indexOf("住址")));
        person.setSex(input.substring(input.indexOf("性别") + 2, input.indexOf("民族")));
        person.setNation(input.substring(input.indexOf("民族") + 2, input.indexOf("出生")));
        return person;

    }
}

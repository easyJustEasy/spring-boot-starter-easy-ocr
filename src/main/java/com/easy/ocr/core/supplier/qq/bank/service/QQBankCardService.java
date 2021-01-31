package com.easy.ocr.core.supplier.qq.bank.service;

import com.easy.ocr.core.supplier.qq.config.QQconfig;
import com.google.gson.Gson;
import com.qcloud.image.exception.AbstractImageException;
import com.qcloud.image.request.OcrBankCardRequest;
import com.easy.ocr.core.core.bean.BankCard;
import com.easy.ocr.core.core.service.BankCardService;
import com.easy.ocr.core.supplier.qq.QQClientUtil;
import com.easy.ocr.core.supplier.qq.bank.bean.Data;
import com.easy.ocr.core.supplier.qq.bank.bean.Items;
import com.easy.ocr.core.supplier.qq.bank.bean.JsonRootBean;
import com.easy.ocr.core.supplier.qq.bank.consts.BankConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
public class QQBankCardService implements BankCardService {
    private QQconfig qQconfig;
    private QQClientUtil qqClientUtil;

    public QQBankCardService(QQconfig qQconfig, QQClientUtil qqClientUtil) {
        this.qqClientUtil = qqClientUtil;
        this.qQconfig = qQconfig;
    }

    /**
     * OCR-银行卡识别
     *
     * @param
     */
    private  BankCard ocrBankCard(File img) {
        String ret = null;
        OcrBankCardRequest request = new OcrBankCardRequest(qQconfig.getBucketName(), img);
        try {
            ret = qqClientUtil.getClient().ocrBankCard(request);
            JsonRootBean res = new Gson().fromJson(ret, JsonRootBean.class);
            if (res != null && res.getCode() == 0 && res.getData() != null) {
                Data data = res.getData();
                List<Items> items = data.getItems();
                if (items != null && items.size() > 0) {
                    BankCard bankCard = new BankCard();
                    for (int i = 0; i < items.size(); i++) {
                        Items item = items.get(i);
                        String key = item.getItem();
                        String value = item.getItemstring();
                        switch (key) {
                            case BankConsts.CARD_NO:
                                bankCard.setCardNo(value);
                                break;
                            case BankConsts.CARD_TYPE:
                                bankCard.setCardType(value);
                                break;
                            case BankConsts.CARD_NAME:
                                bankCard.setCardName(value);
                                break;
                            case BankConsts.CARD_BANK:
                                bankCard.setCardBank(value);
                                break;
                            case BankConsts.CARD_VALID_DATE:
                                bankCard.setCardValidDate(value);
                                break;
                        }
                    }
                    return bankCard;
                }
            }
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BankCard showBankNo(File img) {
        return ocrBankCard(img);
    }

}

package com.zhuzhu.ocr.supplier.qq.bank.service;

import com.google.gson.Gson;
import com.qcloud.image.exception.AbstractImageException;
import com.qcloud.image.request.OcrBankCardRequest;
import com.zhuzhu.ocr.core.bean.BankCard;
import com.zhuzhu.ocr.core.service.BankCardService;
import com.zhuzhu.ocr.supplier.qq.ClientUtil;
import com.zhuzhu.ocr.supplier.qq.bank.bean.Data;
import com.zhuzhu.ocr.supplier.qq.bank.bean.Items;
import com.zhuzhu.ocr.supplier.qq.bank.bean.JsonRootBean;
import com.zhuzhu.ocr.supplier.qq.bank.consts.BankConsts;
import com.zhuzhu.ocr.supplier.qq.config.QQconfig;

import java.io.File;
import java.util.List;

public class QQBankCardService implements BankCardService {
    /**
     * OCR-银行卡识别
     *
     * @param
     */
    private static BankCard ocrBankCard(File img) {
        String ret = null;
        OcrBankCardRequest request = new OcrBankCardRequest(QQconfig.bucketName, img);
        try {
            ret = ClientUtil.getClient().ocrBankCard(request);
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

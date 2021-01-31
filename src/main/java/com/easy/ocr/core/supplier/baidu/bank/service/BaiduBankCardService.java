package com.easy.ocr.core.supplier.baidu.bank.service;

import com.easy.ocr.core.core.bean.BankCard;
import com.easy.ocr.core.core.service.BankCardService;
import com.easy.ocr.core.supplier.baidu.BaiduClientUtil;
import com.easy.ocr.core.core.util.FileUtil;
import com.easy.ocr.core.supplier.baidu.config.BaiduConfig;
import com.easy.ocr.core.supplier.baidu.consts.CardEnum;
import com.google.gson.Gson;

import com.easy.ocr.core.supplier.baidu.bank.bean.JsonRootBean;
import com.easy.ocr.core.supplier.baidu.bank.bean.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;

public class BaiduBankCardService implements BankCardService {
    private BaiduClientUtil baiduClientUtil;
    private BaiduConfig baiduConfig;

    public BaiduBankCardService(BaiduConfig baiduConfig, BaiduClientUtil baiduClientUtil) {
        this.baiduClientUtil = baiduClientUtil;
        this.baiduConfig = baiduConfig;
    }

    public BankCard bankcard(File img) {

        try {
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<>();
            // 参数为本地图片二进制数组
            byte[] file = FileUtil.file2byte(img);
            JSONObject res = baiduClientUtil.getClient().bankcard(file, options);
            if (res != null) {
                JsonRootBean rootBean = new Gson().fromJson(res.toString(), JsonRootBean.class);
                if (rootBean != null && rootBean.getResult() != null) {
                    Result result = rootBean.getResult();
                    BankCard bankCard = new BankCard();
                    bankCard.setCardNo(result.getBank_card_number() != null ? result.getBank_card_number().replaceAll(" +", "") : "");
                    bankCard.setCardType(CardEnum.getMsgByCode(result.getBank_card_type()));
                    bankCard.setCardBank(result.getBank_name());
                    bankCard.setCardName("");
                    bankCard.setCardValidDate("");
                    return bankCard;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public BankCard showBankNo(File img) {
        return bankcard(img);
    }
}

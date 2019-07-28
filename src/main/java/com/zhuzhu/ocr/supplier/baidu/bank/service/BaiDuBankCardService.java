package com.zhuzhu.ocr.supplier.baidu.bank.service;

import com.google.gson.Gson;

import com.zhuzhu.ocr.core.bean.BankCard;
import com.zhuzhu.ocr.core.service.BankCardService;
import com.zhuzhu.ocr.supplier.baidu.ClientUtil;
import com.zhuzhu.ocr.supplier.baidu.FileUtil;
import com.zhuzhu.ocr.supplier.baidu.bank.bean.JsonRootBean;
import com.zhuzhu.ocr.supplier.baidu.bank.bean.Result;
import com.zhuzhu.ocr.supplier.baidu.consts.CardEnum;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

public class BaiDuBankCardService implements BankCardService {

    public BankCard bankcard(File img) {

        try {
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<>();
            // 参数为本地图片二进制数组
            byte[] file = FileUtil.file2byte(img);
            JSONObject res = ClientUtil.getClient().bankcard(file, options);
            if (res != null) {
                JsonRootBean rootBean = new Gson().fromJson(res.toString(), JsonRootBean.class);
                if (rootBean != null && rootBean.getResult() != null) {
                    Result result = rootBean.getResult();
                    BankCard bankCard = new BankCard();
                    bankCard.setCardNo(result.getBank_card_number()!=null?result.getBank_card_number().replaceAll(" +",""):"");
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

package com.easy.ocr.core.supplier.baidu.idcard.service;

import com.easy.ocr.core.core.bean.IdCard;
import com.easy.ocr.core.core.service.IdCardService;
import com.easy.ocr.core.supplier.baidu.BaiduClientUtil;
import com.easy.ocr.core.core.util.FileUtil;
import com.google.gson.Gson;

import com.easy.ocr.core.supplier.baidu.consts.BaiduConsts;
import com.easy.ocr.core.supplier.baidu.idcard.bean.BaduIdCardItem;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
@Service
public class BaiduIdCardService implements IdCardService {
    @Autowired
    private BaiduClientUtil baiduClientUtil;

    public  IdCard ocrIdCard(File img, String side) {
        try {
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("detect_direction", "true");
            options.put("detect_risk", "false");
            // 参数为本地图片二进制数组
            byte[] file = FileUtil.file2byte(img);
            JSONObject res = baiduClientUtil.getClient().idcard(file, side, options);
            if (res != null) {
                IdCard idCard = new IdCard();
                JSONObject words_result = res.getJSONObject("words_result");
                if (words_result != null) {
                    JSONObject item = words_result.getJSONObject(BaiduConsts.NAME);
                    if (item != null) {
                        BaduIdCardItem baduIdCardItem = new Gson().fromJson(item.toString(), BaduIdCardItem.class);
                        idCard.setName(baduIdCardItem.getWords());
                    }
                    item = words_result.getJSONObject(BaiduConsts.NATION);
                    if (item != null) {
                        BaduIdCardItem baduIdCardItem = new Gson().fromJson(item.toString(), BaduIdCardItem.class);
                        idCard.setNation(baduIdCardItem.getWords());
                    }
                    item = words_result.getJSONObject(BaiduConsts.ADDRESS);
                    if (item != null) {
                        BaduIdCardItem baduIdCardItem = new Gson().fromJson(item.toString(), BaduIdCardItem.class);
                        idCard.setAddress(baduIdCardItem.getWords());
                    }
                    item = words_result.getJSONObject(BaiduConsts.CARD_NO);
                    if (item != null) {
                        BaduIdCardItem baduIdCardItem = new Gson().fromJson(item.toString(), BaduIdCardItem.class);
                        idCard.setCardNo(baduIdCardItem.getWords());
                    }
                    item = words_result.getJSONObject(BaiduConsts.BIRTH);
                    if (item != null) {
                        BaduIdCardItem baduIdCardItem = new Gson().fromJson(item.toString(), BaduIdCardItem.class);
                        idCard.setBirth(baduIdCardItem.getWords());
                    }
                    item = words_result.getJSONObject(BaiduConsts.SEX);
                    if (item != null) {
                        BaduIdCardItem baduIdCardItem = new Gson().fromJson(item.toString(), BaduIdCardItem.class);
                        idCard.setSex(baduIdCardItem.getWords());
                    }
                    return idCard;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public IdCard showFront(File img) {
        return ocrIdCard(img, BaiduConsts.FRONT);
    }

    @Override
    public IdCard showBack(File img) {
        return ocrIdCard(img, BaiduConsts.BACK);
    }
}

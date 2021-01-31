package com.easy.ocr.core.supplier.qq.idcard.service;

import com.google.gson.Gson;
import com.qcloud.image.exception.AbstractImageException;
import com.qcloud.image.request.IdcardDetectRequest;
import com.easy.ocr.core.core.bean.IdCard;
import com.easy.ocr.core.core.service.IdCardService;
import com.easy.ocr.core.supplier.qq.QQClientUtil;
import com.easy.ocr.core.supplier.qq.config.QQconfig;
import com.easy.ocr.core.supplier.qq.idcard.bean.Data;
import com.easy.ocr.core.supplier.qq.idcard.bean.JsonRootBean;
import com.easy.ocr.core.supplier.qq.idcard.bean.Result_list;
import com.easy.ocr.core.supplier.qq.idcard.consts.QQIDCardConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
@Service
public class QQIdCardService implements IdCardService {
    @Autowired
    private QQconfig qQconfig;
    @Autowired
    private QQClientUtil qqClientUtil;

    /**
     * 身份证ocr识别操作
     */
    private  IdCard ocrIdCard(File img, Integer cardType) {
        String ret = null;
        /*0:正面, 1:反面*0:不返回身份证照片, 1:返回*/
        IdcardDetectRequest idReq = new IdcardDetectRequest(qQconfig.getBucketName(), new File[]{img}, cardType);
        try {
            ret = qqClientUtil.getClient().idcardDetect(idReq);
            JsonRootBean res = new Gson().fromJson(ret, JsonRootBean.class);
            if (res != null && res.getResult_list() != null && res.getResult_list().size() > 0 && res.getResult_list().get(0) != null) {
                Result_list QQIdCardResult_ = res.getResult_list().get(0);
                if (QQIdCardResult_ != null && QQIdCardResult_.getCode() == 0) {
                    Data idcard = QQIdCardResult_.getData();

                    if (idcard != null) {
                        IdCard card = new IdCard();
                        card.setName(idcard.getName());
                        card.setSex(idcard.getSex());
                        card.setNation(idcard.getNation());
                        card.setBirth(idcard.getBirth());
                        card.setAddress(idcard.getAddress());
                        card.setCardNo(idcard.getId());
                        return card;
                    }
                }
            }
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public IdCard showFront(File img) {
        return ocrIdCard(img, QQIDCardConsts.ID_CARD_FRONT);
    }

    @Override
    public IdCard showBack(File img) {
        return ocrIdCard(img, QQIDCardConsts.ID_CARD_BACK);
    }
}

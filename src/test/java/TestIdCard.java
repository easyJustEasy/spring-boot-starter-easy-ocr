

import com.zhuzhu.ocr.core.bean.BankCard;
import com.zhuzhu.ocr.core.bean.IdCard;
import com.zhuzhu.ocr.supplier.baidu.bank.service.BaiDuBankCardService;
import com.zhuzhu.ocr.supplier.baidu.idcard.service.BaiDuIdCardService;
import com.zhuzhu.ocr.supplier.qq.bank.service.QQBankCardService;
import com.zhuzhu.ocr.supplier.qq.idcard.service.QQIdCardService;

import java.io.File;

public class TestIdCard {
    public static void main(String[] args) {
        QQIdCardService qQuIdCardService = new QQIdCardService();
        IdCard idCard = qQuIdCardService.showFront(new File("d:\\ocr\\1.jpg"));
        System.out.println(idCard);
        QQBankCardService bankCardService = new QQBankCardService();
        BankCard bankCard = bankCardService.showBankNo(new File("d:\\ocr\\3.jpg"));
        System.out.println(bankCard);


        BaiDuIdCardService baiDuIdCardService = new BaiDuIdCardService();
        IdCard baiduId = baiDuIdCardService.showFront(new File("d:\\ocr\\1.jpg"));
        System.out.println(baiduId);


        BaiDuBankCardService baiDuBankCardService = new BaiDuBankCardService();
        BankCard bankCard1 = baiDuBankCardService.bankcard(new File("d:\\ocr\\3.jpg"));
        System.out.println(bankCard1);
    }
}

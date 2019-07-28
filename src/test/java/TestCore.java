

import com.zhuzhu.ocr.core.bean.BankCard;
import com.zhuzhu.ocr.core.bean.IdCard;
import com.zhuzhu.ocr.core.factory.IdCardAndBankCardFactory;

import java.io.File;

public class TestCore {
    public static void main(String[] args) {
        BankCard bankCard =  IdCardAndBankCardFactory.createIdCardAndBankCardService().showBankNo(new File("d:\\ocr\\2.jpg"));
        IdCard idCard =  IdCardAndBankCardFactory.createIdCardAndBankCardService().showFront(new File("d:\\ocr\\1.jpg"));
        System.out.println(bankCard);
        System.out.println(idCard);
    }
}

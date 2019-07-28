
import com.zhuzhu.ocr.core.bean.IdCard;
import com.zhuzhu.ocr.core.factory.IdCardAndBankCardFactory;
import com.zhuzhu.ocr.core.service.impl.IdCardAndBankCardServiceImpl;

import java.io.File;
import java.util.Arrays;

public class TestdCards {
    private static IdCardAndBankCardServiceImpl idCardAndBankCardService = IdCardAndBankCardFactory.createIdCardAndBankCardService();

    public static void main(String[] args) {
        while (true) {
            File dir = new File("D:\\work\\ids\\imgs");
            File[] files = dir.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(i -> {
                    IdCard idCard = idCardAndBankCardService.showFront(i);
                    System.out.println(idCard);
                });

            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

import com.easy.ocr.App;
import com.easy.ocr.core.core.bean.BankCard;
import com.easy.ocr.core.core.bean.IdCard;
import com.easy.ocr.core.core.service.impl.EasyOcrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest(classes = App.class)
public class TestRapidOCRService {
    @Autowired
    private EasyOcrUtil easyOcrUtil;
    @Test
    public void test(){
        IdCard idCard = easyOcrUtil.showFront(new File("G:\\img\\IMG_20241111_0002.jpg"));
        System.out.println(idCard);
         idCard = easyOcrUtil.showBack(new File("G:\\img\\IMG_20241111_0003.jpg"));
        System.out.println(idCard);
    }
    @Test
    public void testBank(){
        BankCard bankCard = easyOcrUtil.showBankNo(new File("C:\\Users\\siyua\\Pictures\\Screenshots\\1.jpg"));
        System.out.println(bankCard);
    }
}

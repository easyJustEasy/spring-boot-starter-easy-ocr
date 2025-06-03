import com.easy.ocr.App;
import com.easy.ocr.core.core.bean.BankCard;
import com.easy.ocr.core.core.bean.IdCard;
import com.easy.ocr.core.core.service.impl.EasyOcrUtil;
import com.easy.ocr.core.supplier.baidu.idcard.service.BaiduIdCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootTest(classes = App.class)
public class TestRapidOCRService {
    @Autowired
    private EasyOcrUtil easyOcrUtil;
    @Autowired
    private BaiduIdCardService baiduIdCardService;
    @Test
    public void test() throws FileNotFoundException {
        IdCard idCard = easyOcrUtil.showFront(ResourceUtils.getFile("classpath:img/idcard/1.png"));
        System.out.println(idCard);
    }
    @Test
    public void testBank() throws FileNotFoundException {
        BankCard bankCard = easyOcrUtil.showBankNo(ResourceUtils.getFile("classpath:img/bank/1.jpg"));
        System.out.println(bankCard);
    }
    @Test
    public void testBaidu() throws FileNotFoundException {
         IdCard idCard = baiduIdCardService.showFront(ResourceUtils.getFile("classpath:img/idcard/1.png"));
          System.out.println(idCard);
    }
}

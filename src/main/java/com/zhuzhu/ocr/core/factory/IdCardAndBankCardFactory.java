package com.zhuzhu.ocr.core.factory;

import com.zhuzhu.ocr.core.service.impl.IdCardAndBankCardServiceImpl;

public class IdCardAndBankCardFactory {
    private static IdCardAndBankCardServiceImpl impl = null;

    /**
     * 单例
     *
     * @return
     */
    public static IdCardAndBankCardServiceImpl createIdCardAndBankCardService() {
        if (impl == null) {
            impl = new IdCardAndBankCardServiceImpl();
        }
        return impl;
    }
}

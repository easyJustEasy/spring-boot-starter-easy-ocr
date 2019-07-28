/**
  * Copyright 2019 bejson.com 
  */
package com.zhuzhu.ocr.supplier.qq.idcard.bean;
import java.util.List;

public class JsonRootBean {

    private List<Result_list> result_list;
    public void setResult_list(List<Result_list> result_list) {
         this.result_list = result_list;
     }
     public List<Result_list> getResult_list() {
         return result_list;
     }

}
/**
  * Copyright 2019 bejson.com 
  */
package com.zhuzhu.ocr.supplier.qq.bank.bean;


public class JsonRootBean {

    private int code;
    private String message;
    private String seq;
    private Data data;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setSeq(String seq) {
         this.seq = seq;
     }
     public String getSeq() {
         return seq;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

}
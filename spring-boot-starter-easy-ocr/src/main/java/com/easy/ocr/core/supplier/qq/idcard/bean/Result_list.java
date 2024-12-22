/**
  * Copyright 2019 bejson.com 
  */
package com.easy.ocr.core.supplier.qq.idcard.bean;

public class Result_list {

    private int code;
    private String message;
    private String seq;
    private String filename;
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

    public void setFilename(String filename) {
         this.filename = filename;
     }
     public String getFilename() {
         return filename;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

}
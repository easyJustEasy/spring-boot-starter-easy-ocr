/**
  * Copyright 2019 bejson.com 
  */
package com.easy.ocr.core.supplier.qq.bank.bean;
import java.util.List;

public class Data {

    private List<Items> items;
    private String session_id;
    private List<String> recognize_warn_code;
    private List<String> recognize_warn_msg;
    public void setItems(List<Items> items) {
         this.items = items;
     }
     public List<Items> getItems() {
         return items;
     }

    public void setSession_id(String session_id) {
         this.session_id = session_id;
     }
     public String getSession_id() {
         return session_id;
     }

    public void setRecognize_warn_code(List<String> recognize_warn_code) {
         this.recognize_warn_code = recognize_warn_code;
     }
     public List<String> getRecognize_warn_code() {
         return recognize_warn_code;
     }

    public void setRecognize_warn_msg(List<String> recognize_warn_msg) {
         this.recognize_warn_msg = recognize_warn_msg;
     }
     public List<String> getRecognize_warn_msg() {
         return recognize_warn_msg;
     }

}
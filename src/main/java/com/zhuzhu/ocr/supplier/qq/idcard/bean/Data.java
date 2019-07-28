/**
  * Copyright 2019 bejson.com 
  */
package com.zhuzhu.ocr.supplier.qq.idcard.bean;

import java.util.List;

public class Data {

    private String name;
    private String sex;
    private String nation;
    private String birth;
    private String address;
    private String id;
    private List<Integer> name_confidence_all;
    private List<Integer> sex_confidence_all;
    private List<Integer> nation_confidence_all;
    private List<Integer> birth_confidence_all;
    private List<Integer> address_confidence_all;
    private List<Integer> id_confidence_all;
    private int card_type;
    private List<String> recognize_warn_code;
    private List<String> recognize_warn_msg;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setSex(String sex) {
         this.sex = sex;
     }
     public String getSex() {
         return sex;
     }

    public void setNation(String nation) {
         this.nation = nation;
     }
     public String getNation() {
         return nation;
     }

    public void setBirth(String birth) {
         this.birth = birth;
     }
     public String getBirth() {
         return birth;
     }

    public void setAddress(String address) {
         this.address = address;
     }
     public String getAddress() {
         return address;
     }

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setName_confidence_all(List<Integer> name_confidence_all) {
         this.name_confidence_all = name_confidence_all;
     }
     public List<Integer> getName_confidence_all() {
         return name_confidence_all;
     }

    public void setSex_confidence_all(List<Integer> sex_confidence_all) {
         this.sex_confidence_all = sex_confidence_all;
     }
     public List<Integer> getSex_confidence_all() {
         return sex_confidence_all;
     }

    public void setNation_confidence_all(List<Integer> nation_confidence_all) {
         this.nation_confidence_all = nation_confidence_all;
     }
     public List<Integer> getNation_confidence_all() {
         return nation_confidence_all;
     }

    public void setBirth_confidence_all(List<Integer> birth_confidence_all) {
         this.birth_confidence_all = birth_confidence_all;
     }
     public List<Integer> getBirth_confidence_all() {
         return birth_confidence_all;
     }

    public void setAddress_confidence_all(List<Integer> address_confidence_all) {
         this.address_confidence_all = address_confidence_all;
     }
     public List<Integer> getAddress_confidence_all() {
         return address_confidence_all;
     }

    public void setId_confidence_all(List<Integer> id_confidence_all) {
         this.id_confidence_all = id_confidence_all;
     }
     public List<Integer> getId_confidence_all() {
         return id_confidence_all;
     }

    public void setCard_type(int card_type) {
         this.card_type = card_type;
     }
     public int getCard_type() {
         return card_type;
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
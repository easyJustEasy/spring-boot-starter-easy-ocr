/**
  * Copyright 2019 bejson.com 
  */
package com.easy.ocr.core.supplier.qq.bank.bean;
import java.util.List;


public class Items {

    private String item;
    private Itemcoord itemcoord;
    private double itemconf;
    private String itemstring;
    private List<String> coords;
    private List<String> words;
    private List<String> candword;
    private List<String> wordcoordpoint;
    public void setItem(String item) {
         this.item = item;
     }
     public String getItem() {
         return item;
     }

    public void setItemcoord(Itemcoord itemcoord) {
         this.itemcoord = itemcoord;
     }
     public Itemcoord getItemcoord() {
         return itemcoord;
     }

    public void setItemconf(double itemconf) {
         this.itemconf = itemconf;
     }
     public double getItemconf() {
         return itemconf;
     }

    public void setItemstring(String itemstring) {
         this.itemstring = itemstring;
     }
     public String getItemstring() {
         return itemstring;
     }

    public void setCoords(List<String> coords) {
         this.coords = coords;
     }
     public List<String> getCoords() {
         return coords;
     }

    public void setWords(List<String> words) {
         this.words = words;
     }
     public List<String> getWords() {
         return words;
     }

    public void setCandword(List<String> candword) {
         this.candword = candword;
     }
     public List<String> getCandword() {
         return candword;
     }

    public void setWordcoordpoint(List<String> wordcoordpoint) {
         this.wordcoordpoint = wordcoordpoint;
     }
     public List<String> getWordcoordpoint() {
         return wordcoordpoint;
     }

}
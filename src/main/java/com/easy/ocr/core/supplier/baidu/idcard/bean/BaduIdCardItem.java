package com.easy.ocr.core.supplier.baidu.idcard.bean;

public class BaduIdCardItem {
    private String words;
    private Point location;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}

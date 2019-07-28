package com.zhuzhu.ocr.core.bean;

public class BankCard {
    private String cardNo;
    private String cardType;
    private String cardName;
    private String cardBank;
    private String cardValidDate;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public String getCardValidDate() {
        return cardValidDate;
    }

    public void setCardValidDate(String cardValidDate) {
        this.cardValidDate = cardValidDate;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "cardNo='" + cardNo + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardBank='" + cardBank + '\'' +
                ", cardValidDate='" + cardValidDate + '\'' +
                '}';
    }
}

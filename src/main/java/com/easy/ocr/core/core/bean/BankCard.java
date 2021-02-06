package com.easy.ocr.core.core.bean;

public class BankCard {
    //银行卡号
    private String cardNo;
    //卡类型
    private String cardType;
    //卡名
    private String cardName;
    //银行
    private String cardBank;
    //有效期
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

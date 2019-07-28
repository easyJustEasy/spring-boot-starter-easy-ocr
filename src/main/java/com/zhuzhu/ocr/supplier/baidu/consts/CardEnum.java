package com.zhuzhu.ocr.supplier.baidu.consts;

public enum  CardEnum {
    UN_KNOW(0, "不能识别"), JIEJI(1, "借记卡"), XINYONG(2, "信用卡");
    //    银行卡类型，0:不能识别; 1: 借记卡; 2: 信用卡
    private int code;
    private String msg;

    CardEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsgByCode(int code){
        for (int i = 0; i < values().length; i++) {
            if(values()[i].getCode()==code){
                return values()[i].getMsg();
            }
        }
        return "";
    }
}

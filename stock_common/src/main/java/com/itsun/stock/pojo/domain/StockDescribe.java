package com.itsun.stock.pojo.domain;

public class StockDescribe {
     private String code;//股票编码
     private String trade;//行业，也就是行业板块名称
     private String business;//公司主营业务
     private String name;//公司名称

    @Override
    public String toString() {
        return "StockDescribe{" +
                "code='" + code + '\'' +
                ", trade='" + trade + '\'' +
                ", business='" + business + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

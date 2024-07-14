package com.itsun.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@ApiModel("大盘板块指数")
public class StockBlockDomain {
     private Integer companyNum;//公司数量
     private Long tradeAmt;//交易量
     private String code;//板块编码
     private BigDecimal avgPrice;//平均价格
     private String name;//板块名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private Date curDate;//当前日期
     private BigDecimal tradeVol;//交易总金额
     private BigDecimal updownRate;//涨幅

    @Override
    public String toString() {
        return "StockBlockDomain{" +
                "companyNum=" + companyNum +
                ", tradeAmt=" + tradeAmt +
                ", code=" + code +
                ", avgPrice=" + avgPrice +
                ", name='" + name + '\'' +
                ", curDate=" + curDate +
                ", tradeVol=" + tradeVol +
                ", updownRate=" + updownRate +
                '}';
    }

    public Integer getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(Integer companyNum) {
        this.companyNum = companyNum;
    }

    public Long getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(Long tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

    public BigDecimal getTradeVol() {
        return tradeVol;
    }

    public void setTradeVol(BigDecimal tradeVol) {
        this.tradeVol = tradeVol;
    }

    public BigDecimal getUpdownRate() {
        return updownRate;
    }

    public void setUpdownRate(BigDecimal updownRate) {
        this.updownRate = updownRate;
    }
}

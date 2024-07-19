package com.itsun.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class StockDetailDomain {
    private BigDecimal tradeAmt;//最新交易量
    private BigDecimal preClosePrice;//前收盘价格
    private BigDecimal lowPrice;//最低价
    private BigDecimal highPrice;//最高价
    private BigDecimal openPrice;//开盘价
    private BigDecimal tradeVol;//交易金额
    private BigDecimal tradePrice;//当前价格
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate;//当前日期

    @Override
    public String toString() {
        return "StockDetailDomain{" +
                "tradeAmt=" + tradeAmt +
                ", preClosePrice=" + preClosePrice +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                ", openPrice=" + openPrice +
                ", tradeVol=" + tradeVol +
                ", tradePrice=" + tradePrice +
                ", curDate=" + curDate +
                '}';
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getPreClosePrice() {
        return preClosePrice;
    }

    public void setPreClosePrice(BigDecimal preClosePrice) {
        this.preClosePrice = preClosePrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getTradeVol() {
        return tradeVol;
    }

    public void setTradeVol(BigDecimal tradeVol) {
        this.tradeVol = tradeVol;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }
}

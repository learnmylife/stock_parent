package com.itsun.stock.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 个股详情信息表
 * @TableName stock_rt_info
 */
public class StockRtInfo implements Serializable {
    /**
     * 主键字段（无业务意义）
     */
    private Long id;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 前收盘价| 昨日收盘价
     */
    private BigDecimal preClosePrice;

    /**
     * 开盘价
     */
    private BigDecimal openPrice;

    /**
     * 当前价格
     */
    private BigDecimal curPrice;

    /**
     * 今日最低价
     */
    private BigDecimal minPrice;

    /**
     * 今日最高价
     */
    private BigDecimal maxPrice;

    /**
     * 成交量
     */
    private Long tradeAmount;

    /**
     * 成交金额
     */
    private BigDecimal tradeVolume;

    /**
     * 当前时间
     */
    private Date curTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "StockRtInfo{" +
                "id=" + id +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", preClosePrice=" + preClosePrice +
                ", openPrice=" + openPrice +
                ", curPrice=" + curPrice +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", tradeAmount=" + tradeAmount +
                ", tradeVolume=" + tradeVolume +
                ", curTime=" + curTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public BigDecimal getPreClosePrice() {
        return preClosePrice;
    }

    public void setPreClosePrice(BigDecimal preClosePrice) {
        this.preClosePrice = preClosePrice;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(BigDecimal curPrice) {
        this.curPrice = curPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Long getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Long tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(BigDecimal tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public Date getCurTime() {
        return curTime;
    }

    public void setCurTime(Date curTime) {
        this.curTime = curTime;
    }
}
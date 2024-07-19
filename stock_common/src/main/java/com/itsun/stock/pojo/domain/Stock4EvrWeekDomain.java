package com.itsun.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel
public class Stock4EvrWeekDomain {
    @ApiModelProperty(value = "一周内平均价", position = 1)
    private BigDecimal avgPrice;//一周内平均价
    @ApiModelProperty(value = "一周内最低价", position = 2)
    private BigDecimal minPrice;//一周内最低价
    @ApiModelProperty(value = "周一开盘价", position = 3)
    private BigDecimal openPrice;//周一开盘价
    @ApiModelProperty(value = "一周内最高价", position = 4)
    private BigDecimal maxPrice;//一周内最高价
    @ApiModelProperty(value = "周五收盘价", position = 5)
    private BigDecimal closePrice;//周五收盘价（如果当前日期不到周五，则显示最新价格）
    @ApiModelProperty(value = "一周内最大时间", position = 6)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date mxTime;//一周内最大时间
    @ApiModelProperty(value = "股票编码", position = 7)
    private String stockCode;//股票编码

    @Override
    public String toString() {
        return "Stock4EvrWeekDomain{" +
                "avgPrice=" + avgPrice +
                ", minPrice=" + minPrice +
                ", openPrice=" + openPrice +
                ", maxPrice=" + maxPrice +
                ", closePrice=" + closePrice +
                ", mxTime=" + mxTime +
                ", stockCode='" + stockCode + '\'' +
                '}';
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public Date getMxTime() {
        return mxTime;
    }

    public void setMxTime(Date mxTime) {
        this.mxTime = mxTime;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}

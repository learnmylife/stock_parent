package com.itsun.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel
public class Stock4MinuteDomain {
    /**
     * 日期，eg:202201280809
     */
    @ApiModelProperty(value = "日期，eg:202201280809", position = 1)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "Asia/Shanghai")
    private Date date;
    /**
     * 交易量
     */

    @ApiModelProperty(value = "交易量", position = 2)
    private Long tradeAmt;
    /**
     * 股票编码
     */
    @ApiModelProperty(value = "股票编码", position = 3)
    private String code;
    /**
     * 最低价
     */
    @ApiModelProperty(value = "最低价", position = 4)
    private BigDecimal lowPrice;
    /**
     * 前收盘价
     */
    @ApiModelProperty(value = "前收盘价", position = 5)
    private BigDecimal preClosePrice;
    /**
     * 股票名称
     */
    @ApiModelProperty(value = "股票名称", position = 6)
    private String name;
    /**
     * 最高价
     */
    @ApiModelProperty(value = "最高价", position = 7)
    private BigDecimal highPrice;
    /**
     * 开盘价
     */
    @ApiModelProperty(value = "开盘价", position = 8)
    private BigDecimal openPrice;

    /**
     * 当前交易总金额
     */
    @ApiModelProperty(value = "当前交易总金额", position = 9)
    private BigDecimal tradeVol;
    /**
     * 当前价格
     */
    @ApiModelProperty(value = "当前价格", position = 10)
    private BigDecimal tradePrice;

    @Override
    public String toString() {
        return "Stock4MinuteDomain{" +
                "date=" + date +
                ", tradeAmt=" + tradeAmt +
                ", code='" + code + '\'' +
                ", lowPrice=" + lowPrice +
                ", preClosePrice=" + preClosePrice +
                ", name='" + name + '\'' +
                ", highPrice=" + highPrice +
                ", openPrice=" + openPrice +
                ", tradeVol=" + tradeVol +
                ", tradePrice=" + tradePrice +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getPreClosePrice() {
        return preClosePrice;
    }

    public void setPreClosePrice(BigDecimal preClosePrice) {
        this.preClosePrice = preClosePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

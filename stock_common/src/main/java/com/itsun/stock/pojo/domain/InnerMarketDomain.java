package com.itsun.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
@ApiModel("大盘数据")
public class InnerMarketDomain {
    @ApiModelProperty("大盘编码")
    private String code;//大盘编码
    @ApiModelProperty("大盘名称")
    private String name;//大盘名称
    @ApiModelProperty("开盘点")
    private BigDecimal openPoint;//开盘点
    @ApiModelProperty("当前点")
    private BigDecimal curPoint;//当前点
    @ApiModelProperty("前收盘点")
    private BigDecimal preClosePoint;//前收盘点
    @ApiModelProperty("交易量")
    private Long tradeAmt;//交易量
    @ApiModelProperty("交易金额")
    private BigDecimal tradeVol;//交易金额
    @ApiModelProperty("涨跌值")
    private BigDecimal upDown;//涨跌值
    @ApiModelProperty("涨幅")
    private BigDecimal rose;//涨幅
    @ApiModelProperty("振幅")
    private BigDecimal amplitude;//振幅
    @ApiModelProperty("当前时间")
    @JsonFormat(pattern = "yyyy-MM-dd")//规定日期的json格式
    private Date curTime;//当前时间

    @Override
    public String toString() {
        return "InnerMarketDomain{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", openPoint=" + openPoint +
                ", curPoint=" + curPoint +
                ", preClosePoint=" + preClosePoint +
                ", tradeAmt=" + tradeAmt +
                ", tradeVol=" + tradeVol +
                ", upDown=" + upDown +
                ", rose=" + rose +
                ", amplitude=" + amplitude +
                ", curTime=" + curTime +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOpenPoint() {
        return openPoint;
    }

    public void setOpenPoint(BigDecimal openPoint) {
        this.openPoint = openPoint;
    }

    public BigDecimal getCurPoint() {
        return curPoint;
    }

    public void setCurPoint(BigDecimal curPoint) {
        this.curPoint = curPoint;
    }

    public BigDecimal getPreClosePoint() {
        return preClosePoint;
    }

    public void setPreClosePoint(BigDecimal preClosePoint) {
        this.preClosePoint = preClosePoint;
    }

    public Long getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(Long tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getTradeVol() {
        return tradeVol;
    }

    public void setTradeVol(BigDecimal tradeVol) {
        this.tradeVol = tradeVol;
    }

    public BigDecimal getUpDown() {
        return upDown;
    }

    public void setUpDown(BigDecimal upDown) {
        this.upDown = upDown;
    }

    public BigDecimal getRose() {
        return rose;
    }

    public void setRose(BigDecimal rose) {
        this.rose = rose;
    }

    public BigDecimal getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(BigDecimal amplitude) {
        this.amplitude = amplitude;
    }

    public Date getCurTime() {
        return curTime;
    }

    public void setCurTime(Date curTime) {
        this.curTime = curTime;
    }
}

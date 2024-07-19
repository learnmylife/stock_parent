package com.itsun.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class externalMarketDomain {
    private String name;//大盘名称
    private BigDecimal curPoint;//当前大盘点
    private BigDecimal upDown;//涨跌值
    private BigDecimal rose;//涨幅
    @JsonFormat(pattern = "yyyyMMdd")//规定日期的json格式
    private Date curTime;//当前时间

    @Override
    public String toString() {
        return "externalMarketDomain{" +
                "name='" + name + '\'' +
                ", curPoint=" + curPoint +
                ", upDown=" + upDown +
                ", rose=" + rose +
                ", curTime=" + curTime +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurPoint() {
        return curPoint;
    }

    public void setCurPoint(BigDecimal curPoint) {
        this.curPoint = curPoint;
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

    public Date getCurTime() {
        return curTime;
    }

    public void setCurTime(Date curTime) {
        this.curTime = curTime;
    }
}

package com.itsun.stock.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 股票板块详情信息表
 * @TableName stock_block_rt_info
 */
public class StockBlockRtInfo implements Serializable {
    /**
     * 板块主键ID（业务无关）
     */
    private Long id;

    /**
     * 表示，如：new_blhy-玻璃行业
     */
    private String label;

    /**
     * 板块名称
     */
    private String blockName;

    /**
     * 公司数量
     */
    private Integer companyNum;

    /**
     * 平均价格
     */
    private BigDecimal avgPrice;

    /**
     * 涨跌幅
     */
    private BigDecimal updownRate;

    /**
     * 交易量
     */
    private Long tradeAmount;

    /**
     * 交易金额
     */
    private BigDecimal tradeVolume;

    /**
     * 当前日期（精确到秒）
     */
    private Date curTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "StockBlockRtInfo{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", blockName='" + blockName + '\'' +
                ", companyNum=" + companyNum +
                ", avgPrice=" + avgPrice +
                ", updownRate=" + updownRate +
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Integer getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(Integer companyNum) {
        this.companyNum = companyNum;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getUpdownRate() {
        return updownRate;
    }

    public void setUpdownRate(BigDecimal updownRate) {
        this.updownRate = updownRate;
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
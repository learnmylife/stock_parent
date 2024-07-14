package com.itsun.stock.pojo.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "stock")
//并非容器注解,需要单独加上@Component,或者在其他类中配置@EnableConfigurationProperties(StockInfoConfig.class)
public class StockInfoConfig {
    //A股大盘ID集合
    private List<String> inner;
    //外盘ID集合
    private List<String> outer;

    private String marketUrl;

    private String blockUrl;

    private List<String> upDownRange;

    @Override
    public String toString() {
        return "StockInfoConfig{" +
                "inner=" + inner +
                ", outer=" + outer +
                ", marketUrl='" + marketUrl + '\'' +
                ", blockUrl='" + blockUrl + '\'' +
                ", upDownRange=" + upDownRange +
                '}';
    }

    public List<String> getUpDownRange() {
        return upDownRange;
    }

    public void setUpDownRange(List<String> upDownRange) {
        this.upDownRange = upDownRange;
    }

    public String getMarketUrl() {
        return marketUrl;
    }

    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    public String getBlockUrl() {
        return blockUrl;
    }

    public void setBlockUrl(String blockUrl) {
        this.blockUrl = blockUrl;
    }

    public List<String> getInner() {
        return inner;
    }

    public void setInner(List<String> inner) {
        this.inner = inner;
    }

    public List<String> getOuter() {
        return outer;
    }

    public void setOuter(List<String> outer) {
        this.outer = outer;
    }
}

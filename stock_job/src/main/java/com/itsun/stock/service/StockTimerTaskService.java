package com.itsun.stock.service;

import org.springframework.boot.configurationprocessor.json.JSONException;

public interface StockTimerTaskService {
    /**
     * 获取国内大盘的实时数据信息
     */
    void getInnerMarketInfo();
    void getInneBlockInfo() throws JSONException;

    void getStockRtIndex();
}

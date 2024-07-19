package com.itsun.stock.service;

import com.itsun.stock.pojo.domain.*;
import com.itsun.stock.vo.resp.PageRespVo;
import com.itsun.stock.vo.resp.R;
import com.itsun.stock.vo.resp.UpdownRespVo;


import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface StockService {
    R<List<InnerMarketDomain>> getInnerMarketInfo();


    R<List<StockBlockDomain>> getStockBlock();

    R<PageRespVo<StockUpdownDomain>> getPageStockUpdown(Integer page, Integer pageSize);


    R<UpdownRespVo> getStockUpdownCount();

    void stockExport(HttpServletResponse response, Integer page, Integer pageSize);

    R<Map<String, List<Map>>> stockTradeVol4InnerMarket();

    R<Map> stockUpDownScopeCount();

    R<List<Stock4MinuteDomain>> stockScreenTimeSharing(String code);

    R<List<Stock4EvrDayDomain>> stockCreenDkLine(String stockCode,Integer flag);

    R<List<Stock4EvrWeekDomain>> stockCreenWkLine(String stockCode);

    R<List<externalMarketDomain>> stockExternalMarket(Integer count);


    R<List<Map>> stockSearchMarket(String searchStr);

    R<StockDescribe> getStockDescribe(String stockCode);

    R<StockDetailDomain> getStockDetail(String stockCode);

    R<List<Map<String,Object>>> getStockSecond(String stockCode);
}

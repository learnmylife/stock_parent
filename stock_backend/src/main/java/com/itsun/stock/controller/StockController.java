package com.itsun.stock.controller;

import com.itsun.stock.pojo.domain.*;
import com.itsun.stock.service.StockService;
import com.itsun.stock.vo.resp.PageRespVo;
import com.itsun.stock.vo.resp.R;
import com.itsun.stock.vo.resp.UpdownRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quot")
public class StockController {
    @Autowired
    private StockService stockService;

    @ApiOperation(value = "获取国内大盘最新数据", notes = "", httpMethod = "GET")
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> getInnerMarketInfo(){
        return stockService.getInnerMarketInfo();
    }
    @ApiOperation(value = "获取大盘模块信息", notes = "", httpMethod = "GET")
    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> getStockBolck(){
        return stockService.getStockBlock();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "")
    })
    @ApiOperation(value = "获取大盘分页信息", notes = "", httpMethod = "GET")
    @GetMapping("/stock/all")
    public R<PageRespVo<StockUpdownDomain>> getPageStockUpdown(@RequestParam(name = "page",required = false,defaultValue = "1") Integer page,
                                                               @RequestParam(name = "pageSize",required = false,defaultValue = "20") Integer pageSize){
        return stockService.getPageStockUpdown(page,pageSize);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "")
    })
    @ApiOperation(value = "小涨幅榜获取", notes = "", httpMethod = "GET")
    @GetMapping("/stock/increase")
    public R<List<StockUpdownDomain>> getStockUpdown(@RequestParam(name = "page",required = false,defaultValue = "1") Integer page,
                                                               @RequestParam(name = "pageSize",required = false,defaultValue = "4") Integer pageSize){
        R<PageRespVo<StockUpdownDomain>> pageStockUpdown = stockService.getPageStockUpdown(page, pageSize);
        List<StockUpdownDomain> rows = pageStockUpdown.getData().getRows();
        return R.ok(rows);
    }
    @ApiOperation(value = "统计最新交易日下股票每分钟涨跌停的数量", notes = "", httpMethod = "GET")
    @GetMapping("/stock/updown/count")
    public R<UpdownRespVo> getStockUpdownCount(){
        return stockService.getStockUpdownCount();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "")
    })
    @ApiOperation(value = "导出股票涨幅信息", notes = "", httpMethod = "GET")
    @GetMapping("/stock/export")
    public void stockExport(HttpServletResponse response,
                            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                            @RequestParam(value = "pageSize",required = false,defaultValue = "20")Integer pageSize){
        stockService.stockExport(response,page,pageSize);
    }

    @ApiOperation(value = "两天交易量对比", notes = "", httpMethod = "GET")
    @GetMapping("/stock/tradeAmt")
    public R<Map<String,List<Map>>> stockTradeVol4InnerMarket(){
        return stockService.stockTradeVol4InnerMarket();
    }

    @ApiOperation(value = "获取涨幅区间数量", notes = "", httpMethod = "GET")
    @GetMapping("/stock/updown")
    public R<Map> getStockUpDown(){
        return stockService.stockUpDownScopeCount();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "code", value = "", required = true)
    })
    @ApiOperation(value = "分时数据", notes = "", httpMethod = "GET")
    @GetMapping("/stock/screen/time-sharing")
    public R<List<Stock4MinuteDomain>> stockScreenTimeSharing(@RequestParam("code") String code){
        return stockService.stockScreenTimeSharing(code);
    }

    @ApiOperation(value = "获取每日K线数据", notes = "", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "code", value = "", required = true)
    })
    @RequestMapping("/stock/screen/dkline")
    public R<List<Stock4EvrDayDomain>> getDayKLinData(@RequestParam("code") String stockCode){
        Integer flag = 0;
        return stockService.stockCreenDkLine(stockCode,flag);
    }
    @ApiOperation(value = "获取每周K线数据", notes = "", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "code", value = "", required = true)
    })
    @RequestMapping("/stock/screen/weekkline")
    public R<List<Stock4EvrWeekDomain>> getWeekKLinData(@RequestParam("code") String stockCode){
        return stockService.stockCreenWkLine(stockCode);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "count", value = "", required = true)
    })
    @ApiOperation(value = "获取外盘数据", notes = "", httpMethod = "GET")
    @GetMapping("/external/index")
    public R<List<externalMarketDomain>> getexternalMarketData(@RequestParam(value = "count", defaultValue = "4") Integer count){
        return stockService.stockExternalMarket(count);
    }
    @GetMapping("/stock/search")
    public R<List<Map>> getexternalMarketData(String searchStr){
        return stockService.stockSearchMarket(searchStr);
    }
    @ApiOperation(value = "个股主营业务查询接口", notes = "", httpMethod = "GET")
    @GetMapping("/stock/describe")
    public R<StockDescribe> getStockDescribe(@RequestParam("code") String stockCode){
        return stockService.getStockDescribe(stockCode);
    }
    @ApiOperation(value = "获取个股最新分时行情数据", notes = "", httpMethod = "GET")
    @GetMapping("/stock/screen/second/detail")
    public R<StockDetailDomain> getStockDetail(@RequestParam("code") String stockCode){
        return stockService.getStockDetail(stockCode);
    }

    @ApiOperation(value = "个股交易流水行情数据查询", notes = "", httpMethod = "GET")
    @GetMapping("/stock/screen/second")
    public R<List<Map<String,Object>>> getStockSecond(@RequestParam("code") String stockCode){
        return stockService.getStockSecond(stockCode);
    }

}

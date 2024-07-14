package com.itsun.stock.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itsun.stock.mapper.StockBlockRtInfoMapper;
import com.itsun.stock.mapper.StockMarketIndexInfoMapper;
import com.itsun.stock.mapper.StockRtInfoMapper;
import com.itsun.stock.pojo.domain.*;
import com.itsun.stock.pojo.vo.StockInfoConfig;
import com.itsun.stock.service.StockService;
import com.itsun.stock.utils.DateTimeUtil;
import com.itsun.stock.vo.resp.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@ApiModel
@Service
public class StockServiceImpl implements StockService {

    @ApiModelProperty(hidden = true)
    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;
    @ApiModelProperty(hidden = true)
    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;
    @ApiModelProperty(hidden = true)
    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;
    @Autowired
    private Cache<String,Object> caffeineCache;

    @ApiModelProperty(hidden = true)
    @Autowired
    private StockInfoConfig stockInfoConfig;
    @Override
    public R<List<InnerMarketDomain>> getInnerMarketInfo() {
        //从缓存中获取大盘数据
        //本地缓存默认有效1分钟
        R<List<InnerMarketDomain>> innerMarketKey = (R<List<InnerMarketDomain>>) caffeineCache.get("innerMarketKey", key -> {
            //没有获取.从数据库获取
            //获取最新股票时间点
            Date date = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
            //可以先写假数据
            date = DateTime.parse("2022-07-07 14:50:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
            //获取大盘编码
            List<String> inner = stockInfoConfig.getInner();
            //mapper
            List<InnerMarketDomain> res = stockMarketIndexInfoMapper.getMarketInfo(date, inner);
            return R.ok(res);
        });
        return innerMarketKey;

    }

    @Override
    public R<List<StockBlockDomain>> getStockBlock() {
        //获取最新股票时间点
        Date date = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();

        //可以先写假数据
        date = DateTime.parse("2022-01-14 16:57:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //mapper
        List<StockBlockDomain> res =  stockBlockRtInfoMapper.getStockBlock(date,10);

        return R.ok(res);
    }

    @Override
    public R<PageRespVo<StockUpdownDomain>> getPageStockUpdown(Integer page, Integer pageSize) {
        //获取最新股票时间点
        Date date = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();

        //可以先写假数据
        date = DateTime.parse("2021-12-30 09:42:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();

        PageHelper.startPage(page,pageSize);
        List<StockUpdownDomain> res =  stockRtInfoMapper.getStockUpdownByTime(date);

        PageInfo<StockUpdownDomain> pageInfo = new PageInfo<>(res);

        PageRespVo<StockUpdownDomain> stockUpdownDomainPageRespVo = new PageRespVo<>(pageInfo);

        return R.ok(stockUpdownDomainPageRespVo);
    }

    @Override
    public R<UpdownRespVo> getStockUpdownCount() {
        //获取最新股票时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();

        //可以先写假数据
        DateTime parse = DateTime.parse("2022-01-06 14:25:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        //获取该股票的开盘时间
        DateTime openDate = DateTimeUtil.getOpenDate(parse);
        lastDate = parse.toDate();
        Date startDate = openDate.toDate();

        List<Map> upList =  stockRtInfoMapper.getUpdownCount(startDate,lastDate,1);
        List<Map> downList =  stockRtInfoMapper.getUpdownCount(startDate,lastDate,0);
        UpdownRespVo updownRespVo = new UpdownRespVo();
        updownRespVo.setUpList(upList);
        updownRespVo.setDownList(downList);

        return R.ok(updownRespVo);
    }

    @Override
    public void stockExport(HttpServletResponse response, Integer page, Integer pageSize) {
        try {
            R<PageRespVo<StockUpdownDomain>> stockUpdown = this.getPageStockUpdown(page, pageSize);
            //3.查询
            List<StockUpdownDomain> infos=stockUpdown.getData().getRows();
            //如果集合为空，响应错误提示信息
            if (CollectionUtils.isEmpty(infos)) {
                //响应提示信息
//                RequestInfoUtil.setUtf8(response);
                R<Object> r = R.error(ResponseCode.NO_RESPONSE_DATA);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(r));
                return;
            }
            //设置响应excel文件格式类型
            response.setContentType("application/vnd.ms-excel");
            //2.设置响应数据的编码格式
            response.setCharacterEncoding("utf-8");
            //3.设置默认的文件名称
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("stockRt", "UTF-8");
            //设置默认文件名称：兼容一些特殊浏览器
            response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //4.响应excel流
            EasyExcel
                    .write(response.getOutputStream(),StockUpdownDomain.class)
                    .sheet("股票信息")
                    .doWrite(infos);
        } catch (IOException e) {
            e.printStackTrace();
//            log.info("当前导出数据异常，当前页：{},每页大小：{},异常信息：{}",page,pageSize,e.getMessage());
        }
    }

    @Override
    public R<Map<String, List<Map>>> stockTradeVol4InnerMarket() {
        //获取最新股票时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //可以先写假数据
        DateTime parse = DateTime.parse("2022-01-03 14:40:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        //获取该股票的开盘时间
        DateTime openDate = DateTimeUtil.getOpenDate(parse);
        lastDate = parse.toDate();
        Date startDate = openDate.toDate();
        //获取前一天的开票和截至时间
//        DateTime parse = DateTime.parse("2022-01-02 14:40:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));

        Date previousLastDay = DateTimeUtil.getPreviousTradingDay(parse).toDate();
        Date previousStartDay = DateTimeUtil.getPreviousTradingDay(openDate).toDate();

        List<Map> tData = stockMarketIndexInfoMapper.getSumAmtInfo(startDate,lastDate,stockInfoConfig.getInner());
        List<Map> preTData = stockMarketIndexInfoMapper.getSumAmtInfo(previousStartDay,previousLastDay,stockInfoConfig.getInner());


        HashMap<String, List<Map>> stringListHashMap = new HashMap<>();

        stringListHashMap.put("amtList",tData);
        stringListHashMap.put("yesAmtList",preTData);
        return R.ok(stringListHashMap);
    }

    @Override
    public R<Map> stockUpDownScopeCount() {
        //获取最新股票时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //可以先写假数据
        DateTime parse = DateTime.parse("2022-01-06 09:55:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Date date = parse.toDate();

        List<Map> info = stockRtInfoMapper.getStockUpDownScopeCount(date);
        // 变为有序List
        List<String> upDownRange = stockInfoConfig.getUpDownRange();
        List<Map> orderMaps =new ArrayList<>();
        for (String title : upDownRange) {
            Map map=null;
            for (Map m : info) {
                if (m.containsValue(title)) {
                    map=m;
                    break;
                }
            }
            if (map==null) {
                map=new HashMap();
                map.put("count",0);
                map.put("title",title);
            }
            orderMaps.add(map);
        }

//        upDownRange.stream().map(title->{
//            Optional<Map> first = info.stream().filter(map -> map.containsValue(title)).findFirst();
//            if (first.isPresent()){
//                return first.get();
//            }else{
//                HashMap<String,Object> mp=new HashMap();
//                mp.put("count",0);
//                mp.put("title",title);
//                return mp;
//            }
//        }).collect(Collectors.toList());

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("time",parse.toString("yyyy-MM-dd HH:mm:ss"));
        stringObjectHashMap.put("infos",orderMaps);

        return R.ok(stringObjectHashMap);

    }

    @Override
    public R<List<Stock4MinuteDomain>> stockScreenTimeSharing(String code) {
        //1.获取最近最新的交易时间点和对应的开盘日期
        //1.1 获取最近有效时间点
        DateTime lastDate4Stock = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date endTime = lastDate4Stock.toDate();
        //TODO mockdata
        DateTime parse = DateTime.parse("2021-12-30 14:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        endTime= parse.toDate();
        //1.2 获取最近有效时间点对应的开盘日期
        DateTime openDateTime = DateTimeUtil.getOpenDate(parse);
        Date startTime = openDateTime.toDate();
//        startTime=DateTime.parse("2021-12-30 09:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        List<Stock4MinuteDomain> list=stockRtInfoMapper.getStockInfoByCodeAndDate(code,startTime,endTime);
        //判断非空处理
        if (CollectionUtils.isEmpty(list)) {
            list=new ArrayList<>();
        }
        //3.返回响应数据
        return R.ok(list);

    }

    @Override
    public R<List<Stock4EvrDayDomain>> stockCreenDkLine(String stockCode,Integer flag) {
//1.1 获取最近有效时间点
        DateTime lastDate4Stock = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date endTime = lastDate4Stock.toDate();
        //TODO mockdata
        DateTime parse = DateTime.parse("2022-01-07 15:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        endTime= parse.toDate();

        DateTime dateTime = parse.minusMonths(2);
        Date startTime = dateTime.toDate();
//        startTime=DateTime.parse("2022-01-01 09:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();

        List<Stock4EvrDayDomain> data= stockRtInfoMapper.getStockInfo4EvrDay(stockCode,startTime,endTime,flag);
        //3.组装数据，响应
        return R.ok(data);

    }

    @Override
    public R<List<Stock4EvrWeekDomain>> stockCreenWkLine(String stockCode) {
        DateTime lastDate4Stock = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date endTime = lastDate4Stock.toDate();
        //TODO mockdata
        DateTime parse = DateTime.parse("2022-01-07 15:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        endTime= parse.toDate();

        DateTime dateTime = parse.minusMonths(2);
        Date startTime = dateTime.toDate();
        List<Stock4EvrWeekDomain> data= stockRtInfoMapper.getStockInfo4Week(stockCode,startTime,endTime);
        //3.组装数据，响应
        return R.ok(data);
    }

}

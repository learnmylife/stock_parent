package com.itsun.stock.service.impl;

import com.google.common.collect.Lists;
import com.itsun.stock.constant.ParseType;
import com.itsun.stock.face.StockCacheFace;
import com.itsun.stock.mapper.*;
import com.itsun.stock.pojo.entity.StockBlockRtInfo;
import com.itsun.stock.pojo.entity.StockMarketIndexInfo;
import com.itsun.stock.pojo.entity.StockOuterMarketIndexInfo;
import com.itsun.stock.pojo.entity.StockRtInfo;
import com.itsun.stock.pojo.vo.StockInfoConfig;
import com.itsun.stock.service.StockTimerTaskService;
import com.itsun.stock.utils.DateTimeUtil;
import com.itsun.stock.utils.IdWorker;
import com.itsun.stock.utils.ParserStockInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockTimerTaskServiceImpl implements StockTimerTaskService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockInfoConfig stockInfoConfig;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;
    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;
    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;
    @Autowired
    private StockBusinessMapper stockBusinessMapper;
    @Autowired
    private StockCacheFace stockCacheFace;//缓存管理
    @Autowired
    private StockOuterMarketIndexInfoMapper stockOuterMarketIndexInfoMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ParserStockInfoUtil parserStockInfoUtil;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void getInnerMarketInfo() {
        //拼接url
        String marketUrl = stockInfoConfig.getMarketUrl();
        List<String> inner = stockInfoConfig.getInner();
        String url = marketUrl + String.join(",", inner);

        //配置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Referer","https://finance.sina.com.cn/stock/");
        httpHeaders.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0");

        //构建请求对象
        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);
        //发起请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (responseEntity.getStatusCodeValue()!=200) {
            log.error("请求失败: {}",responseEntity.getStatusCodeValue());
            return;
        }
        String body = responseEntity.getBody();
        log.info("采集内容:{}",body);

        //
        String reg="var hq_str_(.+)=\"(.+)\";";
        //编译表达式,获取编译对象
        Pattern pattern = Pattern.compile(reg);
        //匹配字符串
        Matcher matcher = pattern.matcher(body);
        ArrayList<StockMarketIndexInfo> list = new ArrayList<>();
        //判断是否有匹配的数值
        while (matcher.find()) {
            StockMarketIndexInfo stockMarketIndexInfo = new StockMarketIndexInfo();
            //获取大盘的code
            String marketCode = matcher.group(1);
            stockMarketIndexInfo.setMarketCode(marketCode);
            //获取其它信息，字符串以逗号间隔
            String otherInfo = matcher.group(2);
            //以逗号切割字符串，形成数组
            String[] splitArr = otherInfo.split(",");
            //大盘名称
            String marketName = splitArr[0];
            stockMarketIndexInfo.setMarketName(marketName);
            //获取当前大盘的开盘点数
            BigDecimal openPoint = new BigDecimal(splitArr[1]);
            stockMarketIndexInfo.setOpenPoint(openPoint);
            //前收盘点
            BigDecimal preClosePoint = new BigDecimal(splitArr[2]);
            stockMarketIndexInfo.setPreClosePoint(preClosePoint);
            //获取大盘的当前点数
            BigDecimal curPoint = new BigDecimal(splitArr[3]);
            stockMarketIndexInfo.setCurPoint(curPoint);
            //获取大盘最高点
            BigDecimal maxPoint = new BigDecimal(splitArr[4]);
            stockMarketIndexInfo.setMaxPoint(maxPoint);
            //获取大盘的最低点
            BigDecimal minPoint = new BigDecimal(splitArr[5]);
            stockMarketIndexInfo.setMinPoint(minPoint);
            //获取成交量
            Long tradeAmt = Long.valueOf(splitArr[8]);
            stockMarketIndexInfo.setTradeAmount(tradeAmt);
            //获取成交金额
            BigDecimal tradeVol = new BigDecimal(splitArr[9]);
            stockMarketIndexInfo.setTradeVolume(tradeVol);
            //时间
            Date curTime = DateTimeUtil.getDateTimeWithoutSecond(splitArr[30] + " " + splitArr[31]).toDate();
            stockMarketIndexInfo.setCurTime(curTime);
            //雪花算法获取id
            stockMarketIndexInfo.setId(idWorker.nextId());

            list.add(stockMarketIndexInfo);
        }
        log.info("采集的当前大盘数据：{}",list);
        //批量插入
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
         int count = stockMarketIndexInfoMapper.insertBatch(list);
//         int count = 2;

        if (count>0){
            //数据收集完成,通知工程刷新缓存
            //date() 用来判断是否超时
            rabbitTemplate.convertAndSend("stockExchange","inner.market",new Date());

        }



    }

    @Override
    public void getInneBlockInfo() throws JSONException {
        //拼接url
        String blockUrl = stockInfoConfig.getBlockUrl();

        //配置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Referer","https://finance.sina.com.cn/stock/");
        httpHeaders.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0");

        //构建请求对象
        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);
        //发起请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(blockUrl, HttpMethod.GET, entity, String.class);
        if (responseEntity.getStatusCodeValue()!=200) {
            log.error("请求失败: {}",responseEntity.getStatusCodeValue());
            return;
        }
        String body = responseEntity.getBody();

        List<StockBlockRtInfo> stockBlockRtInfos = parserStockInfoUtil.parse4StockBlock(body);
//        System.out.println(body);
//        String reg="\\{([^{}]*)\\}";
//        //编译表达式,获取编译对象
//        Pattern pattern = Pattern.compile(reg);
//        //匹配字符串
//        Matcher matcher = pattern.matcher(body);
//        // 创建一个Map来存储解析后的键值对
//        Map<String, List> map = new HashMap<>();
//        while (matcher.find()) {
//            // 提取匹配到的内容
//            String result = matcher.group(0); // 包含花括号
//
//            // 解析提取到的JSON内容
//            JSONObject jsonObject = new JSONObject(result);
//
//            // 遍历JSON对象并存入Map
//            Iterator<String> keys = jsonObject.keys();
//            while (keys.hasNext()) {
//                String key = keys.next();
//                String value = (String) jsonObject.get(key);
//                List<String> list = new ArrayList<>();
//                String[] parts = value.split(",");
//                for (String part : parts) {
//                    list.add(part.trim());
//                }
//                map.put(key, list);
//            }
//        }
//        // 输出Map中的内容
//        ArrayList<StockBlockRtInfo> stockBlockRtInfos = new ArrayList<>();
//        for (Map.Entry<String, List> entry : map.entrySet()) {
//
//            StockBlockRtInfo stockBlockRtInfo = new StockBlockRtInfo();
//            List value = entry.getValue();
//            System.out.println(value);
//            stockBlockRtInfo.setBlockName((String) value.get(1));
//            stockBlockRtInfo.setTradeAmount(Long.valueOf((String) value.get(6)));
//            stockBlockRtInfo.setTradeVolume(BigDecimal.valueOf(Double.parseDouble((String) value.get(7))));
//            stockBlockRtInfo.setAvgPrice(BigDecimal.valueOf(Double.parseDouble((String) value.get(3))));
//            stockBlockRtInfo.setCompanyNum(Integer.valueOf((String) value.get(2)));
//            stockBlockRtInfo.setLabel((String) value.get(0));
//            stockBlockRtInfo.setUpdownRate(BigDecimal.valueOf(Double.parseDouble((String) value.get(5))));
//
//            Date curTime = DateTime.now().toDate();
//            stockBlockRtInfo.setCurTime(curTime);
//            stockBlockRtInfo.setId(idWorker.nextId());
//            stockBlockRtInfos.add(stockBlockRtInfo);
//        }
        //批量插入
        if (CollectionUtils.isEmpty(stockBlockRtInfos)) {
            return;
        }
        int count = stockBlockRtInfoMapper.insertBatch(stockBlockRtInfos);
        log.info("批量插入了：{}条数据",count);
    }
    @Override
    public void getStockRtIndex() {
        List<String> stockIds = stockCacheFace.getAllStockCodeWithPrefix();
//        //批量获取股票ID集合
//        List<String> stockIds = stockBusinessMapper.getStockIds();
//        //计算出符合sina命名规范的股票id数据
//        stockIds = stockIds.stream().map(id -> {
//            return id.startsWith("6") ? "sh" + id : "sz" + id;
//        }).collect(Collectors.toList());
        //设置公共请求头对象
        //设置请求头数据
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer","https://finance.sina.com.cn/stock/");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        //一次性查询过多，我们将需要查询的数据先进行分片处理，每次最多查询20条股票数据
        Lists.partition(stockIds,5).forEach(list->{
//            // 从线程池中获取线程,执行任务
            threadPoolTaskExecutor.execute(()->{

                String name = Thread.currentThread().getName();
                System.out.println("----------------------"+name);

//                //拼接股票url地址
                String stockUrl=stockInfoConfig.getMarketUrl()+String.join(",",list);
                System.out.println("请求路径: "+stockUrl);
//                //获取响应数据
                String result = restTemplate.postForObject(stockUrl,entity,String.class);
                List<StockRtInfo> infos = parserStockInfoUtil.parser4StockOrMarketInfo(result, ParseType.ASHARE);
                log.info("数据量：{}",infos.size());
                System.out.println(infos);
//                //TODO 批量插入
                int i = stockRtInfoMapper.insertBatch(infos);
                if (i>0){
                    System.out.println("添加成功...");
                }
            });
            System.out.println("...");
        });
    }

    @Override
    public void getOutStockRtIndex() {
        //批量获取股票ID集合
        List<String> stockIds = stockOuterMarketIndexInfoMapper.getOutStockIds();
        //计算出符合sina命名规范的股票id数据
//        stockIds = stockIds.stream().map(id -> {
//            return id.startsWith("6") ? "sh" + id : "sz" + id;
//        }).collect(Collectors.toList());
        //设置公共请求头对象
        //设置请求头数据
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer","https://finance.sina.com.cn/stock/");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        Lists.partition(stockIds,5).forEach(code->{
            String stockUrl=stockInfoConfig.getOutUrl()+String.join(",",code);
            System.out.println("请求路径: "+stockUrl);
//                //获取响应数据
            String result = restTemplate.postForObject(stockUrl,entity,String.class);
            List<StockOuterMarketIndexInfo> list = parserStockInfoUtil.parser4StockOrMarketInfo(result, 2);

            System.out.println(list);
            //TODO 批量插入
            int i = stockOuterMarketIndexInfoMapper.insertBatch(list);
            if (i>0){
                System.out.println("添加成功...");
            }

        });
    }
}

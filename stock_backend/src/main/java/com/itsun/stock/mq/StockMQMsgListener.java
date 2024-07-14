package com.itsun.stock.mq;

import com.github.benmanes.caffeine.cache.Cache;
import com.itsun.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class StockMQMsgListener {
    @Autowired
    private Cache<String,Object> caffeineCache;
    @Autowired
    private StockService stockService;

    //监听消息队列
    @RabbitListener(queues = "innerMarketQueue")
    public void refreshInnerMarketInfo(Date date){
        //统计当前时间和发送消息时间的差值,超过一分钟,警告
        long l = DateTime.now().getMillis() - new DateTime(date).getMillis();

        if (l>60000L){
            log.error("大盘发送时间超时:{},同步超时:{}",new DateTime(date).toString("yyyy-MM-dd HH:mm:ss"),l);
        }
        //清除缓存
        caffeineCache.invalidate("innerMarketKey");
        //调用service,刷新数据
        stockService.getInnerMarketInfo();

    }
}

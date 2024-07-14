package com.itsun.stock.job;

import com.itsun.stock.service.StockTimerTaskService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class SrockJob {
    @Autowired
    private StockTimerTaskService stockTimerTaskService;

    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            System.out.println("beat at:" + i);
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        // default success
    }

    /**
     * 定义定时任务，采集国内大盘数据
     */
    @XxlJob("getStockInnerMarketInfos")
    public void getStockInnerMarketInfos(){
        stockTimerTaskService.getInnerMarketInfo();
    }
    /**
     * 定义定时任务，采集国内个股大盘数据
     */
    @XxlJob("getStockRtInfos")
    public void getStockRtInfos(){
        stockTimerTaskService.getStockRtIndex();
    }
    /**
     * 定义定时任务，采集国内个股大盘数据
     */
    @XxlJob("getInneBlockInfo")
    public void getInneBlockInfo(){
        try {
            stockTimerTaskService.getInneBlockInfo();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

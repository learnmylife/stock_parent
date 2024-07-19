package com.itsun.stock.mapper;

import com.itsun.stock.pojo.domain.*;
import com.itsun.stock.pojo.entity.StockRtInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author sunhb
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2024-07-07 17:59:11
* @Entity com.itsun.stock.pojo.entity.StockRtInfo
*/
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);
    List<StockUpdownDomain> getStockUpdownByTime(@Param("date") Date date);


    List<Map> getUpdownCount(@Param("startDate") Date startDate, @Param("lastDate") Date lastDate, @Param("flag") int i);

    List<Map> getStockUpDownScopeCount(@Param("date") Date date);

    List<Stock4MinuteDomain> getStockInfoByCodeAndDate(@Param("code") String code,
                                                       @Param("startTime") Date startTime,
                                                       @Param("endTime") Date endTime);

    List<Stock4EvrDayDomain> getStockInfo4EvrDay(@Param("stockCode") String stockCode,
                                                 @Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime,
                                                 @Param("flag") Integer flag);

    List<Stock4EvrWeekDomain> getStockInfo4Week(@Param("stockCode") String stockCode,
                                                @Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);
    int insertBatch(@Param("infos") List<StockRtInfo> infos);

    List<Map> getByCodeFuzzy(String searchStrFuzzy);

    StockDetailDomain getStockDetailInfo(@Param("startTime") Date startTime,
                                         @Param("endTime") Date endTime,
                                         @Param("code") String stockCode);

    List<Map<String, Object>> getStockSecondInfo(@Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime,
                                                 @Param("code") String stockCode);
}

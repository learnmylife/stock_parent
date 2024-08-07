package com.itsun.stock.mapper;

import com.itsun.stock.pojo.domain.InnerMarketDomain;
import com.itsun.stock.pojo.entity.StockMarketIndexInfo;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author sunhb
* @description 针对表【stock_market_index_info(国内大盘数据详情表)】的数据库操作Mapper
* @createDate 2024-07-07 17:59:11
* @Entity com.itsun.stock.pojo.entity.StockMarketIndexInfo
*/
public interface StockMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockMarketIndexInfo record);

    int insertSelective(StockMarketIndexInfo record);

    StockMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketIndexInfo record);

    int updateByPrimaryKey(StockMarketIndexInfo record);

    //多参数,写@Param
    List<InnerMarketDomain> getMarketInfo(@Param("curDate") Date curDate,@Param("MCodes") List<String> MCodes);

    List<Map> getSumAmtInfo(@Param("startDay") Date startDay,
                            @Param("lastDay") Date lastDay,
                            @Param("inner") List<String> inner);
    int insertBatch(@Param("infos") List<StockMarketIndexInfo> infos);
}

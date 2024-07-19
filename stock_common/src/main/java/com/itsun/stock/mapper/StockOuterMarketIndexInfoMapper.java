package com.itsun.stock.mapper;

import com.itsun.stock.pojo.domain.externalMarketDomain;
import com.itsun.stock.pojo.entity.StockOuterMarketIndexInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author sunhb
* @description 针对表【stock_outer_market_index_info(外盘详情信息表)】的数据库操作Mapper
* @createDate 2024-07-07 17:59:11
* @Entity com.itsun.stock.pojo.entity.StockOuterMarketIndexInfo
*/
public interface StockOuterMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockOuterMarketIndexInfo record);

    int insertSelective(StockOuterMarketIndexInfo record);

    StockOuterMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockOuterMarketIndexInfo record);

    int updateByPrimaryKey(StockOuterMarketIndexInfo record);

    List<externalMarketDomain> getOuterMarketIndexInfo(@Param("time") Date endTime, @Param("count") int count);

    List<String> getOutStockIds();
    int insertBatch(@Param("infos") List<StockOuterMarketIndexInfo> list);

}

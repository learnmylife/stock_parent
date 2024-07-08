package com.itsun.stock.mapper;

import com.itsun.stock.pojo.entity.StockBusiness;

/**
* @author sunhb
* @description 针对表【stock_business(主营业务表)】的数据库操作Mapper
* @createDate 2024-07-07 17:59:11
* @Entity com.itsun.stock.pojo.entity.StockBusiness
*/
public interface StockBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBusiness record);

    int insertSelective(StockBusiness record);

    StockBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBusiness record);

    int updateByPrimaryKey(StockBusiness record);

}

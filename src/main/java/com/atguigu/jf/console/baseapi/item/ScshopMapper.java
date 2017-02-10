package com.atguigu.jf.console.baseapi.item;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.pojo.Scshop;

public interface ScshopMapper {
    int deleteByPrimaryKey(Long shopId);

    int insert(Scshop record);

    int insertSelective(Scshop record);

    Scshop selectByPrimaryKey(Long shopId);

    int updateByPrimaryKeySelective(Scshop record);

    int updateByPrimaryKey(Scshop record);
    
    /**
     * @方法名: selectShopName  
     * @功能描述: 获取门店名称
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月2日
     */
    List<Scshop> selectShopName(Map<String, Object> map) throws Exception;
    
    
    
}  
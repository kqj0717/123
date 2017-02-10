package com.atguigu.jf.console.baseapi.item;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.common.bean.pojo.CodeValue;
import com.atguigu.jf.console.item.bean.pojo.IcitemType;

public interface IcitemTypeMapper {
    int deleteByPrimaryKey(Integer itemType);

    int insert(IcitemType record);

    int insertSelective(IcitemType record);

    IcitemType selectByPrimaryKey(Integer itemType);

    int updateByPrimaryKeySelective(IcitemType record);

    int updateByPrimaryKey(IcitemType record);
    
    /**
     * 
     * @方法名: selectNameByType  
     * @功能描述: 通过商品类型获取商品名称 
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月1日
     */
    List<IcitemType> selectNameByType(Map<String, Object> map) throws Exception;
}
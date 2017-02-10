package com.atguigu.jf.console.baseapi.order;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.order.bean.pojo.tcGoods;

public interface tcGoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(tcGoods record);

    int insertSelective(tcGoods record);

    tcGoods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(tcGoods record);

    int updateByPrimaryKey(tcGoods record);
    
    
    /**
     * @方法名: selectOrderList  
     * @功能描述: 查询结算订单的列表 
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月3日
     */
    List<tcGoods> selectOrderList(Map<String, Object> map) throws Exception;
    
    /**
     * @方法名: selectOrderByNameOrCode  
     * @功能描述: 通过商品的编码和名称查询结算结果信息  
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月4日
     */
    List<tcGoods> selectOrderByNameOrCode(Map<String, Object> map) throws Exception;
    
}
package com.atguigu.jf.console.order.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.pojo.Scshop;
import com.atguigu.jf.console.order.bean.pojo.tcGoods;

public interface OrderService {
	
	 List<tcGoods> selectOrderList(Map<String, Object> map) throws Exception;
	 
	 List<Scshop> selectShopName(Map<String, Object> map) throws Exception;
	 
	 List<tcGoods> selectOrderByNameOrCode(Map<String, Object> map) throws Exception;
}

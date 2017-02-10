package com.atguigu.jf.console.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.item.ScshopMapper;
import com.atguigu.jf.console.baseapi.order.tcGoodsMapper;
import com.atguigu.jf.console.item.bean.pojo.Scshop;
import com.atguigu.jf.console.order.bean.pojo.tcGoods;
import com.atguigu.jf.console.order.service.OrderService;

@Service
public class OderServiceImpl implements OrderService{

	@Autowired
	private tcGoodsMapper tcGoodsMapper;
	
	@Autowired
	private ScshopMapper scshopMapper;

	@Override
	public List<tcGoods> selectOrderList(Map<String, Object> map) throws Exception {
		// 获取结算订单列表
		return tcGoodsMapper.selectOrderList(map);
		
		
	}

	@Override
	public List<Scshop> selectShopName(Map<String, Object> map) throws Exception {
		// 获取门店名称
		return scshopMapper.selectShopName(map);
	}

	@Override
	public List<tcGoods> selectOrderByNameOrCode(Map<String, Object> map) throws Exception {
		// 通过查询条件获取信息
		return tcGoodsMapper.selectOrderByNameOrCode(map);
	}
	
	  
	
}

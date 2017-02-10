package com.atguigu.jf.console.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.item.bean.pojo.Scshop;
import com.atguigu.jf.console.order.bean.pojo.tcGoods;
import com.atguigu.jf.console.order.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("getShopInfo")
	@ResponseBody
	public List<tcGoods> getShopInfo(Map<String, Object> map) throws Exception{
		
	    List<tcGoods> list = orderService.selectOrderByNameOrCode(map);
		//System.out.println("zoula****");
		return list;
		
	}
	//获取门店的名称
	 @RequestMapping("getShopName")
	 @ResponseBody
	 public List<Scshop> getShopName(Map<String, Object> map) throws Exception{
		 
		List<Scshop> shopName = orderService.selectShopName(map);
		 
		 return shopName;
	 }
	 
	 //获取类目列表
	 @RequestMapping("orderList")
	 @ResponseBody
	 public String orderList(tcGoods tcGoods,Integer page,Integer limit) throws Exception{
		 
			 //构造参数，查询用户的列表
			 Map<String, Object> map = new HashMap<>();
			
			 map.put("goodsId", tcGoods.getGoodsId());
			 map.put("orderId", tcGoods.getOrderId());
			 map.put("mchtName",tcGoods.getMchtName());
			 //开始分页
			 PageHelper.startPage(page, limit);
			 
			 //查询用户的总记录数
		     List<tcGoods> orderList = orderService.selectOrderList(map);
			 
			 //使用pageHelper进行包装
			 PageInfo<tcGoods> pageInfo = new PageInfo<>(orderList);
			
			 //Json日期格式化
			 JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
			 //将包装好的对象直接转化成Json的格式
			 String str = JSON.toJSONString(pageInfo,SerializerFeature.WriteDateUseDateFormat,
					        SerializerFeature.WriteMapNullValue,
					        SerializerFeature.WriteNullNumberAsZero,
					        SerializerFeature.WriteNullStringAsEmpty);
			// System.out.println(str); 
		
		 return str;
	 }
	
}

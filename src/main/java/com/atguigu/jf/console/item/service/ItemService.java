package com.atguigu.jf.console.item.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.common.bean.pojo.CodeValue;
import com.atguigu.jf.console.item.bean.bo.EvaluationBean;
import com.atguigu.jf.console.item.bean.pojo.IcMallcat;
import com.atguigu.jf.console.item.bean.pojo.IcitemPicture;
import com.atguigu.jf.console.item.bean.pojo.IcitemType;
import com.atguigu.jf.console.item.bean.pojo.Item;
import com.atguigu.jf.console.item.bean.pojo.Scshop;

public interface ItemService {

	 List<Item> selectItemList(Map<String, Object> map) throws Exception;
	 
	 List<IcitemType> selectNameByType(Map<String, Object> map) throws Exception;
	 
	 List<IcMallcat> selectMallcatNameBymallCode(Map<String, Object> map) throws Exception;
	 
	 int insertSelective(Item record);
	 
	 int updateByPrimaryKeySelective(Item record);
	 
	 int deleteByPrimaryKey(Long itemId);
	 
	 List<Item> selectItemStateById(Map<String, Object> map) throws Exception;
	 
	 List<IcitemPicture> selectItempicFlag(Map<String, Object> map) throws Exception;
	 
	 List<Scshop> selectShopName(Map<String, Object> map) throws Exception;
	  
	 Item selectByPrimaryKey(Long itemId);
	  
	 List<CodeValue> selectCodeType(Map<String, Object> map) throws Exception;
	   
	 List<EvaluationBean> selectEvaluationList(Map<String, Object> map) throws Exception;
	 
	 List<String> selectEvaluationPic(Map<String, Object> map)throws Exception;
	 
	 EvaluationBean selectEvaluation(Long evaluationId);
	 
	 int updateEvaluationStatus(EvaluationBean evaluationBean);
}

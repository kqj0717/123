package com.atguigu.jf.console.system.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.pojo.IcMallcat;

public interface IcMallCatService {

	
	 List<IcMallcat> selectMallcatNameBymallCode(Map<String, Object> map) throws Exception;
	 
	 List<IcMallcat> selectIcMallCatList(Map<String, Object> map) throws Exception;
	 
	 int insertSelective(IcMallcat record);
	 
	 int updateByPrimaryKeySelective(IcMallcat record);
	 
	 IcMallcat selectByPrimaryKey(Long mallCatId);
	 
	 
}

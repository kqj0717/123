package com.atguigu.jf.console.common.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.common.bean.pojo.CodeValue;
import com.atguigu.jf.console.common.bean.pojo.TestSql;

public interface CodeValueService {
	
	List<CodeValue> selectCodeValueByType(Map<String, Object> map) throws Exception;
	
	List<TestSql>  selectOrderList(Map<String, Object> map) throws Exception;
	
}

package com.atguigu.jf.console.baseapi.common;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.common.bean.pojo.TestSql;

public interface TestSqlMapper {

	//测试查询积分导入流水
	List<TestSql> selectOrderList(Map<String, Object> map) throws Exception;
	
	

}

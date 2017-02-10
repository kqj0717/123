package com.atguigu.jf.console.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.common.TestSqlMapper;
import com.atguigu.jf.console.common.bean.pojo.TestSql;
import com.atguigu.jf.console.common.service.CreateExcelService;
import com.atguigu.jf.console.common.service.TestSqlService;
import com.sun.org.apache.xerces.internal.dom.DeferredDOMImplementationImpl;


public class TestSqlServiceImpl implements TestSqlService{
      
	@Autowired
	  private CreateExcelService createExcelService;

	/*@Override
	public List<TestSql> selectOrderList(Map<String, Object> map) throws Exception {
		// 查询积分导入流水
		return testSqlMapper.selectOrderList(map);
	}*/
	
    public void doIt(){
    	System.out.println("执行了创建excel方法:" + new Date());
		try {
			String fileName = createExcelService.createExcel();
			System.out.println("创建excel的路径是:" + fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
}

package com.atguigu.jf.console.test;

import static org.junit.Assert.*;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.jf.console.common.service.CreateExcelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class PoITest {
   
	@Autowired
	private CreateExcelService createExcelService;
	@Test
	public void test() {
		
		try {
			createExcelService.createExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}


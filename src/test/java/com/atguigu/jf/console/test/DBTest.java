package com.atguigu.jf.console.test;

import java.sql.SQLException;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.pool.DruidDataSource;

//数据库连接池的测试

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class DBTest {
	
	@Autowired
	private DruidDataSource dataSource;
	
	@Test
	public void testDB(){
		
		try {
			System.out.println(dataSource.getConnection());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}

/*
 * SELECT sf.func_id FROM sys_func sf WHERE sf.func_id IN (
   
   SELECT srf.func_id FROM sys_op_role sor,sys_role_func srf 
   WHERE sor.`ROLE_ID` = srf.`ROLE_ID` 
   AND sor.`DATA_STATE`=1
   AND srf.`DATA_STATE`=1
   AND sor.`OP_ID`=1   
   
)

//根据登录的用户名查询用户的所有菜单
SELECT * FROM sys_func sf WHERE sf.func_id IN (
   
   SELECT srf.func_id FROM sys_op_role sor,sys_role_func srf,sys_op so
   WHERE sor.`ROLE_ID` = srf.`ROLE_ID` 
   AND sor.`DATA_STATE`=1
   AND srf.`DATA_STATE`=1
   AND sor.`OP_ID`=so.`OP_ID`
   AND so.`LOGIN_CODE`='lisi'   
   
)
 * 
 * 
 * 
 * 
 */


















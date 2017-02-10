package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface UserService {

	
	List<SysOp> selectsysOpList(Map<String, Object> map) throws Exception;
	
	Integer selectsysOpListCount(Map<String, Object> map) throws Exception;
	
	int insertSelective(SysOp record);
	
	int updateByPrimaryKeySelective(SysOp record);
	
	SysOp selectByPrimaryKey(Long opId);//回显
	
	 int deleteByPrimaryKey(Long opId);//删除
	 
	 List<SysOp> selectsysOpListByPageHelper(Map<String, Object> map) throws Exception;
	 
	 List<SysFuncBean>  selectsysFuncListByRoleId(Map<String, Object> map); //查询功能菜单
}

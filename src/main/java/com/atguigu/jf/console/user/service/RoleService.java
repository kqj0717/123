package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.bo.SysRoleFunc;
import com.atguigu.jf.console.user.bean.pojo.SysFunc;
import com.atguigu.jf.console.user.bean.pojo.SysRole;

public interface RoleService {

	
	SysRole selectByPrimaryKey(Long roleId);
	
	/**
	 * @方法名: selectSysRoleList  
	 * @功能描述:查询所有的角色 
	 * @param map
	 * @return
	 * @throws Exception
	 * @作者 kqj 
	 * @日期 2016年11月30日
	 */
	List<SysRole> selectSysRoleList(Map<String, Object> map) throws Exception;
	
	/**
	 * @方法名: selectFuncList  
	 * @功能描述: TODO(这里用一句话描述这个方法的作用)  
	 * @param 查询所有的功能
	 * @return
	 * @作者 kqj 
	 * @日期 2016年11月30日
	 */
	 List<SysFunc> selectFuncList() throws Exception;
     
	 /**
	  * @方法名: selectFuncListByRoleId  
	  * @功能描述: 根据roleId查询所有的功能  
	  * @param map
	  * @return
	  * @作者 kqj 
	  * @日期 2016年11月30日
	  */
	 List<SysFunc> selectFuncListByRoleId(Map<String, Object> map) throws Exception;
	
     void updateDataStateByRoleId(Map<String, Object> map) throws Exception;
     void insertBatchSysRoleFunc(List<SysRoleFunc> list) throws Exception;
}

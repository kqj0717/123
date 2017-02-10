package com.atguigu.jf.console.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.user.SysFuncMapper;
import com.atguigu.jf.console.baseapi.user.SysOpMapper;
import com.atguigu.jf.console.baseapi.user.SysRoleMapper;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.bo.SysRoleFunc;
import com.atguigu.jf.console.user.bean.pojo.SysFunc;
import com.atguigu.jf.console.user.bean.pojo.SysRole;
import com.atguigu.jf.console.user.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private SysRoleMapper sysOpMapper;
	
	@Autowired
    private SysFuncMapper sysFuncMapper;
	
	@Override
	public SysRole selectByPrimaryKey(Long roleId) {
		
		return sysOpMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public List<SysRole> selectSysRoleList(Map<String, Object> map) throws Exception {
		//查询角色列表
		return sysOpMapper.selectSysRoleList(map);
	}

	
   //删除关系
	@Override
	public void updateDataStateByRoleId(Map<String, Object> map) throws Exception {
		
		sysOpMapper.updateDataStateByRoleId(map);
	}
   //批量新增
	@Override
	public void insertBatchSysRoleFunc(List<SysRoleFunc> list) throws Exception {
	
	    sysOpMapper.insertBatchSysRoleFunc(list);	
	}
   //获取所有
	@Override
	public List<SysFunc> selectFuncList() throws Exception {
		
		return sysFuncMapper.selectFuncList();
	}
   //根据roleId获取
	@Override
	public List<SysFunc> selectFuncListByRoleId(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sysFuncMapper.selectFuncListByRoleId(map);
	}

	
	

	

	 
}

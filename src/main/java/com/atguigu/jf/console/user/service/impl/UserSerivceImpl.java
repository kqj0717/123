package com.atguigu.jf.console.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.user.SysFuncMapper;
import com.atguigu.jf.console.baseapi.user.SysOpMapper;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.UserService;

@Service
public class UserSerivceImpl implements UserService{

	@Autowired
	private SysOpMapper sysOpMapper;
	
	@Autowired
	private SysFuncMapper sysFuncMapper;
	
	@Override
	public List<SysOp> selectsysOpList(Map<String, Object> map) throws Exception {
		
		return sysOpMapper.selectsysOpList(map);
	}

	@Override
	public Integer selectsysOpListCount(Map<String, Object> map) throws Exception {
		
		return sysOpMapper.selectsysOpListCount(map);
	}

	@Override
	public int insertSelective(SysOp record) {
		//用户新增
		return sysOpMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysOp record) {
		// 用户修改
		return sysOpMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysOp selectByPrimaryKey(Long opId) {
		// 查询用户实体进行回显
		return sysOpMapper.selectByPrimaryKey(opId);
	}

	@Override
	public int deleteByPrimaryKey(Long opId) {
		//删除用户
		return sysOpMapper.deleteByPrimaryKey(opId);
	}

	@Override
	public List<SysOp> selectsysOpListByPageHelper(Map<String, Object> map) throws Exception {
		// 基于插件的分页方法
		return sysOpMapper.selectsysOpListByPageHelper(map);
	}

	@Override
	public List<SysFuncBean> selectsysFuncListByRoleId(Map<String, Object> map) {
		
		return null;
	}

}

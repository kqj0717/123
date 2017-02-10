package com.atguigu.jf.console.user.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.user.SysFuncMapper;
import com.atguigu.jf.console.baseapi.user.SysOpMapper;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SysOpMapper sysOpMapper;
	
	@Autowired
	private SysFuncMapper sysFuncMapper;
	
	@Override
	public SysOp selectSysOpByNameAndPwd(SysOp record) throws Exception {
		
		return sysOpMapper.selectSysOpByNameAndPwd(record);
	}

	@Override
	public List<SysFuncBean> selectSysFuncByOpId(Map<String, Object> map) {
		
		return sysFuncMapper.selectSysFuncByOpId(map);
	}

	@Override
	public List<SysFuncBean> selectFuncIdByRoleId(Map<String, Object> map) {
		
		return null;
	}

	
}

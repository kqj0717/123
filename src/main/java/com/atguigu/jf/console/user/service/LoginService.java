package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface LoginService {

	public SysOp selectSysOpByNameAndPwd(SysOp record) throws Exception;
	
	List<SysFuncBean>  selectSysFuncByOpId (Map<String, Object> map);
	
	List<SysFuncBean> selectFuncIdByRoleId(Map<String, Object> map);
}

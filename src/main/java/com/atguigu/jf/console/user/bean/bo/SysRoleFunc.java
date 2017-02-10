package com.atguigu.jf.console.user.bean.bo;

import java.util.Date;


public class SysRoleFunc {

	
	private Long roleId;
	private Long funcId;
	private Date modifyDate = new Date();
	private Long creator;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getFuncId() {
		return funcId;
	}
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	
	
}

package com.atguigu.jf.console.user.bean.bo;

import java.util.Date;
import java.util.List;

public class SysFuncBean {
	
	//加入属性children
	
	private List<SysFuncBean> Children;
	

	public List<SysFuncBean> getChildren() {
		return Children;
	}

	public void setChildren(List<SysFuncBean> children) {
		Children = children;
	}

	private Long funcId;

    private Long supFuncId;

    private Short funcLevel;

    private String funcCode;

    private String funcName;

    private Integer funcOrder;

    private String funcUrl;
    

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public Long getSupFuncId() {
		return supFuncId;
	}

	public void setSupFuncId(Long supFuncId) {
		this.supFuncId = supFuncId;
	}

	public Short getFuncLevel() {
		return funcLevel;
	}

	public void setFuncLevel(Short funcLevel) {
		this.funcLevel = funcLevel;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public Integer getFuncOrder() {
		return funcOrder;
	}

	public void setFuncOrder(Integer funcOrder) {
		this.funcOrder = funcOrder;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

    
   


   


    
   
   
    
}
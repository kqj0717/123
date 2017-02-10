package com.atguigu.jf.console.common.bean.bo;

/**
 * 
 * @包名 com.atguigu.jf.console.common.bean.bo
 * @类名 ResultBean.java
 * @作者 kqj  
 * @创建日期 2016年11月28日
 * @描述 添加修改页面的回显页面的信息模型
 * @版本 V 1.0
 */
public class ResultBean {

	public static final String RESULT_SUCCESS = "success";
	public static final String RESULT_FAILED = "failed";
	
	private String result;  //返回的状态; 成功/失败
	private String msg; //返回的信息
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}

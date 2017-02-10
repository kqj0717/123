package com.atguigu.jf.console.common.bean.bo;

import java.util.List;
/**
 * 
 * @包名 com.atguigu.jf.console.common.bean.bo
 * @类名 PageModel.java
 * @作者 kqj  
 * @创建日期 2016年11月26日
 * @描述 通用的分页对象
 * @版本 V 1.0
 */
public class PageModel<T> {

	  private int pageNo;
	  private int pageSize;
	  private int total;
	  private List<T> rows;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	  
	  
}

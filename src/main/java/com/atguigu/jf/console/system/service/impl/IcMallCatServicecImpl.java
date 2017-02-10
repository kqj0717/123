package com.atguigu.jf.console.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.item.IcMallcatMapper;
import com.atguigu.jf.console.item.bean.pojo.IcMallcat;
import com.atguigu.jf.console.system.service.IcMallCatService;

@Service
public class IcMallCatServicecImpl implements IcMallCatService{

	@Autowired
	private  IcMallcatMapper icMallcatMapper;

	@Override
	public List<IcMallcat> selectMallcatNameBymallCode(Map<String, Object> map) throws Exception {
		//获取类目列表名称
		return icMallcatMapper.selectMallcatNameBymallCode(map);
	}

	@Override
	public List<IcMallcat> selectIcMallCatList(Map<String, Object> map) throws Exception {
		// 获取类目类别
		return icMallcatMapper.selectIcMallCatList(map);
	}

	@Override
	public int insertSelective(IcMallcat record) {
		// 新增类目对象
		return icMallcatMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(IcMallcat record) {
		//修改类目
		return icMallcatMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public IcMallcat selectByPrimaryKey(Long mallCatId) {
		// 获取类目的对象
		return icMallcatMapper.selectByPrimaryKey(mallCatId);
	}

	
}

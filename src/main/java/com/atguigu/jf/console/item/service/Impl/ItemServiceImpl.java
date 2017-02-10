package com.atguigu.jf.console.item.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.common.CodeValueMapper;
import com.atguigu.jf.console.baseapi.item.EvaluationMapper;
import com.atguigu.jf.console.baseapi.item.IcMallcatMapper;
import com.atguigu.jf.console.baseapi.item.IcitemPictureMapper;
import com.atguigu.jf.console.baseapi.item.IcitemTypeMapper;
import com.atguigu.jf.console.baseapi.item.ItemMapper;
import com.atguigu.jf.console.baseapi.item.ScshopMapper;
import com.atguigu.jf.console.common.bean.pojo.CodeValue;
import com.atguigu.jf.console.item.bean.bo.EvaluationBean;
import com.atguigu.jf.console.item.bean.pojo.IcMallcat;
import com.atguigu.jf.console.item.bean.pojo.IcitemPicture;
import com.atguigu.jf.console.item.bean.pojo.IcitemType;
import com.atguigu.jf.console.item.bean.pojo.Item;
import com.atguigu.jf.console.item.bean.pojo.Scshop;
import com.atguigu.jf.console.item.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
    
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private IcitemTypeMapper itemTypeMapper;

	@Autowired
	private IcMallcatMapper icMallcatMapper;
	
	@Autowired
	private IcitemPictureMapper icitemPictureMapper;
	
	@Autowired
	private ScshopMapper scshopMapper;
	
	@Autowired
	private CodeValueMapper codeValueMapper;
	
	@Autowired
	private EvaluationMapper  evaluationMapper;
	
	
	@Override
	public List<Item> selectItemList(Map<String, Object> map) throws Exception {
		// 获取商品列表
		return itemMapper.selectItemList(map);
	}

	@Override
	public List<IcitemType> selectNameByType(Map<String, Object> map) throws Exception {
		// 根据商品类型获取商品类型名称
		return itemTypeMapper.selectNameByType(map);
	}

	@Override
	public List<IcMallcat> selectMallcatNameBymallCode(Map<String, Object> map) throws Exception {
		// 根据商品类目编码获取商品类目名称
		return icMallcatMapper.selectMallcatNameBymallCode(map);
	}

	@Override
	public int insertSelective(Item record) {
		// 新增商品
		return itemMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Item record) {
		// 修改商品
		return itemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Long itemId) {
		// 删除商品
		return itemMapper.deleteByPrimaryKey(itemId);
	}

	@Override
	public List<Item> selectItemStateById(Map<String, Object> map) throws Exception {
		// 根据商品的id查询商品的状态
		return itemMapper.selectItemStateById(map);
	}

	@Override
	public List<IcitemPicture> selectItempicFlag(Map<String, Object> map) throws Exception {
		// 获取图片
		return icitemPictureMapper.selectItempicFlag(map);
	}

	@Override
	public List<Scshop> selectShopName(Map<String, Object> map) throws Exception {
		// 获取门店名称
		return scshopMapper.selectShopName(map);
	}

	@Override
	public Item selectByPrimaryKey(Long itemId) {
		// 获取商品实体回显
		return itemMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public List<CodeValue> selectCodeType(Map<String, Object> map) throws Exception {
		// 获取审批类型
		return codeValueMapper.selectCodeType(map);
	}

	@Override
	public List<EvaluationBean> selectEvaluationList(Map<String, Object> map) throws Exception {
		//获取评论管理的列表
		return evaluationMapper.selectEvaluationList(map);
	}

	@Override
	public List<String> selectEvaluationPic(Map<String, Object> map) throws Exception {
		// 获取评论管理列表的图片
		return evaluationMapper.selectEvaluationPic(map);
	}

	@Override
	public EvaluationBean selectEvaluation(Long evaluationId) {
		// 获取评论的单个对象，传到前台获取详情的回显
		return evaluationMapper.selectEvaluation(evaluationId);
	}

	@Override
	public int updateEvaluationStatus(EvaluationBean evaluationBean) {
		//更新评论管理模块的评论状态到屏蔽
		return evaluationMapper.updateEvaluationStatus(evaluationBean);
	}
}

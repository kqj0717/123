package com.atguigu.jf.console.item.controller;

import static org.hamcrest.CoreMatchers.hasItems;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MatrixVariableMapMethodArgumentResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.common.bean.pojo.CodeValue;
import com.atguigu.jf.console.common.service.CodeValueService;
import com.atguigu.jf.console.item.bean.bo.EvaluationBean;
import com.atguigu.jf.console.item.bean.pojo.IcMallcat;
import com.atguigu.jf.console.item.bean.pojo.IcitemPicture;
import com.atguigu.jf.console.item.bean.pojo.IcitemType;
import com.atguigu.jf.console.item.bean.pojo.Item;
import com.atguigu.jf.console.item.bean.pojo.Scshop;
import com.atguigu.jf.console.item.service.ItemService;
import com.atguigu.jf.console.system.service.IcMallCatService;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.crypto.provider.RSACipher;
import com.sun.jersey.core.util.StringIgnoreCaseKeyComparator;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap.Spliterator;
import net.sf.ehcache.util.concurrent.LongAdder;
import sun.security.timestamp.TSRequest;

@Controller
@RequestMapping("item")
public class ItemController {

		@Autowired
		private ItemService itemService;
	
		@Autowired
		private CodeValueService codeValueService;
		
		//获取商品状态的下拉框的信息,多个参数，多个类型，并且将其type和value组合成新的value以便解析时区分
		@RequestMapping("getCodeValue")
		@ResponseBody
		public List<CodeValue> getCodeValue(String codeType) throws Exception{

	      //创建一个空的集合
		  Map<String, Object> map = new HashMap<>();
		 
		  //使用&将类型进行切割
		  String[] split = codeType.split("&");
		  
		  //创建一个空存放切割后的类型的集合
		  List<String> codeTypeList = new ArrayList<>();
		  //遍历类型集合，将遍历后的结果放到切割后存放的list集合里面
		  for (int i = 0; i < split.length; i++) {
			
			  codeTypeList.add(split[i]);
		   }
		  
		  //将获取到的新的类型放到map中,再组合成新的map
		  map.put("codeTypeList", codeTypeList);
		  
		    List<CodeValue> list = itemService.selectCodeType(map);
		    for (CodeValue codeValue : list) {
				
		    	Integer codeType2 = codeValue.getCodeType();
		    	
		    	String codeValue2 = codeValue.getCodeValue();
		    	
		    	String codeValueReal = codeType2 + "&"+codeValue2;
		    	
		    	codeValue.setCodeValue(codeValueReal);
			}
		    
		    System.out.println("lailai");
		    return list;
		}
	 
	 
	 //获取门店名称（结算管理模块的门店名称下拉框的获取）
	 @RequestMapping("getShopName")
	 @ResponseBody
	 public List<Scshop> getShopName(Map<String, Object> map) throws Exception{
		 
		 List<Scshop> nameList = itemService.selectShopName(map);
		 
		 return nameList;
	 }
	 
	//删除商品(可以正常删除)
	 @RequestMapping("deleteItem")
		@ResponseBody
		public ResultBean deleteItem(String itemId) throws Exception {
			Item item = new Item();
			item.setItemId(Long.parseLong(itemId));
			item.setDataState(Short.parseShort("0"));
			int i = itemService.updateByPrimaryKeySelective(item);
			ResultBean rlt = new ResultBean();
			if(i <= 0){
				// 错误的语句
				rlt.setResult(ResultBean.RESULT_FAILED);
				rlt.setMsg("删除商品时错误！");
			}else{
				// 成功
				rlt.setResult(ResultBean.RESULT_SUCCESS);
				rlt.setMsg("删除商品成功！");
			}
			return rlt;
		}
	 
    //修改商品
	 @RequestMapping("modifyItem")
	 @ResponseBody
	 public ResultBean modifyItem(Item item){
		 
		 System.out.println("***" + item.getItemId());
		 //调用修改的方法
		 int count = itemService.updateByPrimaryKeySelective(item);
 
		 //创建返回信息的模型
		 ResultBean rs = new ResultBean();
		 
		 if(count <=0){
			 
			 rs.setResult(ResultBean.RESULT_FAILED);
			 rs.setMsg("修改商品信息失败！");
			 
		 }else{
			 
			 rs.setResult(ResultBean.RESULT_SUCCESS);
			 
			 rs.setMsg("修改商品信息成功！");
		 }
		 
		 
		 return rs;
	 }
	 
	//商品图片上传
    @RequestMapping(value="uploadFile",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean uploadFile(MultipartFile uploadFile,
			                     HttpSession session){
		
		    ResultBean rlt = new  ResultBean();
		
			//通过session对象获取servletContext
			ServletContext servletContext = session.getServletContext();
			
			//声明虚拟路径
			String virtualPath = "/itemPicLogos";
			
			// 4.根据虚拟路径生成真实路径
			String realPath = servletContext.getRealPath(virtualPath);
			
		    //获取原始文件名
			String filename = uploadFile.getOriginalFilename();
			
			//创建文件
			File file = new File(realPath + "/"+ filename);
		
			try{
				
				uploadFile.transferTo(file);
				rlt.setResult(ResultBean.RESULT_SUCCESS);
				rlt.setMsg(virtualPath+"/"+filename);
			}catch(Exception e){
				rlt.setResult(ResultBean.RESULT_FAILED);
				rlt.setMsg("上传图片发生异常");
			}
		
		return rlt;
	}
	 
	 
	 //新增商品
	@RequestMapping("addItem")
	@ResponseBody
	public ResultBean addItem(Item item){
		
		int count = itemService.insertSelective(item);
		
		//System.out.println(count+"***");
		
		ResultBean re = new ResultBean();
		if(count<=0){
			
			re.setResult(ResultBean.RESULT_FAILED);

			re.setMsg("新增商品失败！");
		}else{
			
			re.setResult(ResultBean.RESULT_SUCCESS);
			
			re.setMsg("新增商品成功！");
		}
		
		return re;
	}
	 
	 //去新增商品页面 
	 @RequestMapping("addItemPage")
	 public String addItemPage(String type,String itemId,Map<String, Object> map){
		 
		 //后面需要判断类型是修改类型需要查出对象结果返回给前台显示
		
		 if("modify".equals(type)){
			 
			  Item item = itemService.selectByPrimaryKey(Long.parseLong(itemId));
			   map.put("item", item);
			   map.put("itemId", itemId);
			 }
		 map.put("type", type);
		 
		 
		return "item/addItem"; 
	 }
	 
	 //根据商品的id获取商品状态
	 @RequestMapping("getItemStateById")
	 @ResponseBody
	 public List<Item> getItemStateById(Long itemId) throws Exception{
		 
		 Map<String, Object> map = new HashMap<>();
		 
		 map.put("itemId", itemId);
		 
		List<Item> list = itemService.selectItemStateById(map);
		 
		 return list;
	 }
	 
	 //根据商品类目编码获取商品类目名称
	 @RequestMapping("getMallcatNameByCode")
	 @ResponseBody
	 public List<IcMallcat> getMallcatNameByCode(String mallCatCode) throws Exception{
		 
		 Map<String,Object> map = new HashMap<>();
		 
		 map.put("mallCatCode", mallCatCode);
		 //map.put("mallCatName", mallCatName);
		 List<IcMallcat> list = itemService.selectMallcatNameBymallCode(map);
		 
		 /*for (IcMallcat icMallcat : list) {
			System.out.println(icMallcat+"****");
		}*/
		 return list;
	 }
	 
	 
	 //根据商品的类型获取商品类型的名称
	 @RequestMapping("getItemNameByType")
	 @ResponseBody
	 public List<IcitemType> getItemNameByType(Integer itemType) throws Exception{
		 
		 Map<String, Object> map = new HashMap<>();
		 
		 map.put("itemType", itemType);
		 
		 
		 List<IcitemType> list = itemService.selectNameByType(map);
		 
		 return list;
	 }
	 
	 
	 //显示商品列表
	 @RequestMapping("selectItemList")
	 @ResponseBody
	 public String selectItemList(Item item,Integer page,Integer limit) throws Exception{
		 
			 //构造参数，查询用户的列表
			 Map<String, Object> map = new HashMap<>();
			
			 map.put("itemName",item.getItemName());
			 map.put("itemType",item.getItemType());
			 map.put("mallCatId", item.getMallCatId());
			 map.put("itemApprState", item.getItemApprState());
			 map.put("itemUdState", item.getItemUdState());
			 
			 //开始分页
			 PageHelper.startPage(page, limit);
			 
			 //查询用户的总记录数
		      List<Item> itemList = itemService.selectItemList(map);
			 
			 //使用pageHelper进行包装
			 PageInfo<Item> pageInfo = new PageInfo<>(itemList);
			
			 //Json日期格式化
			 JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
			 //将包装好的对象直接转化成Json的格式
			 String str = JSON.toJSONString(pageInfo,SerializerFeature.WriteDateUseDateFormat,
					        SerializerFeature.WriteMapNullValue,
					        SerializerFeature.WriteNullNumberAsZero,
					        SerializerFeature.WriteNullStringAsEmpty);
			 //System.out.println(str); 
		
		 return str;
	 }
	 
	 //*************一下的是评价管理模块的方法*******************
	 
	 //评论管理模块的评论状态更新到屏蔽
	@RequestMapping("updateEvaluationStatus")
	public String updateEvaluationStatus(EvaluationBean evaluationBean){
		
		int count = itemService.updateEvaluationStatus(evaluationBean);
		System.out.println("更新啦---"+count);
		return "item/itemEvaluationMgnt";
	}
	 
	 //评论对象回显评论详情信息
	 @RequestMapping("getEvaluation")
	 public String selectEvaluation(String evaluationId,Map<String, Object> map){
		 
		 //根据id获取对象
		 EvaluationBean evaluationBean = itemService.selectEvaluation(Long.parseLong(evaluationId));
		 
		  map.put("evaluationBean", evaluationBean);
		 
		 return "item/itemEvaluationDetail";
	 }
	 
	 //获取评论管理的列表
	 @RequestMapping("getEvaluationList")
	 @ResponseBody
	 public String getEvaluationList(EvaluationBean evaluationBean,Integer page,Integer limit) throws Exception{
		 
		 Map<String, Object> map = new HashMap<>();
		 
		 map.put("evaluationId", evaluationBean.getEvaluationId());
		 map.put("userNickname", evaluationBean.getUserNickname());
		 map.put("evaluationStatus", evaluationBean.getEvaluationStatus());
		 
		 
		 //EvaluationBean evaluationBean2 = evaluationList.get(0);
		 
		List<String> picList = itemService.selectEvaluationPic(map);
		 
		 evaluationBean.setEvaluationPicUrls(picList);
		 
		 //开始分页
		 PageHelper.startPage(page,limit);
		 
		 //获取总条数
		 List<EvaluationBean> evaluationList = itemService.selectEvaluationList(map);
		 
		 //使用Pageinfo进行包装
		 PageInfo<EvaluationBean> pageInfo = new PageInfo<>(evaluationList);
		 
		 //Json日期格式化
		 JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		 //将包装好的对象直接转化成Json的格式
		 String str = JSON.toJSONString(pageInfo,SerializerFeature.WriteDateUseDateFormat,
				        SerializerFeature.WriteMapNullValue,
				        SerializerFeature.WriteNullNumberAsZero,
				        SerializerFeature.WriteNullStringAsEmpty);
		 //System.out.println(str); 
	
	    return str;
	 }
	 
	 
	
	 
}






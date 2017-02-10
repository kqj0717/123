package com.atguigu.jf.console.item.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.item.bean.pojo.Item;
import com.atguigu.jf.console.item.service.ItemService;

@Controller
@RequestMapping("itemAdmin")
public class ItemControllerAdmin {

	  @Autowired
	  private ItemService itemService;
	  
	//删除商品
		 @RequestMapping("deleteItemAdmin")
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
		 @RequestMapping("modifyItemAdmin")
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
					rlt.setMsg(filename);
				}catch(Exception e){
					rlt.setResult(ResultBean.RESULT_FAILED);
					rlt.setMsg("上传文件时发生异常");
				}
			
			return rlt;
		}
		 
		 
		 //新增商品
		@RequestMapping("addItemAdmin")
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
		 @RequestMapping("addItemPageAdmin")
		 public String addItemPageAdmin(String type,String itemId,Map<String, Object> map){
			 
			 //后面需要判断类型是修改类型需要查出对象结果返回给前台显示
			
			 if("modify".equals(type)){
				 
				  Item item = itemService.selectByPrimaryKey(Long.parseLong(itemId));
				   map.put("item", item);
				   map.put("itemId", itemId);
				 }
			 map.put("type", type);
			 
			 
			return "item/addItemAdmin"; 
		 }
		 
	  
}

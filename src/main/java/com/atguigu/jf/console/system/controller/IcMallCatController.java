package com.atguigu.jf.console.system.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.item.bean.pojo.IcMallcat;

import com.atguigu.jf.console.system.service.IcMallCatService;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("system")
public class IcMallCatController {

	 @Autowired
	 private IcMallCatService icMallCatService;
	 
	    //删除类目
	     @RequestMapping(value="deleteSystem",method=RequestMethod.GET)
		 @ResponseBody
		 public ResultBean deleteSystem(String mallCatId){
			 
		 ResultBean rlt = new ResultBean();
		 
		    IcMallcat icMallcat = new IcMallcat();
		    
		    icMallcat.setMallCatId(Long.parseLong(mallCatId));
		    
		    icMallcat.setDataState(Short.parseShort("0"));
		 
			 int i = icMallCatService.updateByPrimaryKeySelective(icMallcat);
			 
			 //System.out.println(i +"****");
			 if(i <=0){
				 
				 //修改用户信息失败
				 rlt.setResult(ResultBean.RESULT_FAILED);
				 rlt.setMsg("删除用户失败！");
			 }else{
				 
				 //修改用户信息成功
				 rlt.setResult(ResultBean.RESULT_SUCCESS);
				 rlt.setMsg("删除用户成功！");
			   }
			 
			 
			 return rlt;
		 }
	 //修改类目modifySystem
	@RequestMapping("modifySystem")
	@ResponseBody
	public ResultBean modifySystem(IcMallcat icMallcat){
		
		int count = icMallCatService.updateByPrimaryKeySelective(icMallcat);
		
		//System.out.println(count + "*****");
		ResultBean rs = new ResultBean();
		
		if(count <=0){
			
			rs.setResult(ResultBean.RESULT_FAILED);
			rs.setMsg("修改失败！");
		}else{
			rs.setResult(ResultBean.RESULT_SUCCESS);
			rs.setMsg("修改成功！");
			
		}
		return rs;
	}
	 
	
	 //图片上传
	 @RequestMapping(value="uploadFile", method = RequestMethod.POST)
	 @ResponseBody
	public ResultBean up1oadFile(MultipartFile uploadFile,
			                      HttpSession session){
		
		ResultBean rs = new ResultBean();
		
		//获取图片的真实路径
		ServletContext servletContext = session.getServletContext();
		
		//声明虚拟路径
		String virtualPath = "/itemPicLogos";
		
		// 4.根据虚拟路径生成真实路径
		String path = servletContext.getRealPath(virtualPath);
		
		//获取原始文件名
		String filename = uploadFile.getOriginalFilename();
		
		//创建文件
		File file = new File(path + "/"+ filename);
	
		try{
		       uploadFile.transferTo(file);
		       rs.setResult(ResultBean.RESULT_SUCCESS);
		  
		       rs.setMsg(virtualPath+"/"+filename);
		       
		}catch(Exception e){
		       rs.setResult(ResultBean.RESULT_FAILED);
		       rs.setMsg("上传图片失败！");
		}
		
		return rs;
		
    }
	 //新增页面
	 @RequestMapping("addSystemMall")
	 @ResponseBody
	 public ResultBean addSystem(IcMallcat icMallcat){
		 
		 //调用新增方法
		 int count = icMallCatService.insertSelective(icMallcat);
		 
		 ResultBean rs = new ResultBean();
		 
		 //判断
		 if(count <=0){
			 
			 rs.setResult(ResultBean.RESULT_FAILED);
			 rs.setMsg("保存类目失败！");
		 }else{
			 
			 rs.setResult(ResultBean.RESULT_SUCCESS);
			 rs.setMsg("保存类目成功！");
		 }
		 
		 return rs;
	 }
	 
	 
	 //去新增页面
	@RequestMapping("addSystemPage")
	public String addSystemPage(String type,String mallCatId,Map<String, Object> map){
		
		if("modify".equals(type)){
			 
			   IcMallcat icMallcat = icMallCatService.selectByPrimaryKey(Long.parseLong(mallCatId));
			   map.put("icMallcat", icMallcat);
			
			   map.put("mallCatId", mallCatId);
			 }
		
		map.put("type", type);
		
	    //System.out.println(mallCatId);
	    
		return "system/addMallCat";
	}
	 
	 
	 //获取类目列表
	 @RequestMapping("selectIcMallCatList")
	 @ResponseBody
	 public String selectIcMallCatList(IcMallcat icMallcat,Integer page,Integer limit) throws Exception{
		 
			 //构造参数，查询用户的列表
			 Map<String, Object> map = new HashMap<>();
			
			 map.put("mallCatCode",icMallcat.getMallCatCode());
			 map.put("mallCatName",icMallcat.getMallCatName());
			 
			 //开始分页
			 PageHelper.startPage(page, limit);
			 
			 //查询用户的总记录数
		     List<IcMallcat> catList = icMallCatService.selectIcMallCatList(map);
			 
			 //使用pageHelper进行包装
			 PageInfo<IcMallcat> pageInfo = new PageInfo<>(catList);
			
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

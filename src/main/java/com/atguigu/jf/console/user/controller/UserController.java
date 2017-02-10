package com.atguigu.jf.console.user.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.WebServiceFeatureAnnotation;

import org.glassfish.jersey.uri.PathTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyun.oss.common.comm.ServiceClient.Request;
import com.atguigu.jf.console.common.bean.bo.PageModel;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.UserService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("user")
public class UserController {

	 @Autowired
	 private UserService userService;
	
	 /**
	  * 
	  * @方法名: selectsysOpListByPageHelper  
	  * @功能描述: 基于pageHelper的插件,同时使用FastJson插件将json数据直接转换成string字符串类型  
	  * @param sysOp
	  * @param page
	  * @param limit
	  * @return
	  * @throws Exception
	  * @作者 kqj 
	  * @日期 2016年11月29日
	  */
	 @RequestMapping(value="selectsysOpListByPageHelper")
	 @ResponseBody
	 public String selectsysOpListByPageHelper(SysOp sysOp,Integer page,Integer limit) throws Exception{
		 
		 //构造参数，查询用户的列表
		 Map<String, Object> map = new HashMap<>();
		
		 map.put("opName",sysOp.getOpName());
		 map.put("opKind",sysOp.getOpKind());
		 
		 //开始分页
		 PageHelper.startPage(page, limit);
		 
		 //查询用户的总记录数
		 List<SysOp> list = userService.selectsysOpListByPageHelper(map);
		 
		 //使用pageHelper进行包装
		 PageInfo<SysOp> pageInfo = new PageInfo<>(list);
		
		 //Json日期格式化
		 JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		 //将包装好的对象直接转化成Json的格式
		 String str = JSON.toJSONString(pageInfo,SerializerFeature.WriteDateUseDateFormat,
				        SerializerFeature.WriteMapNullValue,
				        SerializerFeature.WriteNullNumberAsZero,
				        SerializerFeature.WriteNullStringAsEmpty);
		// System.out.println(str); 
		
		 
		 return str;
	 }

	 
	 //文件上传 uploadFiles
	 @RequestMapping(value="uploadFile",method=RequestMethod.POST)
	 @ResponseBody
	public ResultBean uploadFile(MultipartFile uploadFile){
		
		ResultBean rlt = new  ResultBean();
		//定义文件上传的文件夹路径
		//String path="C:\\FileUpload\\";
	      String path = "C:\\testFileUpload\\";
	
			// 2、如果文件夹不存在，则创建
			File filePath = new File(path);
			if(!filePath.exists()){
				filePath.mkdirs();
			}
		    //获取原始文件名
			String filename = uploadFile.getOriginalFilename();
			
			//创建文件
			File file = new File(path + filename);
		    
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
	 
	 
	 
	 //删除用户，需要回显信息给页面
	@RequestMapping(value="deleteUser",method = RequestMethod.GET)
	@ResponseBody
	 public ResultBean deleteUser(Long opId,HttpSession session){
		 
		ResultBean rlt = new ResultBean();
		    //从session域中获取删除用户的信息
		try {
			SysOp sysOp = (SysOp) session.getAttribute("sysOp");
		 //设置用户的id 和操作人的参数id
		    SysOp sysOp2 = new SysOp();
	        sysOp2.setOpId(opId);
	        sysOp2.setModifier(sysOp.getOpId());
				 
		 int i = userService.deleteByPrimaryKey(opId);
		 
		 if(i <=0){
			 
			 //修改用户信息失败
			 rlt.setResult(ResultBean.RESULT_FAILED);
			 rlt.setMsg("删除用户失败！");
		 }else{
			 
			 //修改用户信息成功
			 rlt.setResult(ResultBean.RESULT_SUCCESS);
			 rlt.setMsg("删除用户成功！");
		   }
		 } catch (Exception e) {
			 
			//构造返回值
			rlt.setResult(ResultBean.RESULT_FAILED);
			rlt.setMsg("删除失败！");

		}
		 
		 return rlt;
	 }
	 //修改用户，需要回显信息在添加页面显示
	 @RequestMapping("modifyUser")
	 @ResponseBody
	 public ResultBean modifyUser(SysOp sysOp){
		 
		 int count = userService.updateByPrimaryKeySelective(sysOp);
		 
		 ResultBean rlt = new ResultBean();
		 
		 if(count <=0){
			 
			 //修改用户信息失败
			 rlt.setResult(ResultBean.RESULT_FAILED);
			 rlt.setMsg("修改用户信息失败！");
		 }else{
			 
			 //修改用户信息成功
			 rlt.setResult(ResultBean.RESULT_SUCCESS);
			 rlt.setMsg("修改用户信息成功！");
		 }
		 
		 return rlt;
	 }
	 
	 //新增用户
	 @RequestMapping("addUser")
	 @ResponseBody
	 public ResultBean addUser(SysOp sysOp){
		 
		 //新增插入的条数
		 int count = userService.insertSelective(sysOp);
		 
		 //创建返回信息的模型对象
		 ResultBean rlb = new ResultBean();
		 //给页面提示
		if(count<=0){
			
			  //添加失败
		       rlb.setResult(ResultBean.RESULT_FAILED);
			   rlb.setMsg("新增用户失败！");
		}else{
			
			//添加成功
			    rlb.setResult(ResultBean.RESULT_SUCCESS);
			    rlb.setMsg("新增用户成功！");
			    
		}
			
		 return rlb;
	 }
	 //去新增用户页面
	 @RequestMapping("addUserPage")
	 public String addUserPage(String type,String opId,Map<String, Object> map){
		 //需要判断一下是修改类型的时候，需要将用户的实体信息传给页面进行回显
		 if("modify".equals(type)){
		   SysOp sysOp = userService.selectByPrimaryKey(Long.parseLong(opId));
		   map.put("sysOp", sysOp);
		   map.put("opId", opId);
		 }
		
		 map.put("type", type);
		 
		 return "user/addUser";
	 }
	 
	 //传统方式的分页
	 @RequestMapping("selectsysOpList")
	 @ResponseBody
	 public PageModel<SysOp> selectsysOpList(SysOp sysOp,Integer start,Integer limit) throws Exception{
		 
		 //构造参数，查询用户的列表
		 Map<String, Object> map = new HashMap<>();
		 map.put("start", start);
		 map.put("limit", limit);
		 map.put("opName",sysOp.getOpName());
		 map.put("opKind",sysOp.getOpKind());
		 List<SysOp> list = userService.selectsysOpList(map);
		 
		 //查询用户的总记录数
		 Integer count = userService.selectsysOpListCount(map);
		 
		 //构建、PageModel模型，将获取到数据传入到模型中
		 PageModel<SysOp> pageModel = new PageModel<>();
		 
		 pageModel.setPageNo(start);
		 pageModel.setPageSize(limit);
		 
		 pageModel.setRows(list);
		 pageModel.setTotal(count);
		 
		 return pageModel;
	 }
	 
	 
}

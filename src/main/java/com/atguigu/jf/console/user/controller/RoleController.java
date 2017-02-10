package com.atguigu.jf.console.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.user.bean.bo.SysRoleFunc;
import com.atguigu.jf.console.user.bean.pojo.SysFunc;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.bean.pojo.SysRole;
import com.atguigu.jf.console.user.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("saveTree")
	@ResponseBody
	public ResultBean saveTree(String checkedNodes) throws Exception{
		
		ResultBean rs = new ResultBean();
		
		String[] split = checkedNodes.split(",");
		String roleId = split[0];
		
		//删除关系表中的关联 roleid所有关联funcId
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("roleId", Long.parseLong(roleId));
		//执行删除
	     roleService.updateDataStateByRoleId(map);
		
		//新增
	     
	    List<SysRoleFunc> list = new ArrayList<>();
	    if(split != null && split.length >1){
	    	//循环处理对象并增加到list中
	    	for(int i =1; i<split.length;i++){
	    		if(split[i] !=null && split[i].length()>0){
	    			//组装对象
	    			SysRoleFunc sysRoleFunc = new SysRoleFunc();
	    			sysRoleFunc.setRoleId(Long.parseLong(roleId));
	    			sysRoleFunc.setFuncId(Long.parseLong(split[i]));
	    			sysRoleFunc.setCreator(Long.parseLong("44"));
	    			list.add(sysRoleFunc);
	    		}
	    		
	    	}
	    }
	     roleService.insertBatchSysRoleFunc(list);
	     
	     rs.setResult(ResultBean.RESULT_SUCCESS);
	     rs.setMsg("保存成功！");
		return rs;
	}
	
	
   /**
    * @方法名: getTree  
    * @功能描述:保存树  
    * @param roleId
    * @return
    * @作者 kqj 
    * @日期 2016年11月30日
    */
   @RequestMapping("getTree")
   @ResponseBody
   public List<Map<String,Object>> getTree(Long roleId) throws Exception{
	   
	   List<Map<String, Object>> list = new ArrayList<>();
	   
	   Map<String, Object> rolemap = new HashMap<>();
	   
	   //将角色的id放到map集合里面
	   rolemap.put("roleId", roleId);
	  // System.out.println(roleId);
	   //调用方法获取所有的功能菜单
	   List<SysFunc> funcList = roleService.selectFuncList();
	   List<SysFunc> funcListByRoleId = roleService.selectFuncListByRoleId(rolemap);
	   
	   //遍历功能菜单,将前台需要显示的字段放到map集合里面
	   for (SysFunc sysFunc : funcList) {
		
		   Map<String, Object> funcmap = new HashMap<>();
		   funcmap.put("funcId", sysFunc.getFuncId());
		   funcmap.put("supFuncId", sysFunc.getSupFuncId());
		   funcmap.put("name", sysFunc.getFuncName());
		   funcmap.put("roleId", roleId);
		   
		  //遍历根据roleId获取的功能菜单
		  for (SysFunc sysFunc2 : funcListByRoleId) {
			
			  Long funcId = sysFunc2.getFuncId();
			  
			  //判断根据roleId获取的是否和所有的一致，一致的进行标记
			  if(funcId.equals(sysFunc.getFuncId())){
				  
				  funcmap.put("checked", true);
			  }
		} 
		  list.add(funcmap);   
	}
	   
	   return list;
   }
	
	
	
	/**
	 * @方法名: selectSysRoleList  
	 * @功能描述:查询角色的列表  ，将结果封装传给页面
	 * @param sysRole
	 * @param page
	 * @param limit
	 * @return
	 * @throws Exception
	 * @作者 kqj 
	 * @日期 2016年11月29日
	 */
	@RequestMapping(value="selectSysRoleList")
	 @ResponseBody
	 public List<SysRole> selectSysRoleList(SysRole sysRole,Integer page,Integer limit) throws Exception{
		 
		 //构造参数，查询角色的列表
		 Map<String, Object> map = new HashMap<>();
		
		 map.put("roleId",sysRole.getRoleId());
		 map.put("roleName",sysRole.getRoleName());
		
		 //查询用户的总记录数
		 List<SysRole> list = roleService.selectSysRoleList(map);
		 
		return list;
	}
}

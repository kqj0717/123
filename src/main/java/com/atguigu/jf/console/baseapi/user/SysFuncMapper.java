package com.atguigu.jf.console.baseapi.user;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysFunc;

public interface SysFuncMapper {
	
    int deleteByPrimaryKey(Long funcId);

    int insert(SysFuncBean record);

    int insertSelective(SysFuncBean record);

    SysFuncBean selectByPrimaryKey(Long funcId);

    int updateByPrimaryKeySelective(SysFuncBean record);

    int updateByPrimaryKey(SysFuncBean record);
    /**
     * @方法名: selectSysFuncByOpId  
     * @功能描述: 根据opId 查询菜单
     * @param map
     * @return
     * @作者 kqj 
     * @日期 2016年11月30日
     */
     List<SysFuncBean> selectSysFuncByOpId(Map<String, Object> map);
    
     /**
      * 
      * @方法名: selectFuncList  
      * @功能描述: 根据roleId查询所有的菜单 
      * @param map
      * @return
      * @作者 kqj 
      * @日期 2016年11月30日
      */
     List<SysFunc> selectFuncList() throws Exception;
    
     /**
      * 
      * @方法名: selectFuncListByRoleId  
      * @功能描述: 根据roleId查询所有的菜单 
      * @param map
      * @return
      * @作者 kqj 
      * @日期 2016年11月30日
      */
    
     
     List<SysFunc> selectFuncListByRoleId(Map<String, Object> map) throws Exception;
} 
   
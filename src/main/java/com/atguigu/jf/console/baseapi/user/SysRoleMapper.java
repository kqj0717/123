package com.atguigu.jf.console.baseapi.user;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysRoleFunc;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.bean.pojo.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    /**
     * 
     * @方法名: selectSysRoleList  
     * @功能描述: 查询角色列表  
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年11月29日
     */
    List<SysRole> selectSysRoleList(Map<String, Object> map) throws Exception;
    
 
    
    /**
     * @方法名: updateDataStateByRoleId  
     * @功能描述: 删除关系，将状态改成0  
     * @param map
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年11月30日
     */
    void updateDataStateByRoleId(Map<String, Object> map) throws Exception;
    
    /**
     * @方法名: insertBatchSysRoleFunc  
     * @功能描述: 批量新增关系 
     * @param list
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年11月30日
     */
    void insertBatchSysRoleFunc(List<SysRoleFunc> list) throws Exception; 
    
    
    
}
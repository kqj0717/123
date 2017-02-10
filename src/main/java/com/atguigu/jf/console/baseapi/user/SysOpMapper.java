package com.atguigu.jf.console.baseapi.user;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface SysOpMapper {
	/**
	 * 
	 * @方法名: deleteByPrimaryKey  
	 * @功能描述: 根据用户的id删除用户 
	 * @param opId
	 * @return
	 * @作者 kqj 
	 * @日期 2016年11月28日
	 */
    int deleteByPrimaryKey(Long opId);

    int insert(SysOp record);
  /**
   * 
   * @方法名: insertSelective  
   * @功能描述: 插入新增语句 
   * @param record
   * @return
   * @作者 kqj 
   * @日期 2016年11月28日
   */
    int insertSelective(SysOp record);
/**
 * 
 * @方法名: selectByPrimaryKey  
 * @功能描述: 查询用户的实体信息进行回显 
 * @param opId
 * @return
 * @作者 kqj 
 * @日期 2016年11月28日
 */
    
    SysOp selectByPrimaryKey(Long opId);
   /**
    * 
    * @方法名: updateByPrimaryKeySelective  
    * @功能描述: 根据用户的id修改方法 
    * @param record
    * @return
    * @作者 kqj 
    * @日期 2016年11月28日
    */
    int updateByPrimaryKeySelective(SysOp record);

    int updateByPrimaryKey(SysOp record);
   /**
    * 
    * @方法名: selectSysOpByNameAndPwd  
    * @功能描述: 根据用户名和密码查出用户的登录状态  
    * @param record
    * @return
    * @throws Exception
    * @作者 kqj 
    * @日期 2016年11月25日
    */
    SysOp selectSysOpByNameAndPwd(SysOp record) throws Exception;

    /**
     * 
     * @方法名: selectsysOpList  
     * @功能描述: 获取用户的记录  
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年11月26日
     */
   List<SysOp> selectsysOpList(Map<String, Object> map) throws Exception;
   
   /**
    * 
    * @方法名: selectsysOpListByPageHelper  
    * @功能描述:基于pageHelper插件的分页方法 
    * @param map
    * @return
    * @throws Exception
    * @作者 kqj 
    * @日期 2016年11月29日
    */
   
   List<SysOp> selectsysOpListByPageHelper(Map<String, Object> map) throws Exception;
   
   
   
   /**
    * 
    * @方法名: selectsysOpListCount  
    * @功能描述: 获取用户的总记录数  
    * @param map
    * @return
    * @throws Exception
    * @作者 kqj 
    * @日期 2016年11月26日
    */
   Integer selectsysOpListCount(Map<String, Object> map) throws Exception;
    
}
package com.atguigu.jf.console.baseapi.item;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.pojo.IcitemType;
import com.atguigu.jf.console.item.bean.pojo.Item;
import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface ItemMapper {
	
	/**
	 * @方法名: deleteByPrimaryKey  
	 * @功能描述: 删除商品的方法 
	 * @param itemId
	 * @return
	 * @作者 kqj 
	 * @日期 2016年12月1日
	 */
    int deleteByPrimaryKey(Long itemId);

    int insert(Item record);
    
    /**
     * @方法名: insertSelective  
     * @功能描述:   新增用户
     * @param record
     * @return
     * @作者 kqj 
     * @日期 2016年12月1日
     */
    int insertSelective(Item record);

    Item selectByPrimaryKey(Long itemId);
    /**
     * @方法名: updateByPrimaryKeySelective  
     * @功能描述: 修改商品信息的方法  
     * @param record
     * @return
     * @作者 kqj 
     * @日期 2016年12月1日
     */
    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
  /**
   * @方法名: selectsysOpListByPageHelper  
   * @功能描述: 获取商品列表信息  
   * @param map
   * @return
   * @throws Exception
   * @作者 kqj 
   * @日期 2016年12月1日
   */
    List<Item> selectItemList(Map<String, Object> map) throws Exception;
    
    /**
     * @方法名: selectNameByType  
     * @功能描述: 根据商品的id 获取商品的状态  
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月1日
     */
    List<Item> selectItemStateById(Map<String, Object> map) throws Exception;
}
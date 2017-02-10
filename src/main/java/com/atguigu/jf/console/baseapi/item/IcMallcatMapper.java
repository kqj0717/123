package com.atguigu.jf.console.baseapi.item;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.pojo.IcMallcat;

public interface IcMallcatMapper {
	/**
	 * 
	 * @方法名: deleteByPrimaryKey  
	 * @功能描述: 删除类目
	 * @param mallCatId
	 * @return
	 * @作者 kqj 
	 * @日期 2016年12月2日
	 */
    int deleteByPrimaryKey(Long mallCatId);

    int insert(IcMallcat record);

    int insertSelective(IcMallcat record);
    
    /**
     * 
     * @方法名: selectByPrimaryKey  
     * @功能描述: 查询对象  
     * @param mallCatId
     * @return
     * @作者 kqj 
     * @日期 2016年12月2日
     */
    IcMallcat selectByPrimaryKey(Long mallCatId);

    /**
     * @方法名: updateByPrimaryKeySelective  
     * @功能描述: 修改方法 
     * @param record
     * @return
     * @作者 kqj 
     * @日期 2016年12月2日
     */
    int updateByPrimaryKeySelective(IcMallcat record);

    int updateByPrimaryKey(IcMallcat record);
    
    //selectMallcatNameBymallCode
    /**
     * @方法名: selectMallcatNameBymallCode  
     * @功能描述: 根据商品类目编码获取商品类目的名称  
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月1日
     */
    List<IcMallcat> selectMallcatNameBymallCode(Map<String, Object> map) throws Exception;
    
    /**
     * @方法名: selectIcMallCatList  
     * @功能描述: 获取类目列表 
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月2日
     */
    List<IcMallcat> selectIcMallCatList(Map<String, Object> map) throws Exception;
}






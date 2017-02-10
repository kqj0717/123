package com.atguigu.jf.console.baseapi.common;

import com.atguigu.jf.console.common.bean.pojo.CodeValue;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CodeValueMapper {
    int deleteByPrimaryKey(@Param("codeType") Integer codeType, @Param("codeValue") String codeValue);

    int insert(CodeValue record);

    int insertSelective(CodeValue record);

    CodeValue selectByPrimaryKey(@Param("codeType") Integer codeType, @Param("codeValue") String codeValue);

    int updateByPrimaryKeySelective(CodeValue record);

    int updateByPrimaryKey(CodeValue record);
    
    /**
     * @方法名: selectCodeValueByType  
     * @功能描述: 通过代码的类别拿到代码的取值  
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年11月26日
     */
    List<CodeValue> selectCodeValueByType(Map<String, Object> map) throws Exception;
    
    /**
     * 
     * @方法名: selectCodeType  
     * @功能描述: 商品状态的取值  
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月6日
     */
    
    List<CodeValue> selectCodeType(Map<String, Object> map) throws Exception;
    
    
}
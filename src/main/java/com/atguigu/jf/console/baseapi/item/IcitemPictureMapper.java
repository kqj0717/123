package com.atguigu.jf.console.baseapi.item;

import java.util.List;
import java.util.Map;

import com.aliyun.oss.internal.ResponseParsers.ListImageStyleResponseParser;
import com.atguigu.jf.console.item.bean.pojo.IcitemPicture;

public interface IcitemPictureMapper {
    int deleteByPrimaryKey(Long itempicId);

    int insert(IcitemPicture record);

    int insertSelective(IcitemPicture record);

    IcitemPicture selectByPrimaryKey(Long itempicId);

    int updateByPrimaryKeySelective(IcitemPicture record);

    int updateByPrimaryKey(IcitemPicture record);
    
    List<IcitemPicture> selectItempicFlag(Map<String, Object> map) throws Exception;
}
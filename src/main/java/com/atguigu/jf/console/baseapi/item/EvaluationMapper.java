package com.atguigu.jf.console.baseapi.item;

import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;

import com.atguigu.jf.console.item.bean.bo.EvaluationBean;
import com.atguigu.jf.console.item.bean.pojo.Evaluation;

public interface EvaluationMapper {
    int deleteByPrimaryKey(Long evaluationId);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Long evaluationId);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);
    /**
     * 
     * @方法名: selectEvaluationList  
     * @功能描述: 获取评论列表 
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月7日
     */
    List<EvaluationBean> selectEvaluationList(Map<String, Object> map) throws Exception;
    
    /**
     * @方法名: selectEvaluationPic  
     * @功能描述: 获取评论图片集合 
     * @param map
     * @return
     * @throws Exception
     * @作者 kqj 
     * @日期 2016年12月7日
     */
   List<String> selectEvaluationPic(Map<String, Object> map)throws Exception;
    
   /**
    * @方法名: selectEvaluation  
    * @功能描述: 获取评论的对象  
    * @param evaluationId
    * @return
    * @作者 kqj 
    * @日期 2016年12月8日
    */
   EvaluationBean selectEvaluation(Long evaluationId);
   
   /**
    * @方法名: updateEvaluationStatus  
    * @功能描述: 更新评论状态  
    * @param evaluationBean
    * @return
    * @作者 kqj 
    * @日期 2016年12月8日
    */
   int updateEvaluationStatus(EvaluationBean evaluationBean);
   
}
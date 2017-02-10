<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ include file="/page/common/commonHeader.jsp" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <title>评价详情</title>
<head></head>
<body>
	    <tr>
	      <td>商家名称：</td>
	      <td>${evaluationBean.shopName }</td>
	    </tr>
	    <br><br>
	    <tr>
	      <td>商品名称:</td>
	      <td>${evaluationBean.itemName }</td>
	    </tr>
	    <br><br>
	   <tr>
	      <td>评分分值:</td>
	      <td>${evaluationBean.evaluationGrade }</td>
	    </tr>
	    <br><br>
	    <tr>
	      <td>评论时间:</td>
	      <td><fmt:formatDate value="${evaluationBean.evaluationTime }"/> </td>
	    </tr>
         <br><br>
	     <tr>
	      <td>评论内容:</td>
	      <td>${evaluationBean.evaluationContent }</td>
	    </tr>
         <br><br>
     
      <button class="quit-btn" onclick="history.back();">返回</button>
      
</body>

</html>
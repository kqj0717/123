<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/commonHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>商品审批</title>
    
</head>

<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
        <!-- 第一个下拉框 -->
            <span class="query-item mr20">
                <label class="query-txt">商品类型</label>
                <div class="combo" id="itemTypeCombo"></div>
                <input type="hidden" id="itemType" value="">
            </span>
         <!-- 第二个下拉框 -->
            <span class="query-item mr20">
                <label class="query-txt">商品类别</label>
                <div class="combo" id="mallCatCodeCombo"></div>
                <input type="hidden" id="mallCatCode" value="">
            </span>
          <!-- 第三个下拉框 -->
           <span class="query-item mr20">
                <label class="query-txt">商品状态</label>
				<div class="combo" id="itemApprStateCombo"></div>
				<input type="hidden" id="itemApprState" name="itemApprState" value="">
            </span>
            
            <span class="query-item"> 
               <label class="query-txt">商品名称</label>
				<input class="query-input" id="itemName" name="itemName" placeholder="商品名称或编码">
			 </span> <span class="query-btns">
           
                 <button type="submit" class="btn-search" title="查询"></button>
                 <button type="submit" class="btn-reset" title="重置"></button>
             </span>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    <script src="<%=rootPath %>/page/item/js/itemMgntAdmin.js"></script>
</body>

</html>
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
    <title>结算管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
</head>

<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
        
        <!-- 四个日期框 -->
                    <li>
						<label class="lbl-txt">销售开始时间：</label>
						<span class="query-item mr10 posR">
		                   <input class="query-input" id="itemValidStart" name="itemValidStart" placeholder="销售开始时间" />
		                   <i class="cal-ico" id="timeStartBox"></i>
	           			 </span>
	           		</li>
	                  <li>
	           			<label class="lbl-txt">销售结束时间：</label>
			            <span class="query-item mr10 posR">
			                   <input class="query-input" id="itemValidEnd" name="itemValidEnd" placeholder="销售结束时间" />
			                   <i class="cal-ico" id="timeEndBox"></i>
			            </span>
			        </li>
        
                    <li>
						<label class="lbl-txt">结算开始时间：</label>
						<span class="query-item mr10 posR">
		                   <input class="query-input" id="itemStart" name="itemStart" placeholder="结算开始时间" />
		                   <i class="cal-ico" id="orderStartBox"></i>
	           			 </span>
	           		</li>
	                  <li>
	           			<label class="lbl-txt">结算结束时间：</label>
			            <span class="query-item mr10 posR">
			                   <input class="query-input" id="itemEnd" name="itemEnd" placeholder="结算结束时间" />
			                   <i class="cal-ico" id="orderEndBox"></i>
			            </span>
			        </li>
        <!-- 第一个下拉框 -->
            <span class="query-item mr20">
                <label class="query-txt">请选择分店：</label>
                <div class="combo" id="shopNameCombo"></div>
                <input type="hidden" id="shopName" value="">
            </span>
         
            <span class="query-item"> 
               <label class="query-txt"></label>
				<input class="query-input" id="shopName" name="shopName" placeholder="请输入商品名称或编码">
			 </span>
		<!--  <span class="query-btns"> -->
           
        
                <button type="submit" class="btn-search" title="搜索"></button>
                 <!-- <input type="submit" value="搜索"> -->
                 <input type="submit" value="导出EXCEL">
                 <input type="submit" value="结算">
                 <!-- <button type="submit" value="导出EXCEL"></button>
                 <button type="submit" class="btn-reset2" title="结算"></button> -->
             <!-- </span> -->
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    
    <!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>
    
    <script src="<%=rootPath %>/page/order/js/MerchantSettlement.js"></script>
</body>

</html>
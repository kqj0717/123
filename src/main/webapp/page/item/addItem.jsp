<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/commonHeader.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>添加/删除商品</title>
    
    <!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
</head>

<body>
    <div class="asideR-cont">
        <div class="add-cnt">
            <ul class="add-lst">
              <form id="fbean" action="" method="post">
                <!-- 两个隐藏,接收后台传过来的值 -->
                <input type="hidden" name="type" id="type" value="${type}">
                <input type="hidden" name="itemId" id="itemId"  value="${itemId}">
                <input type="hidden" name="itemPicUrl" id="itemPicUrl" value="${itemPicUrl }"> 
                <!--两个下拉框 -->
                      <li>
	                      <label class="lbl-txt">商品类型：</label>
	                      <span class="query-item">
	                          <div class="combo" id="itemTypeCombo"></div>
	                      </span>
	                      <span class="require">*</span>
	                      <input type="hidden" id="itemType" name="itemType" value="${Item.itemType }"/>
	                  </li>
	                   <li>
	                      <label class="lbl-txt">商品类别：</label>
	                      <span class="query-item">
	                          <div class="combo" id="mallCatCodeCombo"></div>
	                      </span>
	                      <span class="require">*</span>
	                      <input type="hidden" id="mallCatCode" name="mallCatCode" value="${IcMallcat.mallCatName }"/>
	                  </li>
	                  
	                   <li>
		                	 <label class="lbl-txt">选择分店：</label>
		                	 <span >
			                	<ul id="treeDemo" class="ztree"></ul>
		                	 </span>
	                   </li>
	                   <li>
	                      <label class="lbl-txt">商品名称：</label>
	                      <input type="text" class="input-text ver-right"  id="itemName" name="itemName" value="${Item.itemName }"/>
	                      <span class="require">*</span>
	                  </li>
	                  <li>
	                    <label class="lbl-txt">商品原价：</label>
	                    <span class="require">*</span>
	                    <input type="text" class="input-text ver-error" id="itemMarketPrice" name="itemMarketPrice" value="${item.itemMarketPrice }"/> 积分币
	                </li>
	                
	                  <li>
	                    <label class="lbl-txt">商品售价：</label>
	                    <span class="require">*</span>
	                    <input type="text" class="input-text ver-error" id="itemSalePrice" name="itemSalePrice" value="${item.itemSalePrice }"/> 积分币
	                </li>
	                  
	                     <input type="checkbox" name="order" >免预约
	                  <!-- 一排两个 -->
	                  <li>
						<label class="lbl-txt">有效期起：</label>
						<span class="query-item mr10 posR">
		                   <input class="query-input" id="itemValidStart" name="itemValidStart" placeholder="时间起" />
		                   <i class="cal-ico" id="timeStartBox"></i>
	           			 </span>
	           		</li>
	                  <li>
	           			<label class="lbl-txt">有效期止：</label>
			            <span class="query-item mr10 posR">
			                   <input class="query-input" id="itemValidEnd" name="itemValidEnd" placeholder="时间止" />
			                   <i class="cal-ico" id="timeEndBox"></i>
			            </span>
			        </li>
	                  <li>
	                    <label class="lbl-txt">购买须知：</label>
	                    <span class="require">*</span>
	                    <textarea rows="14" cols="60" name="itemDesc1">${item.itemDesc1 }</textarea>
	                </li>
	                
	                 <li>
	                    <label class="lbl-txt">商品描述信息：</label>
	                    <span class="require">*</span>
	                    <textarea rows="10" cols="60" name="itemDes2c">${item.itemDesc2 }</textarea>
	                </li>
	                  
	                 
	                  <!-- 注意：文件上传的时候需要放到表单域中 -->
<%-- <input type="hidden" class="input-text"  id="itempicId" name="itempicId" value="${IcitemPicture.itempicId}"/> --%>
                  </form>
                  
            	<li class="bot-line"></li>
                <li>
                    <label class="lbl-txt">商品主图：</label>
                    
                    <div class="upload-box">
                        <input type="text" class="input-text" id="path" />
                        <input type="file" class="file-upload" onchange="showPath()" id="uploadFile" name="uploadFile" />
                        
                        <button class="browse-btn">浏览</button>
                    </div>    
                    <button class="upload-btn" id="upload-btn">上传</button>
                </li>
                
            </ul>
            <div class="form-aciton">
            
                <button class="submit-btn" id="saveItem1">提交审批</button>
                <button class="submit-btn" id="saveItem">保存</button>
                <button class="quit-btn" onclick="history.back();">取消</button>
                
            </div>
        </div>
    </div>
   
   	<!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>

     <script type="text/javascript" src="<%=rootPath %>/res/ztree/js/jquery.ztree.all.js"></script>
    <script src="<%=rootPath %>/page/item/js/addItem.js"></script>
</body>

</html>
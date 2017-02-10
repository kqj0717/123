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
    <title>添加/删除类目</title>
    
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
                <input type="hidden" name="mallCatId" id="mallCatId"  value="${mallCatId}">
                 
              
	                  <li>
	                      <label class="lbl-txt">类目名称：</label>
	                      <input type="text" class="input-text ver-right"  id="mallCatName" name="mallCatName" value="${icMallcat.mallCatName }"/>
	                  </li>
	                  
	                  <li>
	                      <label class="lbl-txt">类目描述：</label>
	                      <input type="text" class="input-text ver-right"  id="mallCatDesc" name="mallCatDesc" value="${icMallcat.mallCatDesc }"/>
	                  </li>
	                  <!-- 注意：文件上传的时候需要放到表单域中 -->
                        <input type="hidden" class="input-text"  id="mallCatPicUrl" name="mallCatPicUrl" value="${icMallcat.mallCatPicUrl}"/>
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
            
               
                <button class="submit-btn" id="saveItem">确认提交</button>
                <button class="quit-btn" onclick="history.back();">取消</button>
                
            </div>
        </div>
    </div>
   
   	<!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>

    
    <script src="<%=rootPath %>/page/system/js/addMallCat.js"></script>
</body>

</html>
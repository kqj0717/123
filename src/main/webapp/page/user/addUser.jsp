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
    <title>添加积分供应商</title>
    
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
                <input type="hidden" name="opId" id="opId"  value="${opId}">
                 
            <!-- 下拉框 -->
               <li>
	                      <label class="lbl-txt">操作员类型：</label>
	                      <span class="query-item">
	                          <div class="combo" id="opKindCombo"></div>
	                      </span>
	                      <span class="require">*</span>
	                      <input type="hidden" id="opKind" name="opKind" value="${sysOp.opKind }"/>
	                  </li>
	                  <li>
	                      <label class="lbl-txt">操作员名称：</label>
	                      <input type="text" class="input-text ver-right"  id="opName" name="opName" value="${sysOp.opName }" />
	                      <span class="require">*</span>
	                  </li>
	                  <li>
	                      <label class="lbl-txt">操作员编码：</label>
	                      <input type="text" class="input-text ver-right"  id="opCode" name="opCode" value="${sysOp.opCode}" />
	                      <span class="require">*</span>
	                  </li>
	                  <li>
	                      <label class="lbl-txt">登录名：</label>
	                      <input type="text" class="input-text ver-right"  id="loginCode" name="loginCode" value="${sysOp.loginCode }" />
	                      <span class="require">*</span>
	                  </li>
	                  <li>
	                      <label class="lbl-txt">手机号：</label>
	                      <input type="text" class="input-text ver-right"  id="mobileNo" name="mobileNo" value="${sysOp.mobileNo }" />
	                  </li>
	                  <li>
	                      <label class="lbl-txt">电子邮件地址：</label>
	                      <input type="text" class="input-text ver-right"  id="emailAdress" name="emailAdress" value="${sysOp.emailAdress }" />
	                  </li>
	                  <!-- 注意：文件上传的时候需要放到表单域中 -->
                        <input type="hidden" class="input-text"  id="opPic" name="opPic" value="${sysOp.opPic }"/>
                  </form>
            	<li class="bot-line"></li>
                <li>
                    <label class="lbl-txt">头像上传：</label>
                    
                    <div class="upload-box">
                        <input type="text" class="input-text" id="path" />
                        <input type="file" class="file-upload" onchange="showPath()" id="uploadFile" name="uploadFile" />
                        
                        <button class="browse-btn">浏览</button>
                    </div>    
                    <button class="upload-btn" id="upload-btn">上传</button>
                </li>
                
            </ul>
            <div class="form-aciton">
                <button class="submit-btn" id="saveUser">保存</button>
                <button class="quit-btn" onclick="history.back();">取消</button>
                
            </div>
        </div>
    </div>
   
   	<!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>

    
    <script src="<%=rootPath %>/page/user/js/addUser.js"></script>
</body>

</html>
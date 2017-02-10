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
    <title>列表</title>
    
</head>

<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
            <span class="query-item mr20">
                <label class="query-txt">操作员类型</label>
                <div class="combo" id="opKindCombo"></div>
                <input type="hidden" id="opKind" value="">
            </span>
            <span class="query-item">
                <input class="query-input" id="opName" placeholder="操作员名称">
            </span>
            <span class="query-btns">
                 <button type="submit" class="btn-search" title="查询"></button>
                 <button type="submit" class="btn-reset" title="重置"></button>
             </span>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    <script src="<%=rootPath %>/page/user/js/userMgnt.js"></script>
</body>

</html>
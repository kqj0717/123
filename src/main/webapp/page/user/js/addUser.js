//注册保存事件
Ext.onReady(function(){
	initBtnFunc();
	initCombo();//初始化下拉框
	//deleteUser(opId);
	
});

/*init combo */
function initCombo(){
    var store = Ext.create('Ext.data.Store', {
		// 定义模型
	    fields: ['codeValue', 'codeName'],
	    
	    // 通过静态的数据来加载下拉框
	    // 动态加载
	    proxy: {
	    	 // 异步请求
	         type: 'ajax',
	         // 后台服务地址
	         url: rootPath + '/common/getCodeValue',
	         reader: {
	        	 // 数据解析的格式json
	             type: 'json'
	         },
	         // 设置codeType作为参数
	         extraParams :{
	        	 codeType : "1003"
	         }
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'opKindCombo',
        displayField: 'codeName',
        // 字段名对应的值
	    valueField: 'codeValue',
        width: 220,
        labelWidth: 130,		
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value){
        		$("#opKind").val(this.getValue());
        	},
        	// 渲染之后的回调
        	render : function(){
        		//stroe.load();
        		this.setValue($("#opKind").val());
        	}
        }
    });

}
//定义摁钮保存事件
function initBtnFunc(){
	$(".submit-btn").click(function(){
		
/*		//传统方式的表单提交
		var type = $("#type").val();
		//定义表单的action值
		if(type == "add"){
			$("#fbean").attr("action",rootPath + "/user/addUser");
		}else{
			$("#fbean").attr("action",rootPath + "/user/modifyUser");
		}
		//提交表单
		$("#fbean").submit();*/
		
		/**
		 * ajax提交表单
		 */
		var type = $("#type").val();
		var url = '';
		if(type == "add"){
			url = rootPath + "/user/addUser";
			
		}else{
			url = rootPath + "/user/modifyUser";
		}
		
		$.ajax({
			url : url,
			dataType : 'json',
		    type : 'POST',
		    data : $("#fbean").serialize(),
		    success: function(value){
		    	//将后台传入前台提示打印出来
		    	alert(value.msg);
		    	window.location.href = rootPath + '/page/user/userMgnt.jsp';
		    },
		    error:function(value){
		    	alert("发生错误！")
		    }
			
		});
	
	});



/**
 * 文件上传
 */
$(".upload-btn").click(function(){
	var f = $("#uploadFile").val();
	if (f == "") {// 先判断是否已选择了文件
		alert("请选择文件！");
		return false;
	}
	f = f.substr(f.lastIndexOf('.') + 1).toLowerCase();
	var ext = '.jpg.jpeg.gif.bmp.png.';
	if (ext.indexOf('.' + f + '.') == -1) {
		alert("图片格式不正确！");
		return false;
	}
	// ajax方式上传文件
	$.ajaxFileUpload({
		//url : rootPath + '/user/uploadFileByOss',
		url : rootPath + '/user/uploadFile',
		type : 'post',
		dataType : 'json',
		// 文本域的id
		fileElementId : 'uploadFile',
		data :{
			
		},
		success:function(data){
			if(data.result == "success"){
				$("#opPic").val(data.msg);
			}
			alert(data.msg);
		},
		error:function(data){
			
		}
		
	 });
   });
}

/**
 * 文件路径的显示
 */
function showPath(){
	var filePath = $("#uploadFile").val();
	$("#path").val(filePath);
}



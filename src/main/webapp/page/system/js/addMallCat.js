//注册保存事件
Ext.onReady(function(){
	initBtnFunc();
	
});


function initBtnFunc(){

//定义摁钮保存事件
	$(".submit-btn").click(function(){
		
		var type = $("#type").val();
		var url = '';
		if(type == "add"){
			url = rootPath + "/system/addSystemMall";
			
		}else{
			url = rootPath + "/system/modifySystem";
		}
		
		$.ajax({
			url : url,
			dataType : 'json',
		    type : 'POST',
		    data : $("#fbean").serialize(),
		    success: function(value){
		    	//将后台传入前台提示打印出来
		    	alert(value.msg);
		    	window.location.href = rootPath + '/page/system/mallCatMgnt.jsp';
		    },
		    error:function(value){
		    	alert("保存发生错误！")
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
		
		url : rootPath + '/system/uploadFile',
		type : 'post',
		dataType : 'json',
		// 文本域的id
		fileElementId : 'uploadFile',
		data :{
			
		},
		success:function(data){
			if(data.result == "success"){
				$("#mallCatPicUrl").val(data.msg);
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


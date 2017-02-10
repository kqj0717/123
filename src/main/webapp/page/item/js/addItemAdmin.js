Ext.Loader.setConfig({
	enabled : true
});


var data, store, columns, queryGrid, pager;


//注册保存事件
Ext.onReady(function(){
	initBtnFunc();
	initCombo();//初始化下拉框
	initComboTwo();//第二个下拉框
	//initBtnFuncTwo//审批事件
	initDateTime();  //时间事件
});

function initDateTime() {
	// 开始时间
	$("#timeStartBox").live("click", function() {
		WdatePicker({
					el : "itemValidStart",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
	// 结束时间
	$("#timeEndBox").live("click", function() {
		WdatePicker({
					el : "itemValidEnd",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
}



/*init combo */
function initCombo(){
    var store = Ext.create('Ext.data.Store', {
		// 定义模型
	    fields: ['itemType', 'itemTypeName'],
	    
	    // 动态加载
	    proxy: {
	    	 // 异步请求
	         type: 'ajax',
	         // 后台服务地址
	         url: rootPath + '/item/getItemNameByType',
	         reader: {
	        	 // 数据解析的格式json
	             type: 'json'
	         },
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'itemTypeCombo',
        displayField: 'itemTypeName',
        
        // 字段名对应的值
	    valueField: 'itemType',
        width: 100,
        labelWidth: 50,
        store: store,
        typeAhead: true,
        
        listeners:{
        	select : function(value){
        		$("#itemType").val(this.getValue());
        	},
        // 渲染之后的回调
	     render : function(){
		   this.setValue($("#itemType").val());
	}
        }
    });

}
//第二个下拉框
function initComboTwo(){
    var store = Ext.create('Ext.data.Store', {
		// 定义模型
	    fields: ['mallCatId', 'mallCatName'],
	    
	    // 通过静态的数据来加载下拉框
	    // 动态加载
	    proxy: {
	    	 // 异步请求
	         type: 'ajax',
	         // 后台服务地址
	         url: rootPath + '/item/getMallcatNameByCode',
	         reader: {
	        	 // 数据解析的格式json
	             type: 'json'
	         },
	       
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'mallCatCodeCombo',
        displayField: 'mallCatName',
        
        // 字段名对应的值
	    valueField: 'mallCatId',
        width: 100,
        labelWidth: 50,
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value){
        		$("#mallCatCode").val(this.getValue());
        	},
        	// 渲染之后的回调
   	        render : function(){
   		   this.setValue($("#mallCatCode").val());
   	}
        }
    });
   
}

function initBtnFunc(){

//定义摁钮保存事件
	$("#saveItem").click(function(){
		
		var type = $("#type").val();
		var url = '';
		if(type == "add"){
			url = rootPath + "/item/addItemAdmin";
			
		}else{
			url = rootPath + "/item/modifyItemAdmin";
		}
		
		$.ajax({
			url : url,
			dataType : 'json',
		    type : 'POST',
		    data : $("#fbean").serialize(),
		    success: function(value){
		    	//将后台传入前台提示打印出来
		    	alert(value.msg);
		    	window.location.href = rootPath + '/page/item/itemMgntAdmin.jsp';
		    },
		    error:function(value){
		    	alert("保存发生错误！")
	    }
			
		});
	
	});
	$("#saveItem1").click(function() {
		/**
		 * ajax提交表单
		 */
		var type = $("#type").val();
		var url = '';
		if (type == "add") {
			url = rootPath + "/item/addItemAdmin";
		} else {
			url = rootPath + "/item/modifyItemAdmin";
		}
		$.ajax({
			url : url,
			dataType : 'json',
			type : 'post',
			data : $("#fbean").serialize(),
			success : function(value) {
				// 将后台传入前台的提示，打印出来
				alert(value.msg);
				// 加入跳转
				window.location.href = rootPath + '/page/item/itemMgntAdmin.jsp';
			},
			error : function(value) {
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
		url : rootPath + '/item/uploadFile',
		type : 'post',
		dataType : 'json',
		// 文本域的id
		fileElementId : 'uploadFile',
		data :{
			
		},
		success:function(data){
			if(data.result == "success"){
				$("#itemPicUrl").val(data.msg);
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
//提示文字
function qtips(value, cellmeta, record, rowIndex, colIndex, store) {
	return '<span  title="' + value + '">' + value + '</span>';
}

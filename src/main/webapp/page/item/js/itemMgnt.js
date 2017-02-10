Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', rootPath + '/res/extjs/ux/');
Ext.require([
    'Ext.data.*',
    'Ext.grid.*',
    'Ext.util.*',
    'Ext.form.field.ComboBox',
    'Ext.form.FieldSet',
    'Ext.tip.QuickTipManager',
    'Ext.ux.data.PagingMemoryProxy'
    
]);

var data, store, columns, queryGrid,pager;
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    // 初始化列表
    initGrid();
    
    // 初始化下拉框
    initCombo();
    initComboTwo();
    initComboThree();
    
    initBtnFunc();
});

function initBtnFunc(){
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
			itemName : $("#itemName").val(),
			itemType : $("#itemType").val(),
			mallCatCode:$("#mallCatId").val(),
			//mallCatName:$("#mallCatName").val(),
			itemApprState : $("#itemApprState").val(), //新加
		
		};
		store.load();
	});
}
/*init combo 商品类型*/
function initCombo(){
    var store = Ext.create('Ext.data.Store', {
		// 定义模型
	    fields: ['itemType', 'itemTypeName'],
	   
	    
	    // 通过静态的数据来加载下拉框
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
        	}
        }
    });

}
//第二个下拉框 ：商品类别
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
        	}
        }
    });
   
}
//第三个下拉框 ：商品状态
function initCombo3() {
	
	var store = Ext.create('Ext.data.Store', {
		fields : [ 'codeValue', 'codeName' ],
		// 通过静态的数据来加载下拉框
		// 动态加载
		proxy : {
			// 异步请求
			type : 'ajax',
			// 后台服务地址
			url : rootPath + '/item/getCodeValue',
			reader : {
				// 数据解析的格式json
				type : 'json'
			},

			// 设置codeType作为参数
			extraParams : {
				codeType : "1001&2007"
			}
		},
		autoLoad : true
	});
	var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
		renderTo : 'itemApprStateCombo',
		displayField : 'codeName',
		valueField : 'codeValue',
		width : 220,
		labelWidth : 130,
		store : store,
		typeAhead : true,
		listeners : {
			select : function(value) {
				$("#itemApprState").val(this.getValue());
			}
		}
	});
}
function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
    	fields: [
    		
    		{name: 'itemId',  type: 'auto'},
            {name: 'itemCode',  type: 'auto'},
            {name: 'itemName',  type: 'auto'},
            {name: 'itemType',  type: 'auto'},
            {name: 'itemPicUrl',  type: 'auto'},
            {name: 'mallCatName',  type: 'auto'},
            {name: 'itemMarketPrice',  type: 'auto'},
            {name: 'itemSalePrice',  type: 'auto'},
            {name: 'itemValidStart',  type: 'auto'},
            {name: 'itemValidEnd',  type: 'auto'},
            {name: 'itemApprState',  type: 'auto'},
            {name: 'itemUdState',  type: 'auto'}
 	        
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //基于插件的分页
            url: rootPath + '/item/selectItemList',
            actionMethods :{
 		       read: "POST"
 	        },	
            reader: {
               type: 'json',
               root: 'list',
	           // 指定总条数
	           totalProperty: 'total'
            }
        }
    });
    // width确定的宽度
    columns = [
    	  
//    	{
//            text     : 'ID',
//            sortable : false,
//            dataIndex: 'itemId'
//        },
        {
        	text     : '编码',
        	sortable : false,
        	dataIndex: 'itemId',
        	renderer : qtips
        },
            {
                text     : '名称',
                width    : 110,
                sortable : true,
                dataIndex: 'itemName',
                renderer:qtips
            },
            {
                text     : '商品主图',
                sortable : false,
                dataIndex: 'itemPicUrl',
                renderer: function(value){
        			return '<img width=50 height=50  src = '+'/jf-console/'+value+'>';
                }
            },
            {
                text     : '类型',
                sortable : false,
                dataIndex: 'itemType',
                renderer : function(value){
              	   var kind = value;
              	   if(kind == '1'){
              		   return '优惠券';
              	   }else if(kind == '2'){
              		   return '联通直订';
              	   }else if(kind == '3'){
              		 return '商家串码';
              	   }
              	   else{
              		   return '璐璐通';
              	   }
                 }
            },
            {
                text     : '类别',
                sortable : false,
                dataIndex: 'mallCatName',
               
            },
            {
                text     : '原价',
                sortable : false,
                dataIndex: 'itemMarketPrice',
                renderer : qtips
            },
            {
                text     : '售价',
                sortable : false,
                dataIndex: 'itemSalePrice',
                renderer : qtips
            },
            {
                text     : '有效期起',
                sortable : false,
                dataIndex: 'itemValidStart',
                renderer : qtips
            },
            
            {
                text     : '有效期止',
                sortable : false,
                dataIndex: 'itemValidEnd',
                renderer : qtips
            },
            {
                text     : '销售状态',
                sortable : false,
                dataIndex: 'itemApprState',
                renderer : function(value){
              	   var kind = value;
              	   if(kind == '1'){
              		   return '待审批';
              	   }else if(kind == '2'){
              		   return '审批通过';
              	   }else{
              		   return '审批未通过';
              	   }
                 }
            },
            
            {
        		text : '上下架状态',
        		width : 110,
        		sortable : true,
        		dataIndex : 'itemUdState',
        		renderer:function(value){
                	var str = "";
                	if(value == '1'){
                		str = '已上架(生效)';
                	}else if(value=='2'){
                		str = '已下架';
                	}else if(value=='3'){
                		str = '已强制下架';
                	} else if(value=='4') {
                		str = '已替换下架';
                	}
                	return str;
                }
        	},
           
            {
            	// 操作并没有dataIndex，说明仅仅为了显示功能而进行渲染的列
                text: '操作',
                menuDisabled: true,
                sortable: false,
                width: 75,
                renderer: buttonRender,
                align   : 'center'
            }
           
        ];
    // 操作区域
    var dockedItems = [
           {
            xtype: 'toolbar',
            items: [{
                text:'',
                tooltip:'新增',
                minWidth: 30,
                minHeight:30,
                iconCls:'new-ico',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							window.location.href = rootPath + "/item/addItemPage?type=add";
						}
					}
				}
            }]
        }];
    // 多选
    var selModel = Ext.create('Ext.selection.CheckboxModel', {
        listeners: {
            selectionchange: function(sm, selections) {
            }
        }
    });
    //pager
    pager = Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            displayMsg : '显示第 {0} 条到 {1} 条记录,一共 {2} 条'
    });
    // create the Grid
    queryGrid = Ext.create('Ext.grid.Panel', {
    	// s数据仓库的定义
        store: store,
        stateful: true,
        collapsible: false,
        multiSelect: true,
        stateId: 'stateGrid',
        // 列的定义
        columns: columns,
        selModel: selModel,
        dockedItems: dockedItems,
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        /*resizable: {
          handles: 's',
          minHeight: 100
        },*/
        bbar: pager,
        viewConfig: {
            stripeRows: true,
            enableTextSelection: true,
            deferRowRender : false,
            forceFit : true,
            emptyText : "<font class='emptyText'>没有符合条件的记录</font>",
            autoScroll:true,
            scrollOffset:-10
        }
    });
    store.load();
   
}
/*
* 操作按钮
*/

function buttonRender(value, meta, record, rowIndex, colIndex, store) {
    var returnValue = "";
    // record 是一行数据，其中data属性包含了用户的对象
    var itemId = record.data.itemId;
    returnValue += '<em class="modify-ico" title="修改" onclick="modifyItem('+ itemId +')"></em>'+
                   '<em class="del-ico" title="删除" onclick="deleteItem('+ itemId +')"></em>';

    return returnValue;
    
    
  }

//定义删除商品事件
function deleteItem(itemId) {
	  if (confirm('是否要删除该用户')) {
	    $.ajax({
	       url : rootPath + "/item/deleteItem",
	       type : 'GET',
	       dataType : 'json',
	       data : {
	    	   itemId : itemId
	       },
	       success : function(data) {
	         if(data.result=="success"){
	           alert("删除商品成功！");
	         }else{
	           alert(data.msg);
	         }
	         store.load();
	       },
	       error : function(data) {
	         alert("删除商品失败");
	       }
	    });
	  }
	}

//定义修改事件
function modifyItem(itemId){
	window.location.href = rootPath + "/item/addItemPage?type=modify&itemId=" + itemId;
}


/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}





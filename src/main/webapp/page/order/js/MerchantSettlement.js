Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', '../../res/extjs/ux/');
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
    initGrid();
    initCombo();
    initDateTime();
    initBtnFunc();
    
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
	$("#orderStartBox").live("click", function() {
		WdatePicker({
					el : "itemStart",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
	// 结束时间
	$("#orderEndBox").live("click", function() {
		WdatePicker({
					el : "itemEnd",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
}



function initBtnFunc(){
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
				/*goodsId : $("#goodsId").val(),
				orderId : $("#orderId").val(),*/
				shopName : $("#shopName").val(),
				
		};
		store.load();
	});
}
$.ajax({
	//async:false,
	url:rootPath + '/order/getShopInfo',
	data:{} ,
	dataType : 'json',
	type : 'get',
});
function initCombo(){
    var store = Ext.create('Ext.data.Store', {
		// 定义模型
	    fields: ['shopName', 'shopName'],
	    
	    // 通过静态的数据来加载下拉框
	    // 动态加载
	    proxy: {
	    	 // 异步请求
	         type: 'ajax',
	         // 后台服务地址
	         url: rootPath + '/order/getShopName',
	         reader: {
	        	 // 数据解析的格式json
	             type: 'json'
	         },
	       
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'shopNameCombo',
        displayField: 'shopName',
        
        // 字段名对应的值
	    valueField: 'shopName',
        width: 220,
        labelWidth: 130,
        store: store,
        
        
        typeAhead: true,
        listeners:{
        	select : function(value){
        		$("#shopName").val(this.getValue());
        	}
        }
    });
   
}

function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
    	fields: [
    		
    	   {name: 'itemChkCode', type: 'auto'},
    	   {name: 'goodsId', type: 'auto'},
     	   {name: 'orderId', type: 'auto'},
     	   {name: 'useTime', type: 'auto'},
  	       {name: 'shopName', type: 'auto'},
  	       {name: 'itemName', type: 'auto'},
  	       {name: 'itemCode', type: 'auto'},
  	       {name: 'itemActPrice', type: 'auto'},
  	       {name: 'goodsDealFee', type: 'auto'},
  	       {name: 'checkState', type: 'auto'},
  	       {name: 'settlementDate', type: 'auto'},
           
            
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //基于插件的分页
            url: rootPath + '/order/orderList',
            /*actionMethods :{
 		       read: "POST"
 	        },	*/
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
    	{
        	text     : '编号',
        	sortable : false,
        	dataIndex: 'itemChkCode',
        	renderer : qtips
        },
            {
                text     : '消费时间',
                width    : 110,
                sortable : true,
                dataIndex: 'useTime',
                renderer:qtips
            },
            {
                text     : '分店',
                sortable : true,
                dataIndex: 'shopName',
                renderer:qtips
            },
            {
                text     : '商品名称',
                sortable : true,
                dataIndex: 'itemName',
                renderer:qtips
            },
            {
                text     : '商品代码',
                sortable : false,
                dataIndex: 'itemCode',
               
            },
            {
                text     : '商品价格',
                sortable : false,
                dataIndex: 'itemActPrice',
                renderer : qtips
            },
            {
                text     : '结算金额',
                sortable : false,
                dataIndex: 'goodsDealFee',
                renderer : qtips
            },
            {
                text     : '结算状态',
                sortable : false,
                dataIndex: 'checkState',
                renderer : function(value){
                	var kind = value;
                	if(kind=='1'){
                		return '已对账';
                	}else{
                		return '未对账';
                	}
                }
            },
            {
                text     : '结算时间',
                sortable : false,
                dataIndex: 'settlementDate',
                renderer : qtips
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
                tooltip:'',
                minWidth: 30,
                minHeight:30,
                iconCls:'new-ico',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							window.location.href = rootPath + "";
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
    var goodsId = record.data.goodsId;
    returnValue += '';

    return returnValue;
    
  }



/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}





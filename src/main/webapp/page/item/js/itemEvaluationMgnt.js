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
    initBtnFunc();
});

function initBtnFunc(){

	$(".btn-search").click(function(){
		store.proxy.extraParams = {
			
			evaluationStatus:$("#evaluationStatus").val(),
			userNickname:$("#userNickname").val(),
		};
		store.load();
	});
}
/*下拉框：评论状态*/
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
	        	 codeType : "1001"
	         }
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'evaluationStatusCombo',
        displayField: 'codeName',
        
        // 字段名对应的值
	    valueField: 'codeValue',
        width: 100,
        labelWidth: 50,
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value){
        		$("#evaluationStatus").val(this.getValue());
        	}
        }
    });

}

//显示列表
function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
    	fields: [
    		
    	   {name: 'evaluationId', type: 'auto'},
    	   {name: 'userNickname', type: 'auto'},
           {name: 'itemName',  type: 'auto'},
 	       {name: 'itemPicUrl', type: 'auto'},
 	       {name: 'evaluationGrade', type: 'auto'},
 	       {name: 'evaluationContent', type: 'auto'},
 	       {name: 'evaluationPicUrls', type: 'auto'},
 	       {name: 'shopName', type: 'auto'},
 	       {name: 'evaluationTime', type: 'auto'},
 	       {name: 'evaluationStatus', type: 'auto'},
 	        
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //基于插件的分页
            url: rootPath + '/item/getEvaluationList',
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

        {
        	text     : '评论账号',
        	sortable : false,
        	dataIndex: 'userNickname',
        	renderer : qtips
        },
            {
                text     : '商品名称',
                width    : 110,
                sortable : true,
                dataIndex: 'itemName',
                renderer:qtips
            },
            {
                text     : '商品图片',
                sortable : false,
                dataIndex: 'itemPicUrl',
                renderer: function(value){
        			return '<img width=50 height=50  src = '+'/jf-console/'+value+'>';
                }
            },
            {
                text     : '评论分值',
                sortable : false,
                dataIndex: 'evaluationGrade',
                renderer:qtips
            },
            {
                text     : '评论内容',
                sortable : false,
                dataIndex: 'evaluationContent',
               
            },
            {
                text     : '评论图片',
                sortable : false,
                dataIndex: 'evaluationPicUrls',
                renderer: function(value){
        			return '<img width=50 height=50  src = '+'/jf-console/'+value+'>';
                }
            },
            {
                text     : '评论店铺',
                sortable : false,
                dataIndex: 'shopName',
                renderer : qtips
            },
            {
                text     : '评论时间',
                sortable : false,
                dataIndex: 'evaluationTime',
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
							//window.location.href = rootPath + "";
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
    var evaluationId = record.data.evaluationId;
   
    returnValue += '<a href='+rootPath+'/item/getEvaluation?evaluationId=' + evaluationId +'>详情</a>'+
    '<a href='+rootPath+'/item/updateEvaluationStatus?evaluationId=' + evaluationId +'>  屏蔽</a>'
    return returnValue;
    
  }

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}





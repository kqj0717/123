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
    buttonRender();
    deleteSystem();
    initBtnFunc();
    modifySystem();
});

function initBtnFunc(){
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
				mallCatId : $("#mallCatId").val(),
				mallCatCode : $("#mallCatCode").val(),
				mallCatName : $("mallCatName").val(),
				mallCatPicUrl : $("mallCatPicUrl").val(),
				mallCatDesc : $("mallCatDesc").val(),
			
		
		};
		store.load();
	});
}

function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
    	fields: [
    		
    		{name: 'mallCatId', type: 'auto'},
    	    {name: 'mallCatCode', type: 'auto'},
            {name: 'mallCatName',  type: 'auto'},
            {name: 'mallCatPicUrl',  type: 'auto'},
            {name: 'mallCatDesc',  type: 'auto'}
           
            
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //基于插件的分页
            url: rootPath + '/system/selectIcMallCatList',
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
                text     : '类目编码',
                sortable : false,
                dataIndex: 'mallCatId',
                renderer : qtips
            },
            {
                text     : '类目名称',
                width    : 110,
                sortable : true,
                dataIndex: 'mallCatName',
                renderer:qtips
            },
            {
                text     : '图标',
                sortable : false,
                dataIndex: 'mallCatPicUrl',
                renderer: function(value){
        			//return '<img width=50 height=50  src = '+value+'>';
     			return '<img width=50 height=50  src = '+'/jf-console/'+value+'>';
                }
            },
       
            {
                text     : '类目描述',
                sortable : false,
                dataIndex: 'mallCatDesc',
                renderer: function(value){
                	if(value=='undefined'){
                		return "";		
                	}
                	
        			return value;
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
							window.location.href = rootPath + "/system/addSystemPage?type=add";
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
   var state = record.data.mallCatId;
	//var opId = record.data.opId;
    var mallCatId = record.data.mallCatId;
    returnValue += '<em class="modify-ico" title="修改" onclick="modifySystem('+ mallCatId +')"></em>'+
                   '<em class="del-ico" title="删除" onclick="deleteSystem('+ mallCatId +')"></em>';

    return returnValue;
    
    
  }


function deleteSystem(mallCatId) {
	  if (confirm('是否要删除该用户')) {
	    $.ajax({
	       url : rootPath + "/system/deleteSystem",
	       type : 'GET',
	       dataType : 'json',
	       data : {
	    	   mallCatId : mallCatId
	       },
	       success : function(data) {
	         if(data.result=="success"){
	           alert("删除成功！");
	         }else{
	           alert(data.msg);
	         }
	         store.load();
	       },
	       error : function(data) {
	         alert("删除失败");
	       }
	    });
	  }
	}


function modifySystem(mallCatId){
	window.location.href = rootPath + "/system/addSystemPage?type=modify&mallCatId="+mallCatId;
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}





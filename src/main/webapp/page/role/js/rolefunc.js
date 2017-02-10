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
    
    //定义搜索栏的按钮功能
    initBtnFunc();
    
});

function getCheckedNodes(){
	//获取ztree对象
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	//获取到所有选择状态的节点
	var nodes = treeObj.getCheckedNodes(true);
	var checkedNodes = "";
	checkedNodes += nodes[0].roleId;
    for(var i=0;i<nodes.length;i++) {
    	checkedNodes += "," + nodes[i].funcId;
    }
	alert(checkedNodes);
	$.ajax({
		
		url: rootPath + '/role/saveTree',
		type:'post',
		dataType:'json',
		data:{
			checkedNodes:checkedNodes
		},
		success:function(data){
			//alert(data.msg);
		},
		error:function(data){
			alert(data.msg);
		}
	});
	
}

function initBtnFunc(){
	//搜索功能
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
				roleName :$(".query-input").val(),
		};
		store.load();
	});
	//保存节点
	$(".submit-btn").click(function(){
		getCheckedNodes();
		
	});
	
}

//选择菜单
function initGrid(){
    // 显示角色列表
    store = Ext.create('Ext.data.Store', {
        fields: [
        	    
        	    {name: 'roleId', type: 'auto'},
                {name: 'roleName', type: 'auto'},
        ],  
        remoteSort: true,
        pageSize: 10,
      
        proxy: {
            type: 'ajax',
            url: rootPath + '/role/selectSysRoleList',
            actionMethods :{
 		       read: "POST"
 	        },	
            reader: {
               type: 'json',
               root: 'rows',
	         
            }
        }
    });
    // width确定的宽度
    columns = [
           
            {
                text     : '角色名称',
                flex     : 1,
                sortable : false,
                dataIndex: 'roleName',
                renderer : qtips
            }
        ];
    
    //pager
    pager = Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            displayMsg : '显示第 {0} 条到 {1} 条记录,一共 {2} 条'
    });
    
    // create the Grid
    queryGrid = Ext.create('Ext.grid.Panel', {
        store: store,
        stateful: true,
        collapsible: false,
        multiSelect: true,
        stateId: 'stateGrid',
        columns: columns,
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        
        listeners:{
        	select : function(model,record,index,eOpts){
        		var roleId = record.data.roleId;
        		getTree(roleId);
        	}
        },
        
        
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
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
   var roleId = record.data.roleId;
   return '<span onclick="getTree('+roleId+')" title="'+ value +'">' + value + '</span>';    
}

var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "p", "N": "s" }
			},
			data: {
				simpleData: {
					enable: true,
					idKey:'funcId',
					pIdKey:'supFuncId'
				}
			}
			
		};

//获取树
function getTree(roleId){
	
    $.ajax({
    	url : rootPath + '/role/getTree',
    	type: 'post',
    	dataType : 'json',
    	data :{
    		roleId : roleId
    	},
    	success:function(zNodes){
    		if(treeObj !=null){
    			treeObj.destory();
    		}
    		
    		//初始化树，按配置进行渲染
    		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
    		//获取ztree对象
    		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    		//获取到所有选择状态的节点
    		var nodes = treeObj.getCheckedNodes(true);
    		//遍历所有节点，并展开，注意：只作用于父节点，因为子节点无法展开
    		for (var i = 0; i < nodes.length; i++) {
    			treeObj.expandNode(nodes[i], true, true, true);
    		}
    	},
    	
    	error:function(data){
    		alert(data.msg);   
    	}
   
		});
}
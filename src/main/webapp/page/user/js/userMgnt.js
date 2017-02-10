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
			opName : $("#opName").val(),
			opKind : $("#opKind").val(),
	
		};
		store.load();
	});
}
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
        	}
        }
    });
}
function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
    	fields: [
            {name: 'opId',  type: 'auto'},
            {name: 'opName', type: 'auto'},
 	       {name: 'opCode', type: 'auto'},
 	       {name: 'opKind', type: 'auto'},
 	       {name: 'opPic', type: 'auto'},
 	       {name: 'mobileNo', type: 'auto'},
 	       {name: 'emailAdress', type: 'auto'},
 	       {name: 'loginCode', type: 'auto'},
 	       {name: 'lockFlag', type: 'auto'}
           
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //url: rootPath + '/user/selectsysOpList',
            //基于插件的分页
            url: rootPath + '/user/selectsysOpListByPageHelper',
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
                text     : '操作员名称',
                sortable : false,
                dataIndex: 'opName',
                renderer : qtips
            },
            {
                text     : '操作员类型',
                width    : 110,
                sortable : true,
                dataIndex: 'opKind',
                renderer:function(value){
   		        	var str = "";
		        	if(value == '1'){
		        		str = '超级管理员';
		        	}else if(value == '2'){
		        		str = '管理员';
		        	}else if(value == '3'){
		        		str = '普通用户';
		        	}
		        	// 通过renderer重新渲染列
		        	// 场景：
		        	return str;
                  }
            },
            {
                text     : '操作员头像',
                sortable : false,
                dataIndex: 'opPic',
                renderer : qtips
            },
            {
                text     : '手机号',
                sortable : false,
                dataIndex: 'mobileNo',
                renderer : qtips
            },
            {
                text     : '电子邮件地址',
                sortable : false,
                dataIndex: 'emailAdress',
                renderer : qtips
            },
            {
                text     : '登录工号',
                sortable : false,
                dataIndex: 'loginCode',
                renderer : qtips
            },
            {
                text     : '登录密码',
                sortable : false,
                dataIndex: 'loginPasswd',
                renderer : qtips
            },
            {
                text     : '锁定标志',
                sortable : false,
                dataIndex: 'lockFlag',
                renderer : qtips
            },
            
            {
                text     : '数据状态',
                sortable : false,
                dataIndex: 'dataState',
                renderer : qtips
            },
            {
                text     : '修改人',
                sortable : false,
                dataIndex: 'modifier',
                renderer : qtips
            },
            {
                text     : '修改时间',
                sortable : false,
                dataIndex: 'modifyDate',
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
    var dockedItems = [/*{
            xtype: 'toolbar',
            dock: 'bottom',
            ui: 'footer',
            layout: {
                pack: 'center'
            }
        }, */{
            xtype: 'toolbar',
            items: [{
                text:'',
                tooltip:'新建',
                minWidth: 30,
                minHeight:30,
                iconCls:'new-ico',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							//window.location.href = "addUser.jsp";
							window.location.href = rootPath + "/user/addUserPage?type=add";
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
    var opId = record.data.opId;
    returnValue += '<em class="modify-ico" title="修改" onclick="modifyUser('+ opId +')"></em>'+
                    '<em class="del-ico" title="删除" onclick="deleteUser('+ opId +')"></em>';
    
    return returnValue;
}

function deleteUser(opId) {
	  if (confirm('是否要删除该用户')) {
	    $.ajax({
	       url : rootPath + "/user/deleteUser",
	       type : 'GET',
	       dataType : 'json',
	       data : {
	         opId : opId
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

function modifyUser(opId){
	window.location.href = rootPath + "/user/addUserPage?type=modify&opId=" + opId;
}


/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}


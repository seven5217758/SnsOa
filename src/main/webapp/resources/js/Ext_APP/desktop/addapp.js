var AddApp_Menu_item;
var AddApp='AddApp';
var AddApp_grid_menu=Ext.create('Ext.menu.Menu', {
	id : AddApp+'_rightClickCont',
    width: 100,
    height: 100,
    floating:true,
    plain : true, 
    items: [
    	{
    		id:AddApp+'_delete',
        	text: '定制',
        	listeners:{
    			click:function(item,e){
    				id=AddApp_Menu_item.raw.number;
    				Ext.Ajax.request({
			            waitMsg:"请等待。。。正在提交",
			            waitTitle:'请等待',
    				    url: 'addAppToUser',
    				    reader: 'json',
    				    params: {
    				        id: id
    				    },
    				    success: function(response){
    				    	Restaurants2.load();
    				    	Ext.Msg.alert('操作成功','操作成功');
    				    },
    				    failure:function(response){
    				    	Ext.Msg.alert('错误','出错了，可能是您没有登录。或者是数据不合法。');
    				    }
    				})
				}
    		}
    }
    ]
})
Ext.require(['Ext.data.*', 'Ext.grid.*']);
    // wrapped in closure to prevent global vars.
    Ext.define('Restaurant2', {
        extend: 'Ext.data.Model',
        fields:  ['name', 'cuisine','number','isenable']
    });

    var Restaurants2 = Ext.create('Ext.data.Store', {
        storeId: 'restaraunts2',
        model: 'Restaurant2',
        sorters: ['cuisine','name','number','isenable'],
        groupField: 'cuisine',
        proxy: {
        type: 'ajax',
        url : 'UserAppList',
        reader: {
            type: 'json'
        }
    },
    autoLoad: true
    });
    
    var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
        groupHeaderTpl: '应用程序: {name} ({rows.length} 项{[values.rows.length > 1 ? "" : ""]})'
    });


Ext.define('MyDesktop.addapp', {
    extend: 'Ext.window.Window',

    uses: [
        'Ext.tree.Panel',
        'Ext.tree.View',
        'Ext.form.field.Checkbox',
        'Ext.layout.container.Anchor',
        'Ext.layout.container.Border',
        'Ext.ux.desktop.Wallpaper',
        'MyDesktop.WallpaperModel'
    ],
    layout: 'anchor',
    title: '添加应用',
    modal: true,
    width: 640,
    height: 480,
    border: false,
    resizable:false,
    items:{
		xtype:'grid',
        frame: false,
        store: Restaurants2,
        width: 630,
        height: 480,
        border: false,
        features: [groupingFeature],
        columns: [{
            text: '功能名称',
            flex: 1,
            dataIndex: 'name'
        },{
            text: '所属应用程序',
            flex: 1,
            dataIndex: 'cuisine'
        }],
        tbar: [
               { xtype: 'button', text: '刷新应用列表',handler:function(){
            	   Restaurants2.load();
               }}
             ],
         listeners: {
			'itemcontextmenu':function(view,model,item,index,e){
				AddApp_Menu_item=model;
				e.preventDefault(); 
				e.stopEvent();
				if(model.raw.isenable=='false'){
					AddApp_grid_menu.getComponent(AddApp+"_delete").setText('添加程序');
				}else{
					AddApp_grid_menu.getComponent(AddApp+"_delete").setText('卸载程序');
				}
				AddApp_grid_menu.setPosition(e.getX(),e.getY(),false);
				AddApp_grid_menu.show();
			}
		 }
    }
});


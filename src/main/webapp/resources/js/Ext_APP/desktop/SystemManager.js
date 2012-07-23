var SystemManager_Menu_item;
var SystemManager_grid_menu=Ext.create('Ext.menu.Menu', {
			id : 'rightClickCont',
            width: 100,
            height: 100,
            floating:true,
            plain : true, 
            items: [{ 
            		id:'edititem',
                    text: '编辑详细信息',
                    listeners:{
            			click:function(item,e){
            				id=SystemManager_Menu_item.raw.number;
            				Ext.Ajax.request({
            				    url: 'getItemByItemInfo',
            				    reader: 'json',
            				    params: {
            				        id: id
            				    },
            				    success: function(response){
            				    	id=Ext.decode(response.responseText).id;
            				    	appclass=Ext.decode(response.responseText).appclass;
            				    	appfile=Ext.decode(response.responseText).appfile
            				    	appid=Ext.decode(response.responseText).appid
            				    	icon=Ext.decode(response.responseText).icon
            				    	itemname=Ext.decode(response.responseText).itemname
            				    	programid=Ext.decode(response.responseText).programid
            				    	edititemwindow=Ext.create('Ext.window.Window',{
	            				    		id:"edititemwindow",
	            				    		title:'编辑功能配置信息',
	            				    		width:350,
	            				    		height:300,
	            				    		layout:'fit',
	            				    		resizable:false,
	            				    		items:{
	            				    			xtype:'form',
	            				    			border:false,
	            				    			url:'updateItem',
	            				    			layout:'absolute',
	            				    			defaultType: 'textfield',
	            				    			items:[
													{
														x:15,
														y:15,
														width:300,
														value:itemname,
													    name:'itemName',
													    fieldLabel:'功能名称',
													    validator:function(value){
													    		if(value==""&&value==" "){
																	return '不能为空';
													    		}else{
																	return true;
														        }
													    }
													},{
															x:15,
															y:45,
															width:300,
															value:appfile,
														    name:'appFile',
														    fieldLabel:'该功能的js文件',
														    validator:function(value){
														    		if(value==""&&value==" "){
																		return '不能为空';
														    		}else{
																		return true;
															        }
														    }
													    },{
															x:15,
															y:75,
															width:300,
															value:appclass,
														    name:'appClass',
														    fieldLabel:'该功能的class名称',
														    validator:function(value){
														    		if(value==""&&value==" "){
																		return '不能为空';
														    		}else{
																		return true;
															        }
														    }
													    },{
															x:15,
															y:105,
															width:300,
															value:appid,
														    name:'appId',
														    fieldLabel:'该功能的id',
														    validator:function(value){
														    		if(value==""&&value==" "){
																		return '不能为空';
														    		}else{
																		return true;
															        }
														    }
													    },{
															x:15,
															y:135,
															width:300,
															value:icon,
														    name:'icon',
														    fieldLabel:'icon',
														    validator:function(value){
														    		if(value==""&&value==" "){
																		return '不能为空';
														    		}else{
																		return true;
															        }
														    }
													    },{
															x:15,
															y:165,
															width:300,
															value:id,
															hidden:true,
														    name:'id',
														    fieldLabel:'id'
														},{
															x:15,
															y:165,
															width:300,
															value:programid,
															hidden:true,
														    name:'programid',
														    fieldLabel:'programid'
														}
	            				    			       ], buttons:[
		            				       			                {
		            				    			                	text: '保存',
		            								           			formBind: true, //only enabled once the form is valid
		            											        handler: function() {
		            				    			                	 var form = this.up('form').getForm();
		            				    			                	 form.submit({
		            														            waitMsg:"请等待。。。正在提交",
		            														            waitTitle:'请等待',
		            											                    success: function(form, action) {
		            				    			                		 			edititemwindow.close();
		            				    			                		 			Restaurants.load();
		            				    			                		 			Restaurants2.load();
		            											                        Ext.Msg.alert('修改',"修改成功。");
		            											                    },
		            											                    failure: function(form, action) {
		            											                        Ext.Msg.alert('修改失败', "因为一些原因修改失败。请联系软件管理技术人员");
		            											                    }
		            											                });
		            				    			                	}
		            				    			                }
	            				    			                ]
            				    		}
            				    	});
            				    	edititemwindow.show();
            				    }
            				});
            			}
            		}
            },{
            		id:'delete',
                	text: '删除',
                	listeners:{
            			click:function(item,e){
            				id=SystemManager_Menu_item.raw.number;
            				Ext.Ajax.request({
            					waitMsg:"请等待。。。正在提交",
					            waitTitle:'请等待',
            				    url: 'deleteitem',
            				    reader: 'json',
            				    params: {
            				        id: id
            				    },
            				    success: function(response){
            				    	Restaurants.load();
            				    	Restaurants2.load();
            				    	Ext.Msg.alert('成功','删除成功');
            				    },
            				    failure:function(response){
            				    	Ext.Msg.alert('错误','出错了，可能是您没有登录。或者是数据不合法。');
            				    }
            				})
						}
            		}
            },{
            		id:'isenable',
                    text:'禁用该选项',
                    listeners:{
            			click:function(item,e){
            				id=SystemManager_Menu_item.raw.number;
            				Ext.Ajax.request({
            					waitMsg:"请等待。。。正在提交",
					            waitTitle:'请等待',
            				    url: 'enableitem',
            				    reader: 'json',
            				    params: {
            				        id: id
            				    },
            				    success: function(response){
            				    	Restaurants.load();
            				    	Restaurants2.load();
            				    	Ext.Msg.alert('成功','操作成功');
            				    },
            				    failure:function(response){
            				    	Ext.Msg.alert('错误','出错了，可能是您没有登录。或者是数据不合法。');
            				    }
            				})
						}
            		}
            },{
        		id:'enableApp',
                text:'禁用该应用程序',
                listeners:{
        			click:function(item,e){
        				id=SystemManager_Menu_item.raw.number;
        				isEnableApp=SystemManager_Menu_item.raw.isenable;
        				Ext.Ajax.request({
        					waitMsg:"请等待。。。正在提交",
				            waitTitle:'请等待',
        				    url: 'enableApp',
        				    reader: 'json',
        				    params: {
        				        id: id,
        				        enable:isEnableApp
        				    },
        				    success: function(response){
        				    	Restaurants.load();
        				    	Restaurants2.load();
        				    	Ext.Msg.alert('成功','操作成功');
        				    },
        				    failure:function(response){
        				    	Ext.Msg.alert('错误','出错了，可能是您没有登录。或者是数据不合法。');
        				    }
        				})
					}
        		}
        },{
    		id:'addNewItem',
            text:'添加新的功能',
            listeners:{
    			click:function(item,e){
    				id=SystemManager_Menu_item.raw.number;
			    	additemwindow=Ext.create('Ext.window.Window',{
			    		id:"additemwindow",
			    		title:'添加新的功能项',
			    		width:350,
			    		height:300,
			    		layout:'fit',
			    		resizable:false,
			    		items:{
			    			xtype:'form',
			    			border:false,
			    			url:'addnewappitem',
			    			layout:'absolute',
			    			defaultType: 'textfield',
			    			items:[
								{
									x:15,
									y:15,
									width:300,
								    name:'itemName',
								    fieldLabel:'功能名称',
								    validator:function(value){
								    		if(value==""&&value==" "){
												return '不能为空';
								    		}else{
												return true;
									        }
								    }
								},{
										x:15,
										y:45,
										width:300,
									    name:'appFile',
									    fieldLabel:'该功能的js文件',
									    validator:function(value){
									    		if(value==""&&value==" "){
													return '不能为空';
									    		}else{
													return true;
										        }
									    }
								    },{
										x:15,
										y:75,
										width:300,
									    name:'appClass',
									    fieldLabel:'该功能的class名称',
									    validator:function(value){
									    		if(value==""&&value==" "){
													return '不能为空';
									    		}else{
													return true;
										        }
									    }
								    },{
										x:15,
										y:105,
										width:300,
									    name:'appId',
									    fieldLabel:'该功能的id',
									    validator:function(value){
									    		if(value==""&&value==" "){
													return '不能为空';
									    		}else{
													return true;
										        }
									    }
								    },{
										x:15,
										y:135,
										width:300,
									    name:'icon',
									    fieldLabel:'icon',
									    validator:function(value){
									    		if(value==""&&value==" "){
													return '不能为空';
									    		}else{
													return true;
										        }
									    }
								    },{
										x:15,
										y:165,
										width:300,
										value:id,
										hidden:true,
									    name:'id',
									    fieldLabel:'id'
									}
			    			       ], buttons:[
				       			                {
				    			                	text: '保存',
								           			formBind: true, //only enabled once the form is valid
											        handler: function() {
				    			                	 var form = this.up('form').getForm();
				    			                	 form.submit({
														            waitMsg:"请等待。。。正在提交",
														            waitTitle:'请等待',
											                    success: function(form, action) {
				    			                		 			additemwindow.close();
				    			                		 			Restaurants.load();
				    			                		 			Restaurants2.load();
											                        Ext.Msg.alert('添加',"添加成功。");
											                    },
											                    failure: function(form, action) {
											                        Ext.Msg.alert('修改失败', "因为一些原因修改失败。请联系软件管理技术人员");
											                    }
											                });
				    			                	}
				    			                }
			    			                ]
		    		}
		    	});
		    	additemwindow.show();
				}
    		}
    }]
    }); 

Ext.require(['Ext.data.*', 'Ext.grid.*']);
    // wrapped in closure to prevent global vars.
    Ext.define('Restaurant', {
        extend: 'Ext.data.Model',
        fields: ['name', 'cuisine','number','isenable']
    });

    var Restaurants = Ext.create('Ext.data.Store', {
    	pageSize:10000,
        storeId: 'restaraunts',
        model: 'Restaurant',
        sorters: ['cuisine','name','number','isenable'],
        groupField: 'cuisine',
        proxy: {
        type: 'ajax',
        url : 'appList',
        reader: {
            type: 'json'
        }
    },
    autoLoad: true
    });
    
    var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
        groupHeaderTpl: '应用程序: {name} ({rows.length} 项{[values.rows.length > 1 ? "" : ""]})'
    });

    var addNewApps=function(){
    	var addNewApps_window=Ext.create('Ext.window.Window',{
    		id:"addNewApps",
    		title:'添加新的应用',
    		width:350,
    		height:300,
    		layout:'fit',
    		resizable:false,
    		items:{
    			xtype:'form',
    			border:false,
    			url:'addNewApp',
    			layout:'absolute',
    			defaultType: 'textfield',
    			items:[
							{   
							    x: 15,
							    y: 15,
							    width:300,
							    name:'programName',
							    fieldLabel:'程序名称',
							    validator:function(value){
							    		if(value==''||value==' '){
											return '请填写详细名称';
							    		}else{
											return true;
								        }
							    }
							},{   
							    x: 15,
							    y: 45,
							    width:300,
							    name:'index',
							    fieldLabel:'排序号',
							    validator:function(value){
							    		if(isNaN(value)&&value==""&&value==" "){
											return '必须是数字';
							    		}else{
											return true;
								        }
							    }
							},{
								x:15,
								y:75,
								width:300,
							    name:'itemName',
							    fieldLabel:'功能名称',
							    validator:function(value){
							    		if(value==""&&value==" "){
											return '不能为空';
							    		}else{
											return true;
								        }
							    }
							},{
									x:15,
									y:105,
									width:300,
								    name:'appFile',
								    fieldLabel:'该功能的js文件',
								    validator:function(value){
								    		if(value==""&&value==" "){
												return '不能为空';
								    		}else{
												return true;
									        }
								    }
							    },{
									x:15,
									y:135,
									width:300,
								    name:'appClass',
								    fieldLabel:'该功能的class名称',
								    validator:function(value){
								    		if(value==""&&value==" "){
												return '不能为空';
								    		}else{
												return true;
									        }
								    }
							    },{
									x:15,
									y:165,
									width:300,
								    name:'appId',
								    fieldLabel:'该功能的id',
								    validator:function(value){
								    		if(value==""&&value==" "){
												return '不能为空';
								    		}else{
												return true;
									        }
								    }
							    },{
									x:15,
									y:195,
									width:300,
								    name:'icon',
								    fieldLabel:'icon',
								    validator:function(value){
								    		if(value==""&&value==" "){
												return '不能为空';
								    		}else{
												return true;
									        }
								    }
							    }
    			       ],
    			       buttons:[
    			                {
    			                	text: '提交',
				           			formBind: true, //only enabled once the form is valid
							        handler: function() {
    			                	 var form = this.up('form').getForm();
    			                	 form.submit({
										            waitMsg:"请等待。。。正在提交",
										            waitTitle:'请等待',
							                    success: function(form, action) {
    			                		 			addNewApps_window.close();
    			                		 			Restaurants.load();
    			                		 			Restaurants2.load();
							                        Ext.Msg.alert('添加成功',"添加成功。");
							                    },
							                    failure: function(form, action) {
							                        Ext.Msg.alert('添加失败', "因为一些原因添加失败。请联系软件管理技术人员");
							                    }
							                });
    			                	}
    			                }
    			                ]
    		}
    	
    	});
    	addNewApps_window.show();
    }
Ext.define('MyDesktop.SystemManager', {
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
    title: '添加saas应用程序',
    modal: true,
    resizable:false,
    width: 640,
    height: 480,
    border: false,
    items:{
		xtype:'grid',
        frame: true,
        store: Restaurants,
        width: 630,
        height: 480,
        border: false,
        features: [groupingFeature],
        tbar: [
               { xtype: 'button', text: '添加新应用',handler:addNewApps}
             ],
        columns: [{
            text: '功能名称',
            flex: 1,
            dataIndex: 'name'
        },{
            text: '所属应用程序',
            flex: 1,
            dataIndex: 'cuisine'
        }],
        listeners: {
			//右键时出现菜单。
			'beforeitemcontextmenu':function(view,model,item,index,e){
				//alert(model.raw.name);
			},
			'itemcontextmenu':function(view,model,item,index,e){
				SystemManager_Menu_item=model;
				e.preventDefault(); 
				e.stopEvent();

				if(SystemManager_Menu_item.raw.isenable=='false'){
					SystemManager_grid_menu.getComponent("isenable").setText('启用该选项');
					SystemManager_grid_menu.getComponent("enableApp").setText('启用该应用程序');
				}else{
					SystemManager_grid_menu.getComponent("isenable").setText('禁用该选项');
					SystemManager_grid_menu.getComponent("enableApp").setText('禁用该应用程序');
				}
				SystemManager_grid_menu.setPosition(e.getX(),e.getY(),false);
				SystemManager_grid_menu.show();
				//alert(SystemManager_Menu_item.raw.number);
			}
        }
    }
});


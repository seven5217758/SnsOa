
/**
 * 设置人事信息
 */
var HrSetting_grid_menu_Roles_addItemToRoleWindow_menu_store;
var HrSetting_grid_menu_Roles_addItemToRoleWindow_menu_model;
var HrSetting_Menu_item_User;
var HrSetting_Menu_item_Roles;
var HrSetting='HrSetting';
var appSelectId=0;
var HrSetting_grid_menu_Roles_addItemToRoleWindow_menu=Ext.create('Ext.menu.Menu', {
    width: 100,
    height: 100,
    floating:true,
    plain : true,
    items: [{
		id:HrSetting+'_updateUserInfo_role',
    	text: '删除',
    	listeners:{	
			click:function(item,e){
				ids=HrSetting_grid_menu_Roles_addItemToRoleWindow_menu_model.raw.number;
				Ext.Ajax.request({
		            waitMsg:"请等待。。。正在提交",
		            waitTitle:'请等待',
				    url: 'deleteRolesAppItem',
				    reader: 'json',
				    params: {
				        id: ids
				    },
				    success: function(response){
				    	HrSetting_grid_menu_Roles_addItemToRoleWindow_menu_store.load();
				    	Ext.Msg.alert('操作成功','操作成功');
				    },
				    failure:function(response){
				    	Ext.Msg.alert('错误','出错了，可能是您没有登录。或者是数据不合法。');
				    }
				})
			}
		}
    }]
});
var HrSetting_grid_menu_User=Ext.create('Ext.menu.Menu', {
	id : HrSetting+'_rightClickCont_User',
    width: 100,
    height: 100,
    floating:true,
    plain : true, 
    items: [
    	{
    		id:HrSetting+'_updateUserInfo_User',
        	text: '修改公司成员信息',
        	listeners:{
    			click:function(item,e){
    		//'role','account','password','email','fname','lname','number'
    				id=HrSetting_Menu_item_User.raw.number;
    				fname=HrSetting_Menu_item_User.raw.fname;
    				lname=HrSetting_Menu_item_User.raw.lname;
    				account=HrSetting_Menu_item_User.raw.account;
    				password=HrSetting_Menu_item_User.raw.password;
    				email=HrSetting_Menu_item_User.raw.email;
    				updateUserInfo_User_window=Ext.create('Ext.window.Window',{
    					title:'修改公司成员信息',
    	    		   	width:350,
    		    		height:300,
    		    		resizable:false,
    		    		layout:'fit',
    		    		items:
   	    		     {
   	    		    	xtype:'form',
   			    		border:false,
   		    			url:'updateUser',
   		    			layout:'absolute',
   		    			defaultType:'textfield',
   		    			items:[{
   		    				x:15,
							y:15,
							width:300,
						    name:'fname',
						    value:fname,
						    fieldLabel:'姓',
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
						    name:'lname',
						    value:lname,
						    fieldLabel:'名',
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
						    name:'account',
						    value:account,
						    fieldLabel:'账号',
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
						    name:'password',
						    value:password,
						    fieldLabel:'密码',
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
						    name:'email',
						    value:email,
						    fieldLabel:'邮件地址',
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
							hidden:true,
						    name:'id',
						    value:id
   		    			}],
   		    			buttons:[
	       			                {
	    			                	text: '保存',
					           			formBind: true, //only enabled once the form is valid
								        handler: function() {
	    			                	 var form = this.up('form').getForm();
	    			                	 form.submit({
											            waitMsg:"请等待。。。正在提交",
											            waitTitle:'请等待',
								                    success: function(form, action) {
	    			                		 			updateUserInfo_User_window.close();
	    			                		 			HrSetting_Restaurant2_User.load();
								                        Ext.Msg.alert('保存',"保存成功。");
								                    },
								                    failure: function(form, action) {
								                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
								                    }
								                });
	    			                	}
	    			                }
 			                ]
   	    		     }
    				})
    				updateUserInfo_User_window.show();
				}
    		}
    },{
		id:HrSetting+'_rolesToUser_User',
    	text: '给成员分配角色',
    	listeners:{
			click:function(item,e){
    			id=HrSetting_Menu_item_User.raw.number;
		        Ext.define('HrSetting_Restaurant2_RolesToUsers', {
		            extend: 'Ext.data.Model',
		            fields:  ['number', 'name','marks']
		        });
		        HrSetting_Restaurant2_RolesToUsers = Ext.create('Ext.data.Store', {
		        	pageSize:10000,
			            storeId: 'HrSetting_Restaurant2_RolesToUsers',
			            model: 'HrSetting_Restaurant2_RolesToUsers',
			            sorters: ['number', 'name','marks'],
			            proxy: {
			            type: 'ajax',
			            url : 'getCompanyRoles',
			            reader: {
			                type: 'json'
			            }
		        		},
		        	autoLoad: true
		        });
    			UserToRoles_window=Ext.create('Ext.window.Window',{
    				title:'修改公司成员信息',
    			   	width:350,
    	    		height:100,
    	    		resizable:false,
    	    		layout:'fit',
    	    		items:{
    			    	xtype:'form',
    		    		border:false,
	    	   			url:'addRolesToUser',
	    	   			layout:'absolute',
	    	   			defaultType:'textfield',
	    	   			items:[{
		    	   			x:15,
			    			y:10,
			    			xtype:'combobox',
			    			fieldLabel: '选择角色',
			    		    displayField: 'name',
			    		    name:'rid',
			    		    editable:false,
			    		    store: HrSetting_Restaurant2_RolesToUsers,
			    		    valueField: 'number'
	    	   			},
	    	   			{
		    	   			x:15,
			    			y:10,
			    			hidden:true,
			    			value:id,
			    		    name:'id'
	    	   			}],
    		   			buttons:[
 				                {
 			                	text: '保存',
 			           			formBind: true, //only enabled once the form is valid
 						        handler: function() {
 			                	 var form = this.up('form').getForm();
 			                	 form.submit({
 									            waitMsg:"请等待。。。正在提交",
 									            waitTitle:'请等待',
 						                    success: function(form, action) {
 			                		 			UserToRoles_window.close();
 			                		 			HrSetting_Restaurant2_User.load();
 						                        Ext.Msg.alert('保存',"保存成功。");
 						                    },
 						                    failure: function(form, action) {
 						                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
 						                    }
 						                });
 			                	}
 			                }
 		                ]
    			     }
    			});
    			UserToRoles_window.show();
			}
		}
    },{
		id:HrSetting+'_deleteUser_User',
    	text: '删除公司成员',
    	listeners:{
			click:function(item,e){
				id=HrSetting_Menu_item_User.raw.number;
				Ext.Ajax.request({
		            waitMsg:"请等待。。。正在提交",
		            waitTitle:'请等待',
				    url: 'deleteUser',
				    reader: 'json',
				    params: {
				        id: id
				    },
				    success: function(response){
				    	HrSetting_Restaurant2_User.load();
				    	Ext.Msg.alert('操作成功','操作成功');
				    },
				    failure:function(response){
				    	Ext.Msg.alert('错误','出错了，可能是您没有登录。或者是数据不合法。');
				    }
				})
				HrSetting_Restaurant2_User.load();
			}
		}
    },{
		id:HrSetting+'_addUser_User',
    	text: '添加新公司成员',
    	listeners:{
			click:function(item,e){
		addUserInfo_User_window=Ext.create('Ext.window.Window',{
			title:'修改公司成员信息',
		   	width:350,
    		height:300,
    		resizable:false,
    		layout:'fit',
    		items:
		     {
		    	xtype:'form',
	    		border:false,
   			url:'addUser',
   			layout:'absolute',
   			defaultType:'textfield',
   			items:[{
   				x:15,
				y:15,
				width:300,
			    name:'fname',
			    fieldLabel:'姓',
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
			    name:'lname',
			    fieldLabel:'名',
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
			    name:'account',
			    fieldLabel:'账号',
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
			    name:'password',
			    fieldLabel:'密码',
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
			    name:'email',
			    fieldLabel:'邮件地址',
			    validator:function(value){
		    		if(value==""&&value==" "){
						return '不能为空';
		    		}else{
						return true;
			        }
				}
   			}],
   			buttons:[
			                {
		                	text: '保存',
		           			formBind: true, //only enabled once the form is valid
					        handler: function() {
		                	 var form = this.up('form').getForm();
		                	 form.submit({
								            waitMsg:"请等待。。。正在提交",
								            waitTitle:'请等待',
					                    success: function(form, action) {
		                		 			addUserInfo_User_window.close();
		                		 			HrSetting_Restaurant2_User.load();
					                        Ext.Msg.alert('保存',"保存成功。");
					                    },
					                    failure: function(form, action) {
					                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
					                    }
					                });
		                	}
		                }
	                ]
		     }
		})
		addUserInfo_User_window.show();
			}
		}
    }
    ]
})
var HrSetting_grid_menu_Roles=Ext.create('Ext.menu.Menu', {
	id : HrSetting+'_rightClickCont_Roles',
    width: 100,
    height: 100,
    floating:true,
    plain : true, 
    items: [
    	{
    		id:HrSetting+'_addRoles_Roles',
        	text: '添加角色',
        	listeners:{
    			click:function(item,e){
    		var addrolewindow=Ext.create('Ext.window.Window',{
    		   	title:'添加新的角色',
    		   	width:350,
	    		height:300,
	    		resizable:false,
	    		layout:'fit',
	    		items:
	    		     {
	    		    	xtype:'form',
			    			border:false,
		    			url:'addRoles',
		    			layout:'absolute',
		    			defaultType:'textfield',
		    			items:[{
		    				x:15,
							y:15,
							width:300,
						    name:'rolesName',
						    fieldLabel:'角色名',
						    validator:function(value){
					    		if(value==""&&value==" "){
									return '不能为空';
					    		}else{
									return true;
						        }
		    				}
		    			},{
		    				xtype:'textareafield',
		    				x:15,
							y:45,
							width:300,
						    name:'rolesMarks',
						    fieldLabel:'说明',
						    validator:function(value){
					    		if(value==""&&value==" "){
									return true;
					    		}else{
									return true;
						        }
		    				}
		    			}], buttons:[
		       			                {
		    			                	text: '保存',
						           			formBind: true, //only enabled once the form is valid
									        handler: function() {
		    			                	 var form = this.up('form').getForm();
		    			                	 form.submit({
												            waitMsg:"请等待。。。正在提交",
												            waitTitle:'请等待',
									                    success: function(form, action) {
		    			                		 			addrolewindow.close();
		    			                		 			HrSetting_Restaurant2_Roles.load();
									                        Ext.Msg.alert('保存',"保存成功。");
									                    },
									                    failure: function(form, action) {
									                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
									                    }
									                });
		    			                	}
		    			                }
	    			                ]
	    		     }
    	   })
    	   		addrolewindow.show();
				}
    		}
    },{
		id:HrSetting+'_deleteRoles_Roles',
    	text: '删除角色',
    	listeners:{
			click:function(item,e){
				id=HrSetting_Menu_item_Roles.raw.number;
				Ext.Ajax.request({
		            waitMsg:"请等待。。。正在提交",
		            waitTitle:'请等待',
				    url: 'deleteRoles',
				    reader: 'json',
				    params: {
				        id: id
				    },
				    success: function(response){
				    	HrSetting_Restaurant2_Roles.load();
				    	Ext.Msg.alert('操作成功','操作成功');
				    },
				    failure:function(response){
				    	Ext.Msg.alert('错误','出错了，可能是您没有登录。或者是数据不合法。');
				    }
				})
			}
		}
},{
	id:HrSetting+'_permissionToRoles_Roles',
	text: '角色权限管理',
	listeners:{
		click:function(item,e){
			id=HrSetting_Menu_item_Roles.raw.number;
			name=HrSetting_Menu_item_Roles.raw.name;
		    Ext.define('HrSetting_Restaurant2_CompanyRolesAppList', {
		        extend: 'Ext.data.Model',
		        fields:  ['number', 'name']
		    });
		    var HrSetting_Restaurant2_CompanyRolesAppList = Ext.create('Ext.data.Store', {
		    	pageSize:10000,
		    	storeId: 'HrSetting_Restaurant2_CompanyRolesAppList',
		        model: 'HrSetting_Restaurant2_CompanyRolesAppList',
		        sorters: ['number', 'name'],
		        proxy: {
		        type: 'ajax',
		        url : 'getRolesAppsItems?id='+id,
		        reader: {
		            type: 'json'
		        }
		    },
		    autoLoad: true
		    });
			lookuppermission=Ext.create('Ext.window.Window',{
				title:'查看角色权限',
    		   	width:350,
	    		height:300,
	    		resizable:false,
	    		layout:'fit',
	    		items:{

					xtype:'grid',
			        frame: false,
			        store: HrSetting_Restaurant2_CompanyRolesAppList,
			        width: 630,
			        height: 225,
			        border: false,
			        columns: [{
			            text: '功能名称',
			            flex: 1,
			            dataIndex: 'name'
			        }],
			        tbar: [
			               { xtype: 'button', text: '添加新应用权限',handler:function(){
			            	   addnewpermission_addwinodw=Ext.create('Ext.window.Window',{
			            		   title:'添加新应用权限',
			            		   	width:350,
						    		height:200,
						    		resizable:false,
						    		layout:'fit',
						    		items:[
					    		     {
					    		    	xtype:'form',
		 				    			border:false,
						    			url:'addRolesAppItem',
						    		    defaultType: 'textfield',
						    			layout:'absolute',
						    			items:[{
			    							id:'addnewpermission_addwinodw_form_select_combobox1',
							    			x:15,
							    			y:15,
							    			xtype:'combobox',
							    			fieldLabel: '定制的应用程序',
							    		    displayField: 'name',
							    		    editable:false,
							    		    valueField: 'abbr',
							    		    listeners:{
						    					'beforequery':function(obj){
						    							obj.combo.store=Ext.create('Ext.data.Store', {
						    								pageSize:10000,
						    								fields: ['abbr', 'name'],
						    						    proxy: {
						    						        type: 'ajax',
						    						        url : 'getCompanyApps?id='+1,
						    						        reader: {
						    						            type: 'json'
						    						        }
						    							}
						    						});
						    							obj.combo.store.load();
						    					},
				    			                'select':function(p,v){
				    			                	c=this.findParentByType('form');
						    						if(c.getChildByElement('addnewpermission_addwinodw_form_select_combobox')!=null){
						    							c.remove(c.getChildByElement('addnewpermission_addwinodw_form_select_combobox'),true);
						    						}
						    						c.add({
						    							id:'addnewpermission_addwinodw_form_select_combobox',
										    			x:15,
										    			y:45,
										    			xtype:'combobox',
										    			fieldLabel: '程序功能项',
										    		    displayField: 'name',
										    		    name:'itemid',
										    		    editable:false,
										    		    valueField: 'abbr',
										    		    listeners:{
									    					'beforequery':function(obj){
									    							obj.combo.store=Ext.create('Ext.data.Store', {
									    								pageSize:10000,
									    								fields: ['abbr', 'name'],
									    						    proxy: {
									    						        type: 'ajax',
									    						        url : 'getAppItemsList?id='+appSelectId,
									    						        reader: {
									    						            type: 'json'
									    						        }
									    							}
									    							
									    						});
									    							obj.combo.store.load();
									    					}
									    				}
									    			});
						    						appSelectId=p.value;
				    			                }
						    			
						    				}
						    			},{
						    				x:15,
						    				y:75,
						    				hidden:true,
						    				name:'rolesId',
						    				value:id,
							    		    editable:false
						    			}],
						    			buttons:[
					       			                {
					    			                	text: '保存',
									           			formBind: true, //only enabled once the form is valid
												        handler: function() {
					    			                	 var form = this.up('form').getForm();
					    			                	 form.submit({
															            waitMsg:"请等待。。。正在提交",
															            waitTitle:'请等待',
												                    success: function(form, action) {
					    			                					addnewpermission_addwinodw.close();
					    			                		 			HrSetting_Restaurant2_CompanyRolesAppList.load();
												                        Ext.Msg.alert('保存',"保存成功。");
												                    },
												                    failure: function(form, action) {
												                    	
												                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
												                    }
												                });
					    			                	}
					    			                }
				    			                ]
					    		     }
					    		     ]
			            	   });
			            	   addnewpermission_addwinodw.show();
			               	}
			               }
			               ],
			               listeners:{
									'itemcontextmenu':function(view,model,item,index,e){
									HrSetting_grid_menu_Roles_addItemToRoleWindow_menu_model=model;
									HrSetting_grid_menu_Roles_addItemToRoleWindow_menu_store=view.store;
									e.preventDefault(); 
									e.stopEvent();
									HrSetting_grid_menu_Roles_addItemToRoleWindow_menu.setPosition(e.getX(),e.getY(),false);
									HrSetting_grid_menu_Roles_addItemToRoleWindow_menu.show();
								}
							}
			               
				}
			})
			lookuppermission.show();
		}
	}
	}
    ]
})
//用户信息的数据源
Ext.require(['Ext.data.*', 'Ext.grid.*']);
    // wrapped in closure to prevent global vars.
    Ext.define('HrSetting_Restaurant2_User', {
        extend: 'Ext.data.Model',
        fields:  ['role','account','password','email','fname','lname','number']
    });
    var HrSetting_Restaurant2_User = Ext.create('Ext.data.Store', {
    	pageSize:10000,
        storeId: 'HrSetting_Restaurant2_User',
        model: 'HrSetting_Restaurant2_User',
        sorters: ['account','password','email','fname','lname','number','role'],
        groupField: 'role',
        proxy: {
        type: 'ajax',
        url : 'getCompanyUsers',
        reader: {
            type: 'json'
        }
    },
    autoLoad: true
    });
    
    var groupingFeature_User = Ext.create('Ext.grid.feature.Grouping',{
        groupHeaderTpl: '角色: {name} ({rows.length} 项{[values.rows.length > 1 ? "" : ""]})'
    });
    //角色信息的数据源
    Ext.require(['Ext.data.*', 'Ext.grid.*']);
    // wrapped in closure to prevent global vars.
    Ext.define('HrSetting_Restaurant2_Roles', {
        extend: 'Ext.data.Model',
        fields:  ['number', 'name','marks']
    });
    var HrSetting_Restaurant2_Roles = Ext.create('Ext.data.Store', {
    	pageSize:10000,
        storeId: 'HrSetting_Restaurant2_Roles',
        model: 'HrSetting_Restaurant2_Roles',
        sorters: ['number', 'name','marks'],
        proxy: {
        type: 'ajax',
        url : 'getCompanyRoles',
        reader: {
            type: 'json'
        }
    },
    autoLoad: true
    });
    
   
Ext.define('MyDesktop.HrSetting', {
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
    title: '设置人事信息',
    modal: true,
    width: 640,
    height: 480,
    border: false,
    resizable:false,
    items:[{
    	//用户信息列表
		xtype:'grid',
        frame: false,
        store: HrSetting_Restaurant2_User,
        width: 630,
        height: 225,
        border: false,
        features: [groupingFeature_User],
        columns: [{
            text: '姓',
            flex: 1,
            dataIndex: 'fname'
        },{
        	text:'名',
        	flex: 1,
            dataIndex: 'lname'
        },{
            text: '账号',
            flex: 1,
            dataIndex: 'account'
        },{
            text: '密码',
            flex: 1,
            dataIndex: 'password'
        },{
            text: 'email',
            flex: 1,
            dataIndex: 'email'
        },{
            text: '所属角色',
            flex: 1,
            dataIndex: 'role'
        }],
        tbar: [
               { xtype: 'button', text: '刷新',handler:function(){
            	   HrSetting_Restaurant2_User.load();
               }}
             ],
         listeners: {
			'itemcontextmenu':function(view,model,item,index,e){
    			HrSetting_Menu_item_User=model;
				e.preventDefault(); 
				e.stopEvent();
				HrSetting_grid_menu_User.setPosition(e.getX(),e.getY(),false);
				HrSetting_grid_menu_User.show();
			}
		 }
    },{
    	//角色信息列表
		xtype:'grid',
        frame: false,
        store: HrSetting_Restaurant2_Roles,
        width: 630,
        height: 225,
        border: false,
        columns: [{
            text: '角色名',
            flex: 1,
            dataIndex: 'name'
        },{
            text: '备注',
            flex: 1,
            dataIndex: 'marks'
        }],
        tbar: [
               { xtype: 'button', text: '刷新',handler:function(){
            	   HrSetting_Restaurant2_Roles.load();
               	}
               },{
            	   xtype:'button',text:'添加新的角色',handler:function(){
            	   		var addrolewindow=Ext.create('Ext.window.Window',{
            		   	title:'添加新的角色',
            		   	width:350,
			    		height:300,
			    		resizable:false,
			    		layout:'fit',
			    		items:
			    		     {
			    		    	xtype:'form',
 				    			border:false,
				    			url:'addRoles',
				    			layout:'absolute',
				    			defaultType:'textfield',
				    			items:[{
				    				x:15,
									y:15,
									width:300,
								    name:'rolesName',
								    fieldLabel:'角色名',
								    validator:function(value){
							    		if(value==""&&value==" "){
											return '不能为空';
							    		}else{
											return true;
								        }
				    				}
				    			},{
				    				xtype:'textareafield',
				    				x:15,
									y:45,
									width:300,
								    name:'rolesMarks',
								    fieldLabel:'说明',
								    validator:function(value){
							    		if(value==""&&value==" "){
											return true;
							    		}else{
											return true;
								        }
				    				}
				    			}], buttons:[
				       			                {
				    			                	text: '保存',
								           			formBind: true, //only enabled once the form is valid
											        handler: function() {
				    			                	 var form = this.up('form').getForm();
				    			                	 form.submit({
														            waitMsg:"请等待。。。正在提交",
														            waitTitle:'请等待',
											                    success: function(form, action) {
				    			                		 			addrolewindow.close();
				    			                		 			HrSetting_Restaurant2_Roles.load();
											                        Ext.Msg.alert('保存',"保存成功。");
											                    },
											                    failure: function(form, action) {
											                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
											                    }
											                });
				    			                	}
				    			                }
			    			                ]
			    		     }
            	   })
            	   		addrolewindow.show();
               	}
               }
             ],
         listeners: {
	    	'itemcontextmenu':function(view,model,item,index,e){
			HrSetting_Menu_item_Roles=model;
			e.preventDefault(); 
			e.stopEvent();
			HrSetting_grid_menu_Roles.setPosition(e.getX(),e.getY(),false);
			HrSetting_grid_menu_Roles.show();
			}
		 }
    }]
});
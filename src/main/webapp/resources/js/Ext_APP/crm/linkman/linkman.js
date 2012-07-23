
Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander' ]);
Ext.define('Ext.crm.linman.newLinkMen.superior.CustomTrigger', {
    extend: 'Ext.form.field.Trigger',
    alias: 'widget.customtrigger'
});
/**
 * 右键菜单
 */
var linkman_menu_linkmanlist_item;
var linkman_menu_linkmanlist=Ext.create('Ext.menu.Menu',{
	id : 'linkman_menu_linkmanlist_rightClickCont',
    width: 100,
    height: 100,
    floating:true,
    plain : true,
    items: [{ 
		id:'linkman_menu_linkmanlist_deleteitem',
        text: '删除',
        listeners:{
			click:function(item,e){
				id=linkman_menu_linkmanlist_item.raw.id;
				Ext.Ajax.request({
				    url: 'deleteLinkMan',
				    params: {
				        id: id
				    },
				    success: function(response){
				    	linkman_store.load();
				    	Ext.Msg.alert('提示','成功');
				    },
                    failure: function(form, action) {
                        Ext.Msg.alert('提示', "删除失败");
                    }
				});
    		}
    	}
    },{ 
		id:'linkman_menu_linkmanlist_edititem',
        text: '编辑',
        listeners:{
			click:function(item,e){
				updateLinkManwindow=Ext.create("Ext.window.Window",{
					title : '添加新的联系人',
					width : 740,
					height : 480,
					layout:'fit',
					items:{
					id:'addNewLinkManwindow_form',
					xtype:'form',
					url:'updateLinkManInfo',
			        bodyStyle:'padding:5px',
			        width: 735,
			        fieldDefaults: {
			            labelAlign: 'top',
			            msgTarget: 'side'
			        },
			        defaults: {
			            anchor: '100%'
			        },
			        items: [{
			            layout:'column',
			            border:false,
			            items:[{
			                columnWidth:.5,
			                border:false,
			                layout: 'anchor',
			                defaultType: 'textfield',
			                items: [{
			                    fieldLabel: '姓',
			                    name: 'fname',
			                    value:linkman_menu_linkmanlist_item.raw.fname,
			                    anchor:'95%'
			                }, {
			                	id:'addnewlinkman_clientName',
			                    xtype: 'customtrigger',
			                    value:linkman_menu_linkmanlist_item.raw.clientName,
			                    fieldLabel: '客户名称',
			                    editable:false,
			                    anchor:'95%',
			                    onTriggerClick: function() {
			                        linkman_select_clientName=Ext.create("Ext.window.Window",{
			                            height: 300,
			                            width: 600,
			                            title:'选择用户名称',
			    						tbar : [{
			    							text : '添加新客户名称',
			    							tooltip : '添加新客户名称',
			    							iconCls : '',
			    							handler:function (){
			    								linkman_select_clientName_add_new_clientName=Ext.create('Ext.window.Window',{
			    									title : '添加新的客户',
			    									width : 300,
			    									height : 100,
			    									items:{
			    										url:'addClientCompany',
			    										xtype:'form',
				    									width : '290',
				    									height : '65',
			    										defaultType: 'textfield',
			    										items:[
				    										       {
				    										    	    fieldLabel: '输入新客户名称',
				    								                    name: 'name'
				    										       }
			    										       ],
			    										       buttons: [{
			    				    			                	text: '保存',
			    								           			formBind: true, //only enabled once the form is valid
			    											        handler: function() {
			    				    			                	 var form = this.up('form').getForm();
			    				    			                	 form.submit({
			    														            waitMsg:"请等待。。。正在提交",
			    														            waitTitle:'请等待',
			    											                    success: function(form, action) {
			    				    			                		 			linkman_select_clientName_add_new_clientName.close();
			    				    			                		 			linkman_ClientCompany_store.load();
			    											                        Ext.Msg.alert('保存',"保存成功。");
			    											                    },
			    											                    failure: function(form, action) {
			    											                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
			    											                    }
			    											                });
			    				    			                	}
			    				    			                }]
			    										       
			    									}
			    								})
			    								linkman_select_clientName_add_new_clientName.show();
			    							}
			    						}],
			    						items : [ {
			    							xtype : 'grid',
				                            height: 243,
				                            width: 590,
			    							collapsible : false,
			    							animCollapse : true,
			    							iconCls : 'icon-grid',
			    							store :linkman_ClientCompany_store,
			    							columns:[{
			    					            text: "客户名称",
			    					            dataIndex: 'name',
			    					            width: 'auto'
			    					        }],
			    							dockedItems: [{
			    					            dock: 'top',
			    					            xtype: 'toolbar',
			    					            items: {
			    					                width: 400,
			    					                fieldLabel: '查找姓名',
			    					                labelWidth: 80,
			    					                xtype: 'searchfield',
			    					                store: linkman_ClientCompany_store
			    					            }
			    					        }, {
			    					            dock: 'bottom',
			    					            xtype: 'pagingtoolbar',
			    					            store: linkman_ClientCompany_store,
			    					            pageSize: 25,
			    					            displayInfo: true,
			    					            displayMsg: 'Topics {0} - {1} of {2}',
			    					            emptyMsg: 'No topics to display'
			    					        }],
			    					        listeners: {
			    								itemdblclick:function(it,m,item,index,e){
			    									//m.raw.id;
			    									Ext.getCmp("addnewlinkman_clientName").setValue(m.raw.name);
			    									Ext.getCmp("addnewlinkman_clientName_Id_value").setValue(m.raw.id);
			    									linkman_select_clientName.close();
			    								}
			    					        }
			    						}]
			                            
			                        });
			                        linkman_select_clientName.show();
			                    }
			                },{
			                	id:'addnewlinkman_clientName_Id_value',
			                	name:'clientName',
			                	value:linkman_menu_linkmanlist_item.raw.clientNameValue,
			                	hidden:true
			                }]
			            },{
			                columnWidth:.5,
			                border:false,
			                layout: 'anchor',
			                defaultType: 'textfield',
			                items: [{
			                    fieldLabel: '名',
			                    name: 'lname',
			                    value:linkman_menu_linkmanlist_item.raw.lname,
			                    anchor:'95%'
			                },{
			                	xtype:'combobox',
			                    fieldLabel: '性别',
			                    name: 'sex',
			                    value:linkman_menu_linkmanlist_item.raw.sexValue,
			                    store: linkman_sex_store,
			                    displayField: 'name',
			                    valueField: 'abbr',
			                    editable:false,
			                    queryMode: 'local',
			                    anchor:'95%'
			                }]
			            }]
			        },{
			            xtype:'tabpanel',
			            plain:true,
			            activeTab: 0,
			            height:350,
			            defaults:{bodyStyle:'padding:10px'},
			            items:[{
			                title:'地址信息',
			                layout:'column',
			                items: [{
				                defaults: {width: 300,
				            	 labelWidth:120},
			                		width:320,
			                		defaultType: 'textfield',
			                		layout: 'anchor',
			                		 border:false,
			                		items:[
											{
											    fieldLabel: '邮寄地址-国家/地区',
											    value:linkman_menu_linkmanlist_item.raw.sendMailAddressCountry,
											    name: 'sendMailAddressCountry'
											},{
											    fieldLabel: '邮寄地址 - 邮政编码',
											    value:linkman_menu_linkmanlist_item.raw.sendMailAddressCode,
											    name: 'sendMailAddressCode'
											},{
											    fieldLabel: '邮寄地址-州/省',
											    value:linkman_menu_linkmanlist_item.raw.sendMailAddressProvince,
											    name: 'sendMailAddressProvince'
											}, {
											    fieldLabel: '邮寄地址-城市',
											    value:linkman_menu_linkmanlist_item.raw.sendMailAddressCity,
											    name: 'sendMailAddressCity'
											}, {
											    fieldLabel: '邮寄地址 - 街道',
											    value:linkman_menu_linkmanlist_item.raw.sendMailAddressStreet,
											    name: 'sendMailAddressStreet'
											}
			                		       ]
			                	},{
					                defaults: {width: 300,
					            	 labelWidth:120},
				                		width:320,
				                		defaultType: 'textfield',
				                		layout: 'anchor',
				                		 border:false,
				                		items:[
												{
												    fieldLabel: '其他邮寄地址-国家/地区',
												    value:linkman_menu_linkmanlist_item.raw.otherSendMailAddressCountry,
												    name: 'otherSendMailAddressCountry'
												},{
												    fieldLabel: '其他邮寄地址 - 邮政编码',
												    value:linkman_menu_linkmanlist_item.raw.otherSendMailAddressCode,
												    name: 'otherSendMailAddressCode'
												},{
												    fieldLabel: '其他邮寄地址-州/省',
												    value:linkman_menu_linkmanlist_item.raw.otherSendMailAddressProvince,
												    name: 'otherSendMailAddressProvince'
												}, {
												    fieldLabel: '其他邮寄地址-城市',
												    value:linkman_menu_linkmanlist_item.raw.otherSendMailAddressCity,
												    name: 'otherSendMailAddressCity'
												}, {
												    fieldLabel: '其他邮寄地址 - 街道',
												    value:linkman_menu_linkmanlist_item.raw.otherSendMailAddressStreet,
												    name: 'otherSendMailAddressStreet'
												}
				                		       ]
				                	}]
			            },{
			                title:'联系方式和其他信息',
			                autoScroll:true,
			                layout:'column',
			                items: [{
				                defaults: {width: 300,
				            	 labelWidth:120},
			                		width:320,
			                		height:360,
			                		defaultType: 'textfield',
			                		layout: 'anchor',
			                		 border:false,
			                		items:[
											{
											    fieldLabel: 'E-mail',
											    name: 'email',
											    value:linkman_menu_linkmanlist_item.raw.email,
							                    vtype:'email'
											},{
											    fieldLabel: '工作电话',
											    value:linkman_menu_linkmanlist_item.raw.tel,
											    name: 'tel'
											},{
											    fieldLabel: '手机',
											    value:linkman_menu_linkmanlist_item.raw.phone,
											    name: 'phone'
											}, {
											    fieldLabel: '传真',
											    value:linkman_menu_linkmanlist_item.raw.fax,
											    name: 'fax'
											}, {
											    fieldLabel: '家庭电话',
											    value:linkman_menu_linkmanlist_item.raw.HomeTel,
											    name: 'HomeTel'
											}, {
											    fieldLabel: '职位',
											    value:linkman_menu_linkmanlist_item.raw.job,
											    name: 'job'
											}
			                		       ]
			                	},{
					                defaults: {width: 300,
					            	 labelWidth:120},
				                		width:320,
				                		defaultType: 'textfield',
				                		layout: 'anchor',
				                		 border:false,
				                		items:[
												{
												    fieldLabel: '其他电话',
												    value:linkman_menu_linkmanlist_item.raw.otherTel,
												    name: 'otherTel'
												},{
												    fieldLabel: '助理',
												    value:linkman_menu_linkmanlist_item.raw.assistent,
												    name: 'assistent'
												},{
												    fieldLabel: '助理电话',
												    value:linkman_menu_linkmanlist_item.raw.assistentTel,
												    name: 'assistentTel'
												}, {
													xtype: 'datefield',
												    fieldLabel: '出生日期',
												    value:linkman_menu_linkmanlist_item.raw.deteOfbirth,
												    editable:false,
												    name: 'deteOfbirth',
												    validator:function(value){
											    		if(value==""&&value==" "){
															return '不能为空';
											    		}else{
															return true;
												        }
													}
												}, {
								                	xtype:'combobox',
								                    fieldLabel: '客户来源',
								                    name:'clientSource',
								                    value:linkman_menu_linkmanlist_item.raw.clientSourceValue,
								                    store: linkman_sex_clientSource,
								                    displayField: 'name',
								                    valueField: 'id',
								                    editable:false,
								                    anchor:'95%'
								                },{
								                	id:'addnewlinkman_superior',
								                    xtype: 'customtrigger',
								                    value:linkman_menu_linkmanlist_item.raw.superior,
								                    fieldLabel: '所属上级',
								                    editable:false,
								                    onTriggerClick: function() {
									                	addnewlinkman_superior_window=Ext.create('Ext.window.Window',{
									                		title:'选择联系人',
									                		width:'400',
									                		height:'400',
									                		items : [ {
																xtype : 'grid',
										                		width:'388',
										                		height:'370',
																collapsible : false,
																animCollapse : true,
																iconCls : 'icon-grid',
																store :linkman_store,
														        columns:[{
														            // id assigned so we can apply custom css (e.g. .x-grid-cell-topic b { color:#333 })
														            // TODO: This poses an issue in subclasses of Grid now because Headers are now Components
														            // therefore the id will be registered in the ComponentManager and conflict. Need a way to
														            // add additional CSS classes to the rendered cells.
														            text: "姓",
														            dataIndex: 'fname',
														            flex: 1
														        },{
														            text: "名",
														            dataIndex: 'lname',
														            width: 'auto'
														        },{
														            text: "客户名称",
														            dataIndex: 'clientName',
														            width: 'auto'
														        }],
																
																dockedItems: [{
														            dock: 'top',
														            xtype: 'toolbar',
														            items: {
														                width: 350,
														                fieldLabel: '查找姓名',
														                labelWidth: 80,
														                xtype: 'searchfield',
														                store: linkman_store
														            }
														        }, {
														            dock: 'bottom',
														            xtype: 'pagingtoolbar',
														            store: linkman_store,
														            pageSize: 25,
														            displayInfo: true,
														            displayMsg: 'Topics {0} - {1} of {2}',
														            emptyMsg: 'No topics to display'
														        }],											    					        listeners: {
							    								itemdblclick:function(it,m,item,index,e){
							    									//m.raw.id;
								    									Ext.getCmp("addnewlinkman_superior").setValue(m.raw.fname+m.raw.lname);
								    									Ext.getCmp("addnewlinkman_superior_Id_value").setValue(m.raw.id);
								    									addnewlinkman_superior_window.close();
								    								}
								    					        }
															} ]

									                	})
									                	addnewlinkman_superior_window.show();
								                    }
								                },
								                {
								                	id:'addnewlinkman_superior_Id_value',
								                	name:'superior',
								                	value:linkman_menu_linkmanlist_item.raw.superiorValue,
								                	hidden:true
								                },
								                {
								                	name:'id',
								                	value:linkman_menu_linkmanlist_item.raw.id,
								                	hidden:true
								                }
				                		       ]
				                	}]
			            },{
			                cls: 'x-plain',
			                title: '备注',
			                layout: 'fit',
			                items: {
			                    xtype: 'htmleditor',
			                    name: 'marks',
			                    value:linkman_menu_linkmanlist_item.raw.marks,
			                    width:700
			                }
			            }]
			        }],
			        buttons: [{
	                	text: '保存',
	           			formBind: true, //only enabled once the form is valid
				        handler: function() {
	                	 var form = this.up('form').getForm();
	                	 form.submit({
							            waitMsg:"请等待。。。正在提交",
							            waitTitle:'请等待',
				                    success: function(form, action) {
	                		 			updateLinkManwindow.close();
	                		 			linkman_store.load();
				                        Ext.Msg.alert('保存',"保存成功。");
				                    },
				                    failure: function(form, action) {
				                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
				                    }
				                });
	                	}
	                }]}
				});
				updateLinkManwindow.show();
    		}
    	}
    }]   
})
/**
 * 联系人表格的模型
 */
Ext.define('linkman_ForumThread', {
    extend: 'Ext.data.Model',
    fields: [
        'id', 'primaryManId', 'fname', 'lname','sex','sexValue','clientName','clientNameValue','job','phone','tel','email','superior','superiorValue','sendMailAddressCountry','sendMailAddressCode','sendMailAddressProvince','sendMailAddressCity','sendMailAddressStreet','otherSendMailAddressCountry','otherSendMailAddressCode','otherSendMailAddressProvince','otherSendMailAddressCity','otherSendMailAddressStreet','fax','HomeTel','otherTel','assistent','assistentTel','clientSource','clientSourceValue','deteOfbirth','marks'
    ]
});
/**
 * 联系人所属公司查找数据源
 */
Ext.define('linkman_ClientCompany_store', {
    extend: 'Ext.data.Model',
    fields: [
        'id', 'name'
    ]
});
/**
 * 联系人表格的数据源
 */
var linkman_store =Ext.create('Ext.data.Store', {
	pageSize:45,
    storeId: 'linkman_ForumThread',
    model: 'linkman_ForumThread',
    remoteSort: true,
    sorters: [
              'id', 'primaryManId', 'fname', 'lname','sex','sexValue','clientName','clientNameValue','job','phone','tel','email','superior','superiorValue','sendMailAddressCountry','sendMailAddressCode','sendMailAddressProvince','sendMailAddressCity','sendMailAddressStreet','otherSendMailAddressCountry','otherSendMailAddressCode','otherSendMailAddressProvince','otherSendMailAddressCity','otherSendMailAddressStreet','fax','HomeTel','otherTel','assistent','assistentTel','clientSource','clientSourceValue','deteOfbirth','marks'
              ],
    groupField: 'cuisine',
    proxy: {
    type: 'ajax',
    url : 'getLinkManAllInfo',
    reader: {
    	root: 'topics',
    	totalProperty: 'totalCount'
    },
    simpleSortMode: true
},
autoLoad: true
});
/**
 * 联系人所属公司查找数据源
 */
var linkman_ClientCompany_store =Ext.create('Ext.data.Store', {
	pageSize:45,
    storeId: 'linkman_ClientCompany_store',
    model: 'linkman_ClientCompany_store',
    remoteSort: true,
    sorters: [
              'id', 'name'
              ],
    groupField: 'cuisine',
    proxy: {
    type: 'ajax',
    url : 'getAllClientCompany',
    reader: {
    	root: 'topics',
    	totalProperty: 'totalCount'
    },
    simpleSortMode: true
},
autoLoad: true
});
var linkman_sex_clientSource=Ext.create('Ext.data.Store', {
    fields: ['id', 'name'],
    proxy: {
        type: 'ajax',
        url : 'getAllClientSource',
        reader: {
			type: 'json'
        }
	},
	autoLoad: true
});
var linkman_sex_store = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data : [
        {"abbr":"1", "name":"男"},
        {"abbr":"0", "name":"女"}
        //...
    ]
});
Ext.define('MyDesktop.LinkMain', {
	extend : 'Ext.ux.desktop.Module',
	id : 'grid-win',

	init : function() {
		this.launcher = {
			text : '联系人管理',
			iconCls : 'icon-grid',
			handler : this.createWindow,
			scope : this
		};
	},

	createWindow : function() {
		var desktop = this.app.getDesktop();
		var win = desktop.getWindow('grid-win');
		if (!win) {
			win = desktop.createWindow( {//
						id : 'grid-win',
						title : '联系人管理',
						width : 780,
						height : 480,
						iconCls : 'icon-grid',
						layout : 'fit',
						items : [ {
							xtype : 'grid',
							collapsible : false,
							animCollapse : true,
							iconCls : 'icon-grid',
							store :linkman_store,
					        columns:[{
					            // id assigned so we can apply custom css (e.g. .x-grid-cell-topic b { color:#333 })
					            // TODO: This poses an issue in subclasses of Grid now because Headers are now Components
					            // therefore the id will be registered in the ComponentManager and conflict. Need a way to
					            // add additional CSS classes to the rendered cells.
					            text: "姓",
					            dataIndex: 'fname',
					            flex: 1
					        },{
					            text: "名",
					            dataIndex: 'lname',
					            width: 100
					        },{
					            text: "性别",
					            dataIndex: 'sex',
					            width: 70
					        },{
					            text: "客户名称",
					            dataIndex: 'clientName',
					            width: 150
					        }],
							plugins : [ {
								ptype : 'rowexpander',
								rowBodyTpl : [
										'<p><b>所属人:</b> {primaryManId}</p><br>',
										'<p><b>联系人信息:</br></b> 职位:[{job}],手机号:[{phone}],电话号码:[{tel}],email:[{email}],上级：[{superior}],邮寄地址-国家/地区:[{sendMailAddressCountry}],邮寄地址-邮递编码:[{sendMailAddressCode}],邮寄地址-州/省:[{sendMailAddressProvince}],邮寄地址-城市:[{sendMailAddressCity}],邮递地址-街道:[{sendMailAddressStreet}],其他邮寄地址-国家/地区:[{otherSendMailAddressCountry}],其他邮寄地址-邮政编码:[{otherSendMailAddressCode}],其他邮寄地址-州/省:[{otherSendMailAddressProvince}],其他邮寄地址-城市:[{otherSendMailAddressCity}],其他邮寄地址-街道:[{otherSendMailAddressStreet}],E-mail:[{email}],其他电话:[{otherTel}],助理:[{assistent}],助理电话:[{assistentTel}],传真:[{fax}],出生日期:[{deteOfbirth}],客户来源:[{clientSource}]']
							} ],
							dockedItems: [{
					            dock: 'top',
					            xtype: 'toolbar',
					            items: {
					                width: 400,
					                fieldLabel: '查找姓名',
					                labelWidth: 80,
					                xtype: 'searchfield',
					                store: linkman_store
					            }
					        }, {
					            dock: 'bottom',
					            xtype: 'pagingtoolbar',
					            store: linkman_store,
					            pageSize: 25,
					            displayInfo: true,
					            displayMsg: 'Topics {0} - {1} of {2}',
					            emptyMsg: 'No topics to display'
					        }],
					        listeners: {
							//联系人表格右键
								'itemcontextmenu':function(view,model,item,index,e){
									linkman_menu_linkmanlist_item=model;
									e.preventDefault(); 
									e.stopEvent();
									linkman_menu_linkmanlist.setPosition(e.getX(),e.getY(),false);
									linkman_menu_linkmanlist.show();
								}
							} 
						}],
						tbar : [ {
							text : '添加联系人',
							tooltip : '添加新的联系人',
							iconCls : '',
							handler:function (){
							//添加新的联系人按钮的
								addNewLinkManwindow=Ext.create("Ext.window.Window",{
									title : '添加新的联系人',
									width : 740,
									height : 480,
									layout:'fit',
									items:{
									id:'addNewLinkManwindow_form',
									xtype:'form',
									url:'addNewLinkManInfo',
							        bodyStyle:'padding:5px',
							        width: 735,
							        fieldDefaults: {
							            labelAlign: 'top',
							            msgTarget: 'side'
							        },
							        defaults: {
							            anchor: '100%'
							        },
							        items: [{
							            layout:'column',
							            border:false,
							            items:[{
							                columnWidth:.5,
							                border:false,
							                layout: 'anchor',
							                defaultType: 'textfield',
							                items: [{
							                    fieldLabel: '姓',
							                    name: 'fname',
							                    anchor:'95%'
							                }, {
							                	id:'addnewlinkman_clientName',
							                    xtype: 'customtrigger',
							                    fieldLabel: '客户名称',
							                    editable:false,
							                    anchor:'95%',
							                    onTriggerClick: function() {
							                        linkman_select_clientName=Ext.create("Ext.window.Window",{
							                            height: 300,
							                            width: 600,
							                            title:'选择用户名称',
							    						tbar : [{
							    							text : '添加新客户名称',
							    							tooltip : '添加新客户名称',
							    							iconCls : '',
							    							handler:function (){
							    								linkman_select_clientName_add_new_clientName=Ext.create('Ext.window.Window',{
							    									title : '添加新的客户',
							    									width : 300,
							    									height : 100,
							    									items:{
							    										url:'addClientCompany',
							    										xtype:'form',
								    									width : '290',
								    									height : '65',
							    										defaultType: 'textfield',
							    										items:[
								    										       {
								    										    	    fieldLabel: '输入新客户名称',
								    								                    name: 'name'
								    										       }
							    										       ],
							    										       buttons: [{
							    				    			                	text: '保存',
							    								           			formBind: true, //only enabled once the form is valid
							    											        handler: function() {
							    				    			                	 var form = this.up('form').getForm();
							    				    			                	 form.submit({
							    														            waitMsg:"请等待。。。正在提交",
							    														            waitTitle:'请等待',
							    											                    success: function(form, action) {
							    				    			                		 			linkman_select_clientName_add_new_clientName.close();
							    				    			                		 			linkman_ClientCompany_store.load();
							    											                        Ext.Msg.alert('保存',"保存成功。");
							    											                    },
							    											                    failure: function(form, action) {
							    											                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
							    											                    }
							    											                });
							    				    			                	}
							    				    			                }]
							    										       
							    									}
							    								})
							    								linkman_select_clientName_add_new_clientName.show();
							    							}
							    						}],
							    						items : [ {
							    							xtype : 'grid',
								                            height: 243,
								                            width: 590,
							    							collapsible : false,
							    							animCollapse : true,
							    							iconCls : 'icon-grid',
							    							store :linkman_ClientCompany_store,
							    							columns:[{
							    					            text: "客户名称",
							    					            dataIndex: 'name',
							    					            width: 'auto'
							    					        }],
							    							dockedItems: [{
							    					            dock: 'top',
							    					            xtype: 'toolbar',
							    					            items: {
							    					                width: 400,
							    					                fieldLabel: '查找姓名',
							    					                labelWidth: 80,
							    					                xtype: 'searchfield',
							    					                store: linkman_ClientCompany_store
							    					            }
							    					        }, {
							    					            dock: 'bottom',
							    					            xtype: 'pagingtoolbar',
							    					            store: linkman_ClientCompany_store,
							    					            pageSize: 25,
							    					            displayInfo: true,
							    					            displayMsg: 'Topics {0} - {1} of {2}',
							    					            emptyMsg: 'No topics to display'
							    					        }],
							    					        listeners: {
							    								itemdblclick:function(it,m,item,index,e){
							    									//m.raw.id;
							    									Ext.getCmp("addnewlinkman_clientName").setValue(m.raw.name);
							    									Ext.getCmp("addnewlinkman_clientName_Id_value").setValue(m.raw.id);
							    									linkman_select_clientName.close();
							    								}
							    					        }
							    						}]
							                            
							                        });
							                        linkman_select_clientName.show();
							                    }
							                },{
							                	id:'addnewlinkman_clientName_Id_value',
							                	name:'clientName',
							                	hidden:true
							                }]
							            },{
							                columnWidth:.5,
							                border:false,
							                layout: 'anchor',
							                defaultType: 'textfield',
							                items: [{
							                    fieldLabel: '名',
							                    name: 'lname',
							                    anchor:'95%'
							                },{
							                	xtype:'combobox',
							                    fieldLabel: '性别',
							                    name: 'sex',
							                    store: linkman_sex_store,
							                    displayField: 'name',
							                    valueField: 'abbr',
							                    editable:false,
							                    queryMode: 'local',
							                    anchor:'95%'
							                }]
							            }]
							        },{
							            xtype:'tabpanel',
							            plain:true,
							            activeTab: 0,
							            height:350,
							            defaults:{bodyStyle:'padding:10px'},
							            items:[{
							                title:'地址信息',
							                layout:'column',
							                items: [{
								                defaults: {width: 300,
								            	 labelWidth:120},
							                		width:320,
							                		defaultType: 'textfield',
							                		layout: 'anchor',
							                		 border:false,
							                		items:[
															{
															    fieldLabel: '邮寄地址-国家/地区',
															    name: 'sendMailAddressCountry'
															},{
															    fieldLabel: '邮寄地址 - 邮政编码',
															    name: 'sendMailAddressCode'
															},{
															    fieldLabel: '邮寄地址-州/省',
															    name: 'sendMailAddressProvince'
															}, {
															    fieldLabel: '邮寄地址-城市',
															    name: 'sendMailAddressCity'
															}, {
															    fieldLabel: '邮寄地址 - 街道',
															    name: 'sendMailAddressStreet'
															}
							                		       ]
							                	},{
									                defaults: {width: 300,
									            	 labelWidth:120},
								                		width:320,
								                		defaultType: 'textfield',
								                		layout: 'anchor',
								                		 border:false,
								                		items:[
																{
																    fieldLabel: '其他邮寄地址-国家/地区',
																    name: 'otherSendMailAddressCountry'
																},{
																    fieldLabel: '其他邮寄地址 - 邮政编码',
																    name: 'otherSendMailAddressCode'
																},{
																    fieldLabel: '其他邮寄地址-州/省',
																    name: 'otherSendMailAddressProvince'
																}, {
																    fieldLabel: '其他邮寄地址-城市',
																    name: 'otherSendMailAddressCity'
																}, {
																    fieldLabel: '其他邮寄地址 - 街道',
																    name: 'otherSendMailAddressStreet'
																}
								                		       ]
								                	}]
							            },{
							                title:'联系方式和其他信息',
							                autoScroll:true,
							                layout:'column',
							                items: [{
								                defaults: {width: 300,
								            	 labelWidth:120},
							                		width:320,
							                		height:360,
							                		defaultType: 'textfield',
							                		layout: 'anchor',
							                		 border:false,
							                		items:[
															{
															    fieldLabel: 'E-mail',
															    name: 'email',
											                    vtype:'email'
															},{
															    fieldLabel: '工作电话',
															    name: 'tel'
															},{
															    fieldLabel: '手机',
															    name: 'phone'
															}, {
															    fieldLabel: '传真',
															    name: 'fax'
															}, {
															    fieldLabel: '家庭电话',
															    name: 'HomeTel'
															}, {
															    fieldLabel: '职位',
															    name: 'job'
															}
							                		       ]
							                	},{
									                defaults: {width: 300,
									            	 labelWidth:120},
								                		width:320,
								                		defaultType: 'textfield',
								                		layout: 'anchor',
								                		 border:false,
								                		items:[
																{
																    fieldLabel: '其他电话',
																    name: 'otherTel'
																},{
																    fieldLabel: '助理',
																    name: 'assistent'
																},{
																    fieldLabel: '助理电话',
																    name: 'assistentTel'
																}, {
																	xtype: 'datefield',
																    fieldLabel: '出生日期',
																    editable:false,
																    name: 'deteOfbirth',
																    validator:function(value){
															    		if(value==""&&value==" "){
																			return '不能为空';
															    		}else{
																			return true;
																        }
																	}
																}, {
												                	xtype:'combobox',
												                    fieldLabel: '客户来源',
												                    name: 'clientSource',
												                    store: linkman_sex_clientSource,
												                    displayField: 'name',
												                    valueField: 'id',
												                    editable:false,
												                    anchor:'95%'
												                }, {
												                	id:'addnewlinkman_superior',
												                    xtype: 'customtrigger',
												                    fieldLabel: '所属上级',
												                    editable:false,
												                    onTriggerClick: function() {
													                	addnewlinkman_superior_window=Ext.create('Ext.window.Window',{
													                		title:'选择联系人',
													                		width:'400',
													                		height:'400',
													                		items : [ {
																				xtype : 'grid',
														                		width:'388',
														                		height:'370',
																				collapsible : false,
																				animCollapse : true,
																				iconCls : 'icon-grid',
																				store :linkman_store,
																		        columns:[{
																		            // id assigned so we can apply custom css (e.g. .x-grid-cell-topic b { color:#333 })
																		            // TODO: This poses an issue in subclasses of Grid now because Headers are now Components
																		            // therefore the id will be registered in the ComponentManager and conflict. Need a way to
																		            // add additional CSS classes to the rendered cells.
																		            text: "姓",
																		            dataIndex: 'fname',
																		            flex: 1
																		        },{
																		            text: "名",
																		            dataIndex: 'lname',
																		            width: 'auto'
																		        },{
																		            text: "客户名称",
																		            dataIndex: 'clientName',
																		            width: 'auto'
																		        }],
																				
																				dockedItems: [{
																		            dock: 'top',
																		            xtype: 'toolbar',
																		            items: {
																		                width: 350,
																		                fieldLabel: '查找姓名',
																		                labelWidth: 80,
																		                xtype: 'searchfield',
																		                store: linkman_store
																		            }
																		        }, {
																		            dock: 'bottom',
																		            xtype: 'pagingtoolbar',
																		            store: linkman_store,
																		            pageSize: 25,
																		            displayInfo: true,
																		            displayMsg: 'Topics {0} - {1} of {2}',
																		            emptyMsg: 'No topics to display'
																		        }],											    					        listeners: {
											    								itemdblclick:function(it,m,item,index,e){
											    									//m.raw.id;
												    									Ext.getCmp("addnewlinkman_superior").setValue(m.raw.fname+m.raw.lname);
												    									Ext.getCmp("addnewlinkman_superior_Id_value").setValue(m.raw.id);
												    									addnewlinkman_superior_window.close();
												    								}
												    					        }
																			} ]

													                	})
													                	addnewlinkman_superior_window.show();
												                    }
												                },
												                {
												                	id:'addnewlinkman_superior_Id_value',
												                	name:'superior',
												                	hidden:true
												                }
								                		       ]
								                	}]
							            },{
							                cls: 'x-plain',
							                title: '备注',
							                layout: 'fit',
							                items: {
							                    xtype: 'htmleditor',
							                    name: 'marks',
							                    width:700
							                }
							            }]
							        }],
							        buttons: [{
	    			                	text: '保存',
					           			formBind: true, //only enabled once the form is valid
								        handler: function() {
	    			                	 var form = this.up('form').getForm();
	    			                	 form.submit({
											            waitMsg:"请等待。。。正在提交",
											            waitTitle:'请等待',
								                    success: function(form, action) {
	    			                		 			addNewLinkManwindow.close();
	    			                		 			linkman_store.load();
								                        Ext.Msg.alert('保存',"保存成功。");
								                    },
								                    failure: function(form, action) {
								                        Ext.Msg.alert('保存失败', "因为一些原因保存失败。请联系软件管理技术人员");
								                    }
								                });
	    			                	}
	    			                }]}
								});
								addNewLinkManwindow.show();
							}
						} ]
					});
		}
		win.show();
		return win;
	}
});

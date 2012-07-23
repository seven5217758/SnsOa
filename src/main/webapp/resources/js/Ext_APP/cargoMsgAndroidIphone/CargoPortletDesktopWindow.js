/**
 * 移动商店系统的桌面图标窗口
 * 说明：
 * 		主要是用一个iframe显示一个大型portlet窗口
 */
			/**
			 * portlet的列表数据源
			 */
			var cargo_portlet_store =Ext.create('Ext.data.Store', {
				pageSize:45,
			    storeId: 'cargo_portlet_store',
			    remoteSort: true,
			    sorters: [
			              'id','title'
			             ],
			    groupField: 'cuisine',
			    proxy: {
			    type: 'ajax',
			    url : '/portlet/getAppPortletList',
			    reader: {
			    	root: 'topics',
			    	totalProperty: 'totalCount'
			    },
			    simpleSortMode: true
			},
			autoLoad: true
			});
Ext.define('MyDesktop.CargoPortletManager', {
	extend : 'Ext.ux.desktop.Module',
	id : 'CargoPortletManager',

	init : function() {
		this.launcher = {
			text : '网络商店',
			iconCls : 'icon-grid',
			handler : this.createWindow,
			scope : this
		};
	},

	createWindow : function() {
		var desktop = this.app.getDesktop();
		var win = desktop.getWindow('CargoPortletManager_window');
		
		if (!win) {

			win = desktop.createWindow( {
						id : 'CargoPortletManager_window',
						title : '网络商店',
						width : 780,
						height : 480,
						iconCls : 'icon-grid',
						layout : 'fit',
						tools:[{
							//刷新按钮
						    type:'refresh',
						    qtip: '刷新窗口内的内容',
						    // 监听器
						    handler: function(event, toolEl, panel){
						       iframe_Portlets_CargoPortletDesktopWindow.window.location.reload();
						    }
						},{
							//设置本程序的portlet
						    type:'gear',
						    qtip: '设置改程序的信息',
						    // 监听器
						    handler: function(event, toolEl, panel){
				    		   if(userCompanyId){
						       CargoPortlet_setting=Ext.create('Ext.window.Window',{
						    	   title:'设置',
						    	   width:300,
						    	   height : '100%',
						    	   tbar : [{
						    		   text : '添加新的app',
		    							tooltip : '添加新的app到列表',
		    							iconCls : '',
		    							handler:function (){
						    		   		//添加新的app的窗口
						    		   		CargoPortlet_createNewPortlet_window=Ext.create('Ext.window.Window',{
						    		   			title:'添加新的app',
						    		   			width:300,
						    		   			height:300,
						    		   			items:{
						    		   				url:'addClientCompany',
						    		   				xtype:'form',
						    		   				width : '100%',
						    		   				height : '100%',
						    		   				defaultType: 'textfield',
						    		   				items:[
						    		   				       	{
						    		   				       		x:15,
						    		   				       		y:45,
						    		   				       		fieldLabel:'程序名称',
						    		   				       		name:'appname'
						    		   				       	},
						    		   				       	{
						    		   				       		x:15,
						    		   				       		y:75,
						    		   				       		fieldLabel:'url',
						    		   				       		name:'url'
						    		   				       	},
						    		   				       	{
						    		   				       		x:15,
						    		   				       		y:105,
						    		   				       		fieldLabel:'extId',
						    		   				       		name:'extid'
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
 				    			                		 			CargoPortlet_createNewPortlet_window.close();
 				    			                		 			cargo_portlet_store.load();
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
						    		   		CargoPortlet_createNewPortlet_window.show();
						    		   		
						    	   		}
						    	   }],
						    	   height:200,
						    	   items:[
						    	          {
						    	        	  xtype:'grid',
						    	        	  collapsible : false,
											  animCollapse : true,
											  iconCls : 'icon-grid',
										      store :cargo_portlet_store,
										      columns:[{
										    	  	text: "portlet名称",
										            dataIndex: 'title',
										            flex: 1
										      }]
						    	          }
						    	         ]
						       })
						       CargoPortlet_setting.show();
						    }
						}
						}],
						html:"<iframe id='iframe_Portlets_CargoPortletDesktopWindow' frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no src='portle/test'></iframe>"
			});
		}
		win.show();
		return win;
	}
});


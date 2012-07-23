	/**
 * 登陆和注册窗口的js。
 * 同志们加油。
 */
Ext.onReady(function() {
	var validatorCode;//验证码
			 Ext.define("Ext.ux.comboboxtree", {
				 extend : "Ext.form.field.Picker",
				 requires : ["Ext.tree.Panel"],
				 initComponent : function() {
				  var self = this;
				  Ext.apply(self, {
				     fieldLabel : self.fieldLabel,
				     labelWidth : self.labelWidth     
				    });
				  self.callParent();
				 },
			    
				 createPicker : function() {
				  var self = this;
				  var store = Ext.create('Ext.data.TreeStore', {
				     proxy : {
				      type : 'ajax',
				      url : self.storeUrl
				     },
				     sorters : [{
				        property : 'leaf',
				        direction : 'ASC'
				       }, {
				        property : 'text',
				        direction : 'ASC'
				       }],
				     root : {
				      id : self.rootId,
				      text : self.rootText
				     },
				     nodeParameter : self.treeNodeParameter
				    });
				  self.picker = new Ext.tree.Panel({
				     height : 300,
				     autoScroll : true,
				     floating : true,
				     focusOnToFront : false,
				     shadow : true,
				     ownerCt : this.ownerCt,
				     useArrows : true,
				     store : store,
				     rootVisible : false,
				     scroll:"both",
				     autoScroll:true,
				     listeners:{
					  itemclick:function(view,mode,item,index,e){	
				    		com.setValue(mode.raw.text);
				    		self.picker.hide();
						}
			 		 }
				    });
				  self.picker.on({
				   checkchange : function() {
				    var records = self.picker.getView().getChecked(), names = [], values = [];
				    Ext.Array.each(records, function(rec) {
				       names.push(rec.get('text'));
				       values.push(rec.get('id'));
				      });
				    self.setRawValue(values.join(';'));// 隐藏值
				    self.setValue(names.join(';'));// 显示值
				   }
				  });
				  return self.picker;
				 },
				 alignPicker : function() {
				  var me = this, picker, isAbove, aboveSfx = '-above';
				  if (this.isExpanded) {
				   picker = me.getPicker();
				   if (me.matchFieldWidth) {
				    picker.setWidth(me.bodyEl.getWidth());
				   }
				   if (picker.isFloating()) {
				    picker.alignTo(me.inputEl, "", me.pickerOffset);// ""->tl
				    isAbove = picker.el.getY() < me.inputEl.getY();
				    me.bodyEl[isAbove ? 'addCls' : 'removeCls'](me.openCls
				      + aboveSfx);
				    picker.el[isAbove ? 'addCls' : 'removeCls'](picker.baseCls
				      + aboveSfx);
				   }
				  }
				 }
				});
			 var com =Ext.create("Ext.ux.comboboxtree", {
			     id:'name',
			     name:'companyType',
			     hiddenName:'hiddenName',
			     storeUrl : "companyTypeTree",
			     width : 270,
			     fieldLabel : '企业类别',
			     rootId : '0',
			     rootText : '企业类别',
			     x:15,
			     y:45,
			     emptyText:"请选择指定类型",
			     invalidText:"sss",
			     treeNodeParameter : 'pNode',
			     editable:false,
			     validator:function(value){
	           		if(value==''||value=='请选择指定类型'){
						return '请填写详细名称';
	           		}else{
						return true;
			        }
			           
	           }
			    }); 

			 //注册布局
			var register=Ext.create('Ext.form.Panel',{
					id:'register',
					title:"注册",
					url:'register',
					standardSubmit:true,//设置非ajax提交
					defaultType: 'textfield',
					layout:'absolute',
					autoScroll : true,
					items:[
					       {   
						       x: 15,
					           y: 15,
					           name:'companyName',
					           fieldLabel: '公司名称',
					           validator:function(value){
					           		if(value==''||value==' '){
										return '请填写详细名称';
					           		}else{
										return true;
							        }
							           
					           }
						       
						   },
						   com,
						   {	
							   x:15,
							   y:75,
							   name:'corporateMan',
							   fieldLabel:'法人代表',
							   validator:function(value){
									if(value==''||value==' '){
										return '请填写详细法人代表';
									}else{
										return true;
									}
								}
							},
							   {	
								x:15,
								y:105,
								name:'registerfund',
								fieldLabel:'注册资金',
								validator:function(value){
									if(value==''||value==' '){
										return '请填写详细注册资金';
									}else{
										return true;
									}
								}
							},
							{
								id:'tongyi',
								x:15,
								y:140,
					            fieldLabel : '同意用户',
					            defaultType: 'checkboxfield',
								xtype: 'fieldcontainer',
								items: [
											{
												id:'agree',
											    boxLabel  : '同意',
											    name      : 'agree',
											    inputValue: '1'
											},
											{
												width:200,
												html:'<a href=\'javascript:alert("ssss")\'>查看用户协议</a>'
											}
								        ]
							},{
								x:15,
							 	y:185,
							 	name:'identifyingCode',
							 	fieldLabel:'验证码',
							 	validateOnChange:true,
							 	validator:function(value){
								//ajax去判断验证码是否正确。不正确则无法提交
									 Ext.Ajax.request({	
									    url: 'ajaxIdentifyingCode',
									    params: {
										 identifyingCode: value
									    },
									    success: function(response){
									    	validatorCode= response.responseText;
									    }
									});
									 if(validatorCode=='true'){
										 return true;
									 }else{
										 return "验证码输入错误";
									 }
									
								}
							},{
								x:15,
							 	y:215,
							 	html:'<div id="identifying"><img src="image"/></div>'
							},
							{
								x:200,
								y:215,
								xtype:'button',
								text:'点击更换验证码',
								handler:function(){
									document.getElementById("identifying").innerHTML="<img src='image'/>";
								}
							}
						  ],
						  buttons:[{
						        		text: '重置',
							        		handler: function() {
							            		this.up('form').getForm().reset();
							        		}
						  		   		},
						           		{
						           			text: '提交',
						           			formBind: true, //only enabled once the form is valid
									        handler: function() {
						  		   			if(register.getComponent('tongyi').getComponent('agree').checked){
									            var form = this.up('form').getForm();
									                form.submit(
											            {
												            waitMsg:"请等待。。。正在提交",
												            waitTitle:'请等待',
									                    success: function(form, action) {
									                       Ext.Msg.alert('注册成功',"注册成功。。正在跳转。请稍后。。。");
									                    },
									                    failure: function(form, action) {
									                        Ext.Msg.alert('注册失败', "因为一些原因注册失败。请联系软件管理技术人员");
									                    }
									                });
									            }else{
							  		   				Ext.Msg.alert('信息未填写全',"还未选择同意用户协议勾选项。");
							  		   			}
						  		   			}
								        }
						           ]
						 
				})
			 //登陆tab的布局
			var login=Ext.create('Ext.form.Panel',{
				id:'login',
				title: '登陆',
			    bodyPadding: 5,
			    // The form will submit an AJAX request to this URL when submitted
			    url: 'login',
			    // Fields will be arranged vertically, stretched to full width
			    layout: 'anchor',
			    defaults: {
			        anchor: '100%'
			    },
			    // The fields
			    defaultType: 'textfield',
			    standardSubmit:true,//设置非ajax提交
			    items: [{
			        fieldLabel: '账号',
			        name: 'account',
			        validator:function(value){
				        if(value==''||value==' '){
							return '请填写账号';
						}else{
							return true;
						}
			   		}
			    },{
			        fieldLabel: '密码',
			        name: 'password',
			        inputType:'password',
				        validator:function(value){
				    		if(value==''||value==' '){
							return '请填写密码';
						}else{
							return true;
						}
			    	}
			    },{
				 	name:'identifyingCode',
				 	fieldLabel:'验证码',
				 	validateOnChange:true,
				 	validator:function(value){
					//ajax去判断验证码是否正确。不正确则无法提交
						 Ext.Ajax.request({	
						    url: 'ajaxIdentifyingCode',
						    params: {
							 identifyingCode: value
						    },
						    success: function(response){
						    	validatorCode= response.responseText;
						    }
						});
						 if(validatorCode=='true'){
							 return true;
						 }else{
							 return "验证码输入错误";
						 }
						
					}
				},{
				 	html:'<div id="identifying1"><img src="image"/></div>'
				},
				{
					xtype:'button',
					text:'点击更换验证码',
					handler:function(){
						document.getElementById("identifying1").innerHTML="<img src='image'/>";
					}
				}],

			    // Reset and Submit buttons
			    buttons: [{
			        text: '重置',
			        handler: function() {
			            this.up('form').getForm().reset();
			        }
			    }, {
			        text: '提交',
			        formBind: true, //only enabled once the form is valid
			        disabled: true,
			        handler: function() {
			            var form = this.up('form').getForm();
			            if (form.isValid()) {
			                form.submit(
					            {
						            waitMsg:"请等待。。。正在提交",
						            waitTitle:'请等待',
						            success: function(form, action) {
					            	Ext.Msg.alert('登陆成功',action.result.url);
			                    },
			                    failure: function(form, action) {
			                        Ext.Msg.alert('登录失败', "失败");
			                    }
			                });
			            }
			        }
			    }]
				})
			//主布局，选项卡。
			 var loginwindowPanel=Ext.create("Ext.tab.Panel",{  
					 region:'center',
				    //margins:'3 3 3 0',
				    activeTab:0,
				    //defaults:{autoScroll:true},
				    items:[
						  login,
						  register
						  ]
	            });
			//主窗口
			var mainwindow=Ext.create('Ext.window.Window', {
				 title:'互联时代-企业综合应用系统',
				    closable:false,       //这里要注意不要写成 closeable（多一个e）
				    width:340,
				    draggable:false,
				    resizable:false,
				    height:350,
				    border:false,
				    layout:'border',
				    tools:[
				           	{
				        	   	id:'closable',
						    	 handler:function(){
				           			mainwindow.hide();
						    	 }
				           	}
				           ],
				    items:[loginwindowPanel]
				});
				mainwindow.show();
		 })
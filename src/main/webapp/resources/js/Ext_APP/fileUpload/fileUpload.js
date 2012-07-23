	//上传文件的布局
			 var filepanl=Ext.create('Ext.form.Panel', {
				    width: 400,
				    bodyPadding: 10,
				    frame: true, 
				    items: [{
				        xtype: 'filefield',
				        name: 'businessLicenselmage',
				        fieldLabel: '上传营业执照图片',
				        labelWidth: 100,
				        msgTarget: 'side',
				        anchor: '100%',
				        buttonText: '选择',
				        validator:function(value){
							if(value==''||value==' '){
								return '请上传营业执照';
							}else{
								if(value.split('.').length==2){
									format=value.split('.')[value.split('.').length-1];
									if(format=='jpg'){	
										return true;
									}else{
										return "你上传的文件格式是错误的必须是jpg"
									}
								}
								return "请确定您上传的文件格式是错误的。";
							}
						}
				    }],
				   
				    buttons: [{
				        formBind: true, //only enabled once the form is valid
				        disabled: true,
				        text: '上传',
				        handler: function() {
				            var form = this.up('form').getForm();
				            if(form.isValid()){
				                form.submit({
				                    url: 'uploadImage',
				                    waitMsg: '正在上传请稍后',
				                    success: function(fp, o) {
				                        Ext.Msg.alert('上传营业执照成功');
				                    }
				                });
				            }
				        }
				    }]
				});
/**
			  * 上传文件的ext窗口
			  */
			var upfilewindow=Ext.create('Ext.window.Window', {
				 	title:'上传文件',
				    closable:false,       //这里要注意不要写成 closeable（多一个e）
				    width:400,
				    border:false,
				    tools:[{
				    	 id:'closable1',
				    	 handler:function(){
				    		upfilewindow.hide();
				    	 }
				    }],
				    items:[filepanl]
				})
				/*{
							 	x:15,
							 	y:140,
							 	width:300,
							 	xtype:'button',
							 	text:'点击上传营业执照图片(有利于快速审核)',
							 	handler:function(){
									upfilewindow.show();
								}
							},*/
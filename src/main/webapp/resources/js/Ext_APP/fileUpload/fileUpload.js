	//�ϴ��ļ��Ĳ���
			 var filepanl=Ext.create('Ext.form.Panel', {
				    width: 400,
				    bodyPadding: 10,
				    frame: true, 
				    items: [{
				        xtype: 'filefield',
				        name: 'businessLicenselmage',
				        fieldLabel: '�ϴ�Ӫҵִ��ͼƬ',
				        labelWidth: 100,
				        msgTarget: 'side',
				        anchor: '100%',
				        buttonText: 'ѡ��',
				        validator:function(value){
							if(value==''||value==' '){
								return '���ϴ�Ӫҵִ��';
							}else{
								if(value.split('.').length==2){
									format=value.split('.')[value.split('.').length-1];
									if(format=='jpg'){	
										return true;
									}else{
										return "���ϴ����ļ���ʽ�Ǵ���ı�����jpg"
									}
								}
								return "��ȷ�����ϴ����ļ���ʽ�Ǵ���ġ�";
							}
						}
				    }],
				   
				    buttons: [{
				        formBind: true, //only enabled once the form is valid
				        disabled: true,
				        text: '�ϴ�',
				        handler: function() {
				            var form = this.up('form').getForm();
				            if(form.isValid()){
				                form.submit({
				                    url: 'uploadImage',
				                    waitMsg: '�����ϴ����Ժ�',
				                    success: function(fp, o) {
				                        Ext.Msg.alert('�ϴ�Ӫҵִ�ճɹ�');
				                    }
				                });
				            }
				        }
				    }]
				});
/**
			  * �ϴ��ļ���ext����
			  */
			var upfilewindow=Ext.create('Ext.window.Window', {
				 	title:'�ϴ��ļ�',
				    closable:false,       //����Ҫע�ⲻҪд�� closeable����һ��e��
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
							 	text:'����ϴ�Ӫҵִ��ͼƬ(�����ڿ������)',
							 	handler:function(){
									upfilewindow.show();
								}
							},*/
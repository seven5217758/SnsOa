/**
 * @class Ext.app.Portal
 * @extends Object
 * A sample portal layout application class.
 */
// TODO: Fill in the content panel -- no AccordionLayout at the moment
// TODO: Fix container drag/scroll support (waiting on Ext.lib.Anim)
// TODO: Fix Ext.Tool scope being set to the panel header
// TODO: Drag/drop does not cause a refresh of scroll overflow when needed
// TODO: Grid portlet throws errors on destroy (grid bug)
// TODO: Z-index issues during drag

Ext.define('Ext.app.Portal', {

    extend: 'Ext.container.Viewport',

    uses: ['Ext.app.PortalPanel', 'Ext.app.PortalColumn'],

    getTools: function(){
        return [{
            xtype: 'tool',
            type: 'gear',
            handler: function(e, target, panelHeader, tool){
                var portlet = panelHeader.ownerCt;
                portlet.setLoading('Working...');
                Ext.defer(function() {
                    portlet.setLoading(false);
                }, 2000);
            }
        }];
    },
    initComponent: function(){
        var content = '<div class="portlet-content">'+Ext.example.shortBogusMarkup+'</div>';

        Ext.apply(this, {
            id: 'app-viewport',
            layout: {
                type: 'border',
                padding: '0 5 5 5' // pad the layout from the window edges
            },
            items: [{
                xtype: 'container',
                region: 'center',
                layout: 'border',
                items: [{
                    id: 'app-options',
                    title: '菜单项',
                    region: 'west',
                    animCollapse: true,
                    width: 200,
                    minWidth: 150,
                    maxWidth: 400,
                    split: true,
                    collapsible: true,
                    layout: 'accordion',
                    layoutConfig:{
                        animate: true
                    },
                    items: [{
                        title:'商店管理',
                        autoScroll: true,
                        border: false,
                        items   : [
                          {
                            xtype: 'button',
                            text : 'My Button',
                            handler:function(){
                        	  test=Ext.create('Ext.window.Window',{
                        		 title:"ssssss",
                        		 id:'sadasdasdasdasds'	
                        	  });
                        	  test.show();
                          	}
                          }
                        ],
                        iconCls: 'nav'
                    },{
                        title:'留言管理',
                        border: false,
                        autoScroll: true,
                        iconCls: 'settings'
                    }]
                },{
                	 xtype:'tabpanel',
                     region: 'center',
                	 items:[{
                		 title:'1',
                		 xtype: 'portalpanel',
		                    items: [{
		                    	//列数
		                        id: 'col-1',
		                        items: [{
		                            id: 'portlet-1',
		                            title: 'Grid Portlet',
		                            tools: this.getTools(),
		                            //items: Ext.create('Ext.app.GridPortlet'),
		                            listeners: {
		                                'close': Ext.bind(this.onPortletClose, this)
		                            }
		                        },{
		                            id: 'portlet-2',
		                            title: 'Portlet 2',
		                            tools: this.getTools(),
		                            //html: content,
		                            listeners: {
		                                'close': Ext.bind(this.onPortletClose, this)
		                            }
		                        }]
		                    },{//列数
		                        id: 'col-2',
		                        items: [{
		                            id: 'portlet-3',
		                            title: 'Portlet 3',
		                            tools: this.getTools(),
		                            //html: '<div class="portlet-content">'+Ext.example.bogusMarkup+'</div>',
		                            listeners: {
		                                'close': Ext.bind(this.onPortletClose, this)
		                            }
		                        }]
		                    }]
                	 	}]
                }]
                
            }]
        });
        this.callParent(arguments);
    },

    onPortletClose: function(portlet) {
        this.showMsg('"' + portlet.title + '" was removed');
    },

    showMsg: function(msg) {
        var el = Ext.get('app-msg'),
            msgId = Ext.id();

        this.msgId = msgId;
        el.update(msg).show();

        Ext.defer(this.clearMsg, 3000, this, [msgId]);
    },

    clearMsg: function(msgId) {
        if (msgId === this.msgId) {
            Ext.get('app-msg').hide();
        }
    }
});

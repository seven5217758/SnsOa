

Ext.define('MyDesktop.App', {
    extend: 'Ext.ux.desktop.App',

    requires: LoadClass,

    init: function() {
        // custom logic before getXYZ methods get called...

        this.callParent();

        // now ready...
    },

    getModules : function(){
        return newObj;
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            //cls: 'ux-desktop-black',

            contextMenuItems: [
                { text: '改变背景', handler: me.onSettings, scope: me }
            ],

            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: userMain
            }),

            wallpaper: extDesktopSrc+'wallpapers/Blue-Sencha.jpg',
            wallpaperStretch: false
        });
    },

    // config for the start menu
    getStartConfig :getStartConfig,

    getTaskbarConfig: function () {
        var ret = this.callParent();

        return Ext.apply(ret, {
            quickStart:quickStart,
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('注销', '你是否要注销?',function(optional){
        	if(optional=='yes'){
        		document.location.href="logout";
        	}
        });
    },
    onSelectApp: function () {
    	var app = new MyDesktop.addapp({
            desktop: this.desktop
        });
        app.show();
    },
    onSystemManager: function () {
    	var SysManager = new MyDesktop.SystemManager({
            desktop: this.desktop
        });
    	SysManager.show();
    },
    onSettings: function () {
        var dlg = new MyDesktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    },
    onHrSetting:function(){
    	var HrSetting = new MyDesktop.HrSetting({
            desktop: this.desktop
        });
    	HrSetting.show();
    }
});


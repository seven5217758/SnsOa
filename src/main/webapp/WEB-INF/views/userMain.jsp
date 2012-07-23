<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.SnsOa.system.util.SystemSessionAttreName"%>
<%@page import="com.SnsOa.entity.Users"%>
<%@page import="com.SnsOa.entity.Program"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%
		String context = request.getContextPath();
		String linkman=((Users)request.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)).getFname()==null?"未设置姓名":((Users)request.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)).getLname();
		Users user=(Users)request.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
		long userCompanyId=user.getCompanyId();
		//是不是主账户，是不是注册时系统生成的账户
		boolean isMainAccount=user.isMainAccount();
		//该账户是否被用户自己修改定义过
		boolean isChangerAccount=user.isChangerAccount();
	    String jsFile=(String)request.getAttribute(SystemSessionAttreName.JSFILE);
	    String loadClass=(String)request.getAttribute(SystemSessionAttreName.LOADCLASS);
	    String newObj=(String)request.getAttribute(SystemSessionAttreName.NEWOBJ);
	    String userMain=(String)request.getAttribute(SystemSessionAttreName.USERMAIN);
	%>
<html>
<head>
<title>互联时代-企业应用平台</title>
<link rel="stylesheet" type="text/css"
			href="<%=context%>/resources/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
			src="<%=context%>/resources/js/ext/ext-all.js"></script>
		<script type="text/javascript"
			src="<%=context%>/resources/js/jquery-1.6.3.js"></script>

	<script type="text/javascript" src="<%=context%>/resources/js/ext/bootstrap.js"></script> 
	<script type="text/javascript"
			src="<%=context%>/resources/js/ext/ux/RowExpander.js"></script>
	<script type="text/javascript"
			src="<%=context%>/resources/js/ext/ux/form/SearchField.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=context%>/resources/js/Ext_APP/desktop/css/desktop.css" />
    <script type="text/javascript">
    Ext.tip.QuickTipManager.init();
    </script>
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/js/Module.js"></script> 
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/js/Video.js"></script>
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/js/Wallpaper.js"></script> 
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/js/FitAllLayout.js"></script> 
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/js/StartMenu.js"></script> 
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/js/TaskBar.js"></script> 
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/js/ShortcutModel.js"></script>   
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/js/Desktop.js"></script> 
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/js/App.js"></script> 
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/WallpaperModel.js"></script>
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/Settings.js"></script>
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/HrSetting.js"></script>
	<%=jsFile %>
	<script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/addapp.js"></script>
	<%=userCompanyId==1?"<script type='text/javascript' src='"+context+"/resources/js/Ext_APP/desktop/SystemManager.js'></script>":"" %> 
	<script type="text/javascript">
	document.oncontextmenu=function(){return false;};
	var extDesktopSrc='<%=context%>/resources/js/Ext_APP/desktop/';
	var loginMan='<%=linkman%>';
	var userCompanyId=<%=userCompanyId==1?"true":"false"%>;
	//开始菜单的配置
	 function getStartConfig() {
	        var me = this, ret = me.callParent();
	        return Ext.apply(ret, {
	            title: '登录人：'+loginMan,
	            iconCls: 'user',
	            height: 300,
	            toolConfig: {
	                width: 100,
	                items: [<%if(isMainAccount){%>{
                        text:'人事配置',
                        iconCls:'logout',
                        handler: me.onHrSetting,
                        scope: me
                    	},'-',
                    	<%}%>
	                    {
	                        text:'设置',
	                        iconCls:'settings',
	                        handler: me.onSettings,
	                        scope: me
	                    },
	                    '-',
	                    {
	                        text:'注销',
	                        iconCls:'logout',
	                        handler: me.onLogout,
	                        scope: me
	                    }<%if(isMainAccount){%>,'-',{
	                    	text:'添加应用',
	                    	iconCls:'logout',
	                    	handler:me.onSelectApp,
	                    	scope:me
	                    }<%}%><%=userCompanyId==1?",'-',{text:'系统管理',iconCls:'logout',handler:me.onSystemManager,scope:me}":""%>
	                ]
	            }
	        });
	    }
	//桌面图标
	var userMain=[
	          	<%=userMain%>
                  ];
    //加载的类
    var LoadClass= [
                    <%=loadClass%>
                    'Ext.window.MessageBox',
                    'Ext.ux.desktop.ShortcutModel'
                ];
    //实例化所需类别
    var newObj=[
                //new MyDesktop.VideoWindow(),
                <%=newObj%>
            ];
    //快速启动栏的图标
    var quickStart=[];
    </script>
	<script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/desktop/App.js"></script>

    <script type="text/javascript">

        Ext.Loader.setPath({
            'Ext.ux.desktop': 'js',
            MyDesktop: ''
        });

        Ext.require('MyDesktop.App');
        var myDesktopApp;
        Ext.onReady(function () {
            myDesktopApp = new MyDesktop.App();
        });
    </script>
</head>
<body>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
  <head>

    
    <title>My JSP 'cargoMag.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
	String context = request.getContextPath();
	%>
	<link rel="stylesheet" type="text/css" href="<%=context%>/resources/js/ext/resources/css/ext-all.css" />
    <link rel="stylesheet" type="text/css" href="<%=context%>/resources/js/Ext_APP/cargoMsgAndroidIphone/portal.css" />

<script type="text/javascript"
			src="<%=context%>/resources/js/ext/ext-all.js"></script>
<script type="text/javascript"
			src="<%=context%>/resources/js/jquery-1.6.3.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/cargoMsgAndroidIphone/classes/PortalColumn.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/cargoMsgAndroidIphone/classes/PortalDropZone.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/cargoMsgAndroidIphone/classes/PortalPanel.js"></script>
	<script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/cargoMsgAndroidIphone/classes/Portlet.js"></script>
    <!-- shared example code -->
    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/cargoMsgAndroidIphone/examples.js"></script>

    <script type="text/javascript" src="<%=context%>/resources/js/Ext_APP/cargoMsgAndroidIphone/portal.js"></script>
    <script type="text/javascript">
        Ext.Loader.setPath('Ext.app', 'classes');
		
        Ext.require([
            'Ext.layout.container.*',
            'Ext.resizer.Splitter',
            'Ext.fx.target.Element',
            'Ext.fx.target.Component',
            'Ext.window.Window',
            'Ext.app.Portlet',
            'Ext.app.PortalPanel',
            'Ext.app.Portlet',
            'Ext.app.PortalDropZone'
        ]);
		
        Ext.onReady(function(){
            Ext.create('Ext.app.Portal');
        });
    </script>
  </head>
  
  <body>
  <span id="app-msg" style="display:none;"></span>
  </body>
</html>

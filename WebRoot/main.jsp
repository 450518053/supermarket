<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%
	String path = request.getContextPath();
%>
<link rel="stylesheet" href="${ctx}/jslib/easyui1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/jslib/easyui1.4.2/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/jslib/easyui1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/Popup.js"></script>
<script type="text/javascript" src="${ctx}/main.js"></script>
<script type="text/javascript" src="${ctx}/jslib/easyui1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript">
 var web_Path='<%=path%>';
 var qx='${user_permissions}';
</script>
</head>
<body id="bodys" class="easyui-layout" style="overflow-y: hidden;"
	scroll="no">
<script type="text/javascript">
ajaxLoading();
</script>
    
	<noscript>
		<div style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">抱歉，请开启脚本支持！</div>
	</noscript>
	<div>
		<div class="self-head">
	    	<div class="operator">
	        	<img src="${ctx}/style/images/nav_pre.gif"></img> 欢迎  <span> ${userinfo.dept} </span> [ <span>${userinfo.userName}</span> ] 登陆系统
	    	</div>  
      		<div class="operate">
	        	<a href=""><img src="${ctx}/style/images/nav_help.png" /></a> <span id="clock"></span>
      		</div>
    	</div>
	</div>
    
	<div id="frm2" region="south"
		style="line-height: 20px; overflow: hidden; text-align: center; display: none;">
		后台管理
	</div>
	<div data-options="region:'west',split:true,border:false"  title="导航菜单"
		style="width: 230px; overflow: hidden;" icon="icon-redo">
		<div id="menu" class="easyui-accordion" fit="true" border="false"
			style="display: none;"></div>
	</div>
		<div data-options="region:'center',split:true,border:false" id="mainPanle" style="background: #eee; overflow: hidden;width:100%;">
			<div id="tabs" style="display: none;" class="easyui-tabs" fit="true">
				<div title="欢迎页面"
					style="padding: 20px; text-align: center; vertical-align: bottom;"
					id="home">
					<h2>
						<img src="" alt="welcome..." style="vertical-align: middle;"/>
					</h2>
				</div>
			</div>
		</div>
</body>
</html>

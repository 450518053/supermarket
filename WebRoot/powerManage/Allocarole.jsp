<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int exemInCount=0;//统计个数
int inCount=3;//多少个换行变量
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'haverole.jsp' starting page</title>
    
  </head>
  
  <body>
  <div class="easyui-panel" minimizable="false" maximizable="false"
		icon="icon-save"
		style="width: 780px; height:300px; ">
           <form id="roleForm" name="roleForm">
					<fieldset class="easyui-fieldset">
				    	<legend>未选-用户列表</legend>
				    		<table>
						<tr>
							<td>
							<c:forEach items="${haveMap}" var="v" varStatus="vv">
								<input id="lack_role_${vv.count}" name="lack_role_id_${vv.count}" type="checkbox" />${v.user_name}</td>
							</c:forEach>
						</tr>
					</table>
				    </fieldset>
				    <fieldset>
				    	<legend>已选-用户列表</legend>
				    		<table >
						<tr>
							 <c:forEach items="${lackMap}" var="v" varStatus="vv">
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input id="have_role_${vv.count}" name="add_role_id_${vv.count}" type="checkbox"/>${v.user_name}</td>
							</c:forEach> 
						</tr>
					</table>
				    </fieldset>
				</form>
    </div>
    <%-- <!-- 分配用户 角色缺少的用户和拥有的用户 -->
	<div id="role" class="easyui-window" title="修改角色"
		collapsible="false" minimizable="false" maximizable="false"
		icon="icon-save"
		style="width: 650px; height:200px; padding: 5px;
        background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<form id="roleForm" name="roleForm">
					<fieldset class="easyui-fieldset">
				    	<legend>未选-用户列表</legend>
				    		<table style="width: 500px;margin-left: auto;margin-right: auto;">
						<tr>
							<td>
							<c:forEach items="${haveMap}" var="v">
								<input id="lack_role" name="add_role_id" style="width: 100px;" type="checkbox" /></td>
							</c:forEach>
							
						</tr>
					</table>
				    </fieldset>
				    <fieldset>
				    	<legend>已选-用户列表</legend>
				    		<table style="width: 500px;margin-left: auto;margin-right: auto;">
						<tr>
							<c:forEach items="${lackMap}" var="v">
								<td><input id="have_role" name="add_role_id" style="width: 200px;"
								type="checkbox"/>${v.user_name}</td>
							</c:forEach>
							
						</tr>
					</table>
				    </fieldset>
				</form>
			</div>
		</div>
	</div>  --%>
  </body>
</html>

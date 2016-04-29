<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'rolemanage.jsp' starting page</title>
<link rel="stylesheet" href="../jslib/easyui1.4.2/themes/icon.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../jslib/easyui1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../style/css/commer.css" type="text/css"></link>
<script type="text/javascript" src="../jslib/easyui1.4.2/jquery.min.js"></script>
<script src="../jslib/easyui1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/Rolemanage.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript">
	var basepath='<%=basePath%>';
	var userid = '${userinfo.userId}';
</script>
<style type="text/css">

table td{
	padding-left: 20px;
}

</style>
</head>
<body id="cc"
	onload="$('#insert').window('close');$('#update').window('close');">
	<div>
		<form id="searchForm">
			<table style="margin-left: 90px;font-size: 13px;font-family: 微软雅黑;">
				<tr>
					<td>角色代码：<input type="text" id="role_id" name="role_id"
						class="easyui-textbox" style="width:180px"  validType="length[0,20]"/>
					</td>
					<td>角色名称：<input type="text" id="role_name" name="role_name"
						class="easyui-textbox" style="width:180px"  validType="length[0,20]"/>
					</td>
				</tr>
			</table>
		</form>
		<div style="margin-top: 20px;margin-bottom: 20px;margin-left: 280px;">
			<a href="#" onclick="queryRole();" class="button blue"
				>查询</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"
				onclick="resetCriteria();" class="button blue"
				>重置</a>
		</div>
	</div>
	<table id="query_data" style="overflow: hidden;"></table>
	<div id="tb">
		<a href="#" onclick="addRole();" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">新增</a> <a href="#"
			onclick="modifyRole();" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a> <a href="#"
			onclick="logOutRole();" class="easyui-linkbutton"
			data-options="iconCls:'icon-no',plain:true">注销</a> <a href="#"
			onclick="activateRole();" class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true">启用</a> <a href="#"
			onclick="assignPermission();" class="easyui-linkbutton"
			data-options="iconCls:'icon-lock',plain:true">分配权限</a> <a href="#"
			onclick="assignUser();" class="easyui-linkbutton"
			data-options="iconCls:'icon-man',plain:true">分配用户</a>
	</div>
	<!--新增角色窗口-->
	<div id="insert" class="easyui-window" title="新增角色" collapsible="false"
		minimizable="false" maximizable="false"
		style="width: 650px; height:200px; padding: 15px;
        background: #fafafa;position:relative;left: 10px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<form id="insertForm" name="insertForm">
					<table
						style="width: 500px;font-size: 13px;font-family: 微软雅黑;margin-left: auto;margin-right: auto;">
						<tr>
							<td align="center">角色代码： <input id="add_role_id"
								name="add_role_id" style="width: 200px;" type="text"
								required="true" missingMessage="此项为必填" class="easyui-textbox" />
							</td>
						</tr>
						<tr>
							<td align="center">角色名称： <input id="add_role_name"
								name="add_role_name" style="width: 200px;" type="text"
								required="true" missingMessage="此项为必填" class="easyui-textbox" />
							</td>
						</tr>
					</table>
				</form>
				<div region="south" border="false"
					style="text-align: center; height: 30px; line-height: 30px;">
					<a id="btnEp" class="button blue" onclick="sure_but();">确定</a> <a
						id="btnCancel" class="button blue" onclick="cancel_but();">重置</a>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改角色 -->
	<div id="update" class="easyui-window" title="新增角色" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 650px; height:200px; padding: 5px;
        background: #fafafa;position:relative;left: 10px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<form id="updateForm" name="updateForm">
					<table
						style="width: 500px;font-size: 13px;font-family: 微软雅黑;margin-left: auto;margin-right: auto;">
						<tr>
							<td align="center">角色代码： <input id="upd_role_id"
								name="upd_role_id" style="width: 200px;" type="text" readonly="readonly" 
								/></td>

						</tr>
						<tr>
							<td align="center">角色名称： <input id="upd_role_name"
								name="upd_role_name" style="width: 200px;" type="text"/></td>
						</tr>
					</table>
				</form>
				<div region="south" border="false"
					style="text-align: center; height: 30px; line-height: 30px;">
					<a id="btnEp" class="button blue"  onclick="upd_sure_but();">确定</a> <a
						id="btnCancel" class="button blue" onclick="upd_cancel_but();">重置</a>
				</div>
			</div>
		</div>
	</div>
	<div id="add_dialog"></div>
	<div id="update_userAuto"></div>

</body>
</html>

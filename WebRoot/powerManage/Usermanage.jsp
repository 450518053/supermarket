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
<title>用户管理</title>
<link rel="stylesheet" href="../jslib/easyui1.4.2/themes/icon.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../jslib/easyui1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../style/css/commer.css" type="text/css"></link>
<script type="text/javascript" src="../jslib/easyui1.4.2/jquery.min.js"></script>
<script src="../jslib/easyui1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/Usermanage.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript">
		var basepath="<%=basePath%>";
	var userid = '${userinfo.userId}';
</script>
<style type="text/css">

table td{
	padding-left: 20px;
}

</style>
</head>

<body>
	<div
		style="width: 75%;padding-bottom: 20px;">
		<form id="queryForm">
			<table style="margin-left: 20px;margin-top: 20px;font-size: 13px;font-family: 微软雅黑;">
				<tr>
					<td>用户ID：<input type="text" id="user_id" name="user_id"
						style="width:150px;" class="easyui-textbox" validType="length[0,20]">
					</td>
					<td>姓名：<input type="text" id="user_name" name="user_name"
						style="width:150px;" class="easyui-textbox" validType="length[0,20]">
					</td>
					<td>用户类型： <select id="user_type" name="user_type"
						style="width:150px;" class="easyui-combobox" editable="false">
							<option value="">请选择</option>
							<option value="0">操作员</option>
							<option value="1">管理员</option>
					</select></td>
				</tr>
			</table>
		</form>
		&nbsp;
		<div style="margin-left: 290px;">
			<a href="#" onclick="query();" class="button blue"
				>查询</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="#" onclick="resetForm();" class="button blue">重置</a>
		</div>
		<div id="tb">
			<a href="#" onclick="modifyUser();" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true">修改</a> <a href="#"
				onclick="resetPassword();" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">重置密码</a> <a href="#"
				onclick="unsubscribe();" class="easyui-linkbutton"
				data-options="iconCls:'icon-no',plain:true">注销</a> <a href="#"
				onclick="activateUser();" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">启用</a> <a href="#"
				onclick="assignRole();" class="easyui-linkbutton"
				data-options="iconCls:'icon-man',plain:true">分配角色</a>
		</div>
	</div>

	<div id="show_data" style="width:75%;padding-bottom: 20px;"></div>

	<div id="add_dialog" ></div>

	<div id="all_dialog"></div>


</body>
</html>

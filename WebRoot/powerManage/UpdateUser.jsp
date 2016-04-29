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
<title>My JSP 'manager.jsp' starting page</title>
<script type="text/javascript">
	function subUser(){
		   $.ajax({
					 url:'<%=basePath%>' + 'user/update',
			type : "POST",
			data : {
				user_id : $('#userid').val(),
				user_name : $('#username').val(),
				dept : $('#dept').val()
			},
			success : function(data) {
				if (data.trim() == 'success') {
					$('#add_dialog').dialog('close');
					$('#query_data').datagrid('reload');
				} else {
					jQuery.messager.alert('温馨提示', '修改失败,请重试!');
				}
			}
		});
	}
	
</script>

<style type="text/css">
table tr td:nth-child(1) {
	text-align: right;
}
</style>
<div style="margin-top: 50px;">
	<form id="userForm">
		<table cellspacing="10px" align="center"
			style="font-size: 13px;font-family: 微软雅黑;line-height: 30px;">
			<tr>
				<td>用户ID：</td>
				<td><input id="userid" name="userid" value="${bean.userId}"
					readonly style="width: 200px;" class="easyui-textbox" /></td>
			</tr>

			<tr>
				<td>姓名：</td>
				<td><input id="username" name="username"
					value="${bean.userName}" style="width: 200px;"
					class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>部门：</td>
				<td><input id="dept" name="dept" value="${bean.dept}"
					style="width: 200px;" class="easyui-textbox" /></td>
			</tr>
			
		</table>
		<div style="margin-left: 280px;margin-top: 10px;">
			<input id="sure" type="button" value="确定" onclick="subUser();"
				class="button blue">
		</div>
	</form>
</div>
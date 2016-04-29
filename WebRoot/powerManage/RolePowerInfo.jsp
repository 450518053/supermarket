<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>查询角色权限</title>
<link rel="stylesheet" href="./easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="./easyui/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet" href="./wxBackGround/css/commer.css"
	type="text/css"></link>
<script src="./easyui/jquery.min.js"></script>
<script src="./easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#RolePowerInfo_list")
				.datagrid(
						{
							title : "客户信息列表",
							pageSize : 10,//默认选择的分页是每页5行数据
							pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
							nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
							striped : true,//设置为true将交替显示行背景。
							toolbar : "#tb",//在添加 增添、删除、修改操作的按钮要用到这个
							url : "./ParamTypeValueController/getparamTypeValue_fenye.do",//url调用Action方法
							loadMsg : "数据装载中......",
							singleSelect : true,//为true时只能选择单行
							fitColumns : true,
							rownumbers : true,
							remoteSort : false,
							onClickRow : function(rowIndex, rowData) {//单击事件
							},
							columns : [ [
									{
										field : 'paramTypeValueName',
										align : 'center',
										title : '参数类型值名称',
										width : 120
									},
									{
										field : 'paramTypeValueAlias',
										align : 'center',
										title : '参数类型值别名',
										width : 120
									},
									{
										field : 'id',
										align : 'center',
										title : '参数类型',
										width : 120,
										formatter : function rowformater(value,
												row, index) {
											return "<a href='./wxBackGround/PowerManage/RoleModify.jsp'>修改</a>|<a href='#' target='iframe'>删除</a>";
										},
										hidden : false
									}, {
										field : 'paramTypeId',
										align : 'center',
										title : '参数类型id',
										width : 120,
										hidden : true
									} ] ],
							pagination : true,//分页
							rownumbers : true
						//行数
						});
		var p = $("#RolePowerInfo_list").datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10   
			pageList : [ 5, 10, 15, 20 ],//可以设置每页记录条数的列表   
			beforePageText : '第',//页数文本框前显示的汉字   
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		});

	});
</script>
</head>

<body>
	<div>
		<form>
			<table class="roletable">
				<tr>
					<td>请选择角色：</td>
					<td><select class="easyui-combobox roleselect">
							<option>角色1</option>
							<option>角色2</option>
					</select></td>
				</tr>
			</table>
			<div class="rolediv">
				<input type="submit" value="查询"  class="button blue"/>&nbsp;<input
					type="reset" value="重置"  class="button blue"/>&nbsp;<input type="button"
					value="导出"  class="button blue"/>
			</div>
		</form>
		<table id="RolePowerInfo_list" class="easyui-datagrid">
		</table>
	</div>
</body>
</html>

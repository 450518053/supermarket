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

<title>查询分组</title>
<link rel="stylesheet" href="./easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="./easyui/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet" href="./wxBackGround/css/commer.css"
	type="text/css"></link>
<script src="./easyui/jquery.min.js"></script>
<script src="./easyui/jquery.easyui.min.js"></script>
<script src="./validate/validate.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		var editIndex = undefined;
		$("#GroupInfo_list").datagrid(
				{
					title : "分组列表",
					pageSize : 10,//默认选择的分页是每页5行数据
					pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
					nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
					striped : true,//设置为true将交替显示行背景。
					toolbar : "#tb",//在添加 增添、删除、修改操作的按钮要用到这个
					url : "./manage/groupManagerController/getGroup.do",//url调用Action方法
					loadMsg : "数据装载中......",
					singleSelect : false,//为true时只能选择单行
					fitColumns : true,
					rownumbers : true,
					remoteSort : false,
					onClickRow : function(rowIndex, rowData) {//单击事件
						if (editIndex ==undefined) {
							$("#GroupInfo_list").datagrid("beginEdit",rowIndex);
							editIndex=rowIndex;
						}else{
							 $("#GroupInfo_list").datagrid("endEdit",editIndex);
							 $("#GroupInfo_list").datagrid("beginEdit",rowIndex);
							 editIndex=rowIndex;
						}
						
					},
					columns : [ [ {
						field : 'groupName',
						align : 'center',
						title : '组名',
						width : 120,
						editor : {
							type : "textbox"
						}
					}, {
						field : 'groupId',
						align : 'center',
						title : 'ID',
						width : 120,
						hidden : true
					} ] ],
					pagination : true,//分页
					rownumbers : true
				//行数
				});
		var p = $("#GroupInfo_list").datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10   
			pageList : [ 5, 10, 15, 20 ],//可以设置每页记录条数的列表   
			beforePageText : '第',//页数文本框前显示的汉字   
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	});
</script>
</head>

<body>
	<div class="groupinfodiv" align="center" style="margin-left:60px">	
		<div class="groupinfodiv3" >
			<table id="GroupInfo_list" class="easyui-datagrid"
				style="min-height: 400px;"></table>
		</div>
		<div id="tb">
			<a  href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save',plain:true"
				onclick="updateGroup()">保存</a>
		</div>

	</div>
	<script type="text/javascript">
		
	<%-- 修改组的名称 --%>
		function updateGroup() {
			var row = $("#GroupInfo_list").datagrid("getSelected");
			//var row = $("#GroupInfo_list").datagrid("getSelections");
			var groupInfo = "";
			$("#GroupInfo_list").datagrid("acceptChanges");
			if (row == null) {
				alert("请修改后再提交!")
				return;
			} else {
			 var groupId  =  row.groupId;
			 var groupName  =  row.groupName;
			}
			$.ajax({
						type : "get",
						traditional : true,
						url : "./manage/groupManagerController/updateGroup.do?",
						dataType : "json",
						data:{
						groupId:groupId,
						groupName:groupName
						},
						success : function(data) {
							if (data.message > 0) {
								alert("修改成功！")
							} else {
								alert("修改失败！");
							}
						},
						error : function() {
							alert("系统异常");
						}
					});
		}

		
	</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>客户群分组</title>
<link rel="stylesheet" href="./easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="./easyui/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet" href="./wxBackGround/css/commer.css"
	type="text/css"></link>
<script src="./easyui/jquery.min.js"></script>
<script src="./easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../validate/validate.js"></script>
<script type="text/javascript">
	$(function() {
		$("#download").click(function() {
			document.location.href = "<%=basePath%>manage/customer/export.do?cityId="+$("#cityId").combobox("getValue")+"&cusGroupId="+$("#cusGroupId").combobox("getValue")+"&isSubscribe="+$("#isSubscribe").combobox("getValue");
		});
		var groupData = [];
		$("#cusGroupId").find("option").each(function(){
			if($(this).html()!="所有组"){
				groupData.push({"text":$(this).html(),"value":$(this).val()});
			}
		});
		var cityData = [];
		$("#cityId").find("option").each(function(){
			cityData.push({"text":$(this).html(),"value":$(this).val()});
		});
		if("${cityId}" != "1-1"){
			$("#cityId").combobox("readonly",true);
		}
		var editIndex = undefined;
		$("#CusGroupInfo_list").datagrid(
				{
					title : "客户信息列表",
					iconCls : "icon-edit",
					pageSize : 10,//默认选择的分页是每页5行数据
					pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
					nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
					striped : true,//设置为true将交替显示行背景。
					toolbar : "#tb",//在添加 增添、删除、修改操作的按钮要用到这个
					url : "manage/customer/showList.do",//url调用Action方法
					loadMsg : "数据装载中......",
					singleSelect : true,//为true时只能选择单行
					fitColumns : true,
					rownumbers : true,
					remoteSort : false,
					onClickRow : function(rowIndex, rowData) {//单击事件
						if (editIndex ==undefined) {
							$("#CusGroupInfo_list").datagrid("beginEdit",rowIndex);
							editIndex=rowIndex;
						}else{
							$("#CusGroupInfo_list").datagrid("endEdit",editIndex);
							$("#CusGroupInfo_list").datagrid("beginEdit",rowIndex);
							editIndex=rowIndex;
						}
						
					},
					columns : [ [ {
						field : 'wxNickname',
						align : 'center',
						title : '微信昵称',
						width : 100
					}, {
						field : 'cusBirthday',
						align : 'center',
						title : '客户生日',
						width : 100
					}, {
						field : 'cusMobile',
						align : 'center',
						title : '手机号码',
						width : 100
					}, {
						field : 'cusGroupId',
						align : 'center',
						title : '所在组',
						width : 100,
						formatter : function(value, row, index) {
							if (value == 0) {
								return "默认组";
							}
							for(var i = 0; i < groupData.length; i++){
								if (groupData[i].value == value) {
							            return groupData[i].text;
							    }
							}
						},
						editor : {
							type : "combobox",
							options : {
								data: groupData,
								valueField: "value", 
								textField: "text" ,
								required:true
							}
						}
					}, {
						field : 'cusCity',
						align : 'center',
						title : '绑定城市',
						width : 50,
						formatter : function(value, row, index) {
							for(var i = 0; i < cityData.length; i++){
								if (cityData[i].value == value) {
							            return cityData[i].text;
							    }
							}
							return "";
						}
					}, {
						field : 'isSubscribe',
						align : 'center',
						title : '是否关注',
						width : 50,
						formatter : function(value, row, index) {
							if (value == 'Y') {
								return "是";
							} else if (value == 'N') {
								return "否";
							}
						}
					}, {
						field : 'openId',
						align : 'center',
						hidden : true
					} ] ],
					onBeforeEdit : function(index, row) {
						row.editing = true;
						updateActions(index);
					},
					onAfterEdit : function(index, row) {
						row.editing = false;
						updateActions(index);
					},
					onCancelEdit : function(index, row) {
						row.editing = false;
						updateActions(index);
					},
					onEndEdit : function(index, row, changes) {
						if (typeof(changes.cusGroupId) != "undefined") {
							$("#modifyStr").val($("#modifyStr").val() + row.openId + ":" + changes.cusGroupId + ",");
						}
					//	$.ajax({
					//			type:"get",
					//			url:"./manage/customer/modify.do?openId="+row.openId+"&cusGroupId="+row.cusGroupId,
					//			dataType:"json",
					//			success:function(data){
					//				if(data.success){
					//					//修改失败才弹窗
					//				}else{
					//					alert(data.message);
					//				}
					//			},
					//			error:function(){
					//				alert("系统异常");
					//			}
					//		});
					},
					pagination : true,//分页
					rownumbers : true
				//行数
				});

		function updateActions(index) {
			$('#CusGroupInfo_list').datagrid('updateRow', {
				index : index,
				row : {}
			});
		}

		var p = $("#CusGroupInfo_list").datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10   
			pageList : [ 5, 10, 15, 20 ],//可以设置每页记录条数的列表   
			beforePageText : '第',//页数文本框前显示的汉字   
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});

		$("#doSearch").click(
				function() {
					if(check()){
						var cusGroupId = $("#cusGroupId").combobox("getValue");
					var cusMobile = $("#cusMobile").val();
					var cityId = $("#cityId").combobox("getValue");
					var isSubscribe = $("#isSubscribe").combobox("getValue");
					var item = $("#CusGroupInfo_list").datagrid('getRows');
					if (item) {
						for ( var i = item.length - 1; i >= 0; i--) {
							var index = $("#CusGroupInfo_list").datagrid(
									'getRowIndex', item[i]);
							$("#CusGroupInfo_list")
									.datagrid('deleteRow', index);
						}
					}
					$("#CusGroupInfo_list").datagrid("reload", {
						cusGroupId : cusGroupId,
						cusMobile : cusMobile,
						cityId : cityId,
						isSubscribe : isSubscribe
					});
					}
		});
		
		$("#reset").click(
				function() {
					$("#cusGroupId").combobox("setValue","-1");
					$("#cusMobile").textbox("setValue", "");
					if("${cityId}" == "1-1"){
						$("#cityId").combobox("setValue",0);
					}
					$("#isSubscribe").combobox("setValue","-1");
		});
		
		$("#subform").click(function(){
			if(check()){
				$("#myform").submit();
			}
		});
		$("#download2").click(function() {
			document.location.href = "<%=basePath%>manage/customer/download.do";
		});
		if ("${result}") {//弹出上传文件结果通知
			alert("${result}");
		}
	});
	function check(){
		if($("#modifyStr").val() == ""){
			return true;
		}
		if(confirm("您修改的数据没有保存，是否忽略并继续操作")){
			$("#modifyStr").val("");
			return true;
		}else{
			return false;
		}
	}
	function accept() {
		var row = $("#CusGroupInfo_list").datagrid("getSelected");
		if(row == null){
			return;
		}
		$("#CusGroupInfo_list").datagrid("acceptChanges");
		$.ajax({
			type:"get",
			url:"./manage/customer/modify.do?modifyStr="+$("#modifyStr").val(),
			dataType:"json",
			success:function(data){
				if(data.success){
					//修改失败才弹窗
				}else{
					alert(data.message);
				}
				$("#modifyStr").val("");
			},
			error:function(){
					alert("系统异常");
				}
			});
	}
</script>
<style type="text/css">
.btn {
	position: absolute;
	left: 232px;
	top: 1px;
}

.btn2 {
	position: absolute;
	left: 323px;
	top: 1px;
}
.blue {
	color: #fef4e9;
	border: solid 1px #F08080;
	background: #F08080;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#F08080',
		endColorstr='#F08080' );
}
</style>
</head>

<body>
	<div>
		<form>
			<table class="cusgrouptable" style="margin-left:180px">
				<tr>
					<td>所在城市：</td>
					<td><select class="easyui-combobox userselect" id="cityId"  editable="false">
							<option value="0">所有城市</option>
							<c:forEach items="${cityList}" var="info">
								<c:choose>
									<c:when test="${info.paramTypeValueId eq cityId}">
										<option value="${info.paramTypeValueId}" selected="selected">${info.paramTypeValueName}</option>
									</c:when>
									<c:otherwise>
										<option value="${info.paramTypeValueId}">${info.paramTypeValueName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>组名：</td>
					<td><select class="easyui-combobox cusgroupselect"
						id="cusGroupId" editable="false">
							<option value="-1">所有组</option>
							<option value="0">默认组</option>
							<c:forEach items="${groupList}" var="info">
								<option value="${info.groupId}">${info.groupName}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td>是否关注：</td>
					<td><select class="easyui-combobox cusgroupselect"
						id="isSubscribe" editable="false">
							<option value="-1">不限</option>
							<option value="Y">是</option>
							<option value="N">否</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>手机号码：</td>
					<td><input type="text" class="easyui-textbox" name="cusMobile"
						id="cusMobile"  validType="length[0,11]"/>
					</td>
				</tr>
			</table>
			<div class="cusgroupdiv" style="margin-left:160px">
				<input type="button" id="doSearch" value="查询" class="button blue" />&nbsp;<input
					type="button" id="reset" value="重置" class="button blue" />&nbsp;<input
					type="button" value="导出" class="button blue" id="download" />
				<input type="hidden" id="modifyStr"/>
			</div>
		</form>
		<div class="file-box" style="width:43%;margin-left:100px">
			<form encType="multipart/form-data" method="post"
				action="<%=basePath%>manage/customer/upload.do" id="myform">
				<input type='file' class="button" value='浏览'  name="file" /><input
					type="button" name="subform" id="subform" class="button  btn"
					value="上传" /><input type="button" class="button  btn2"
					id="download2" value="下载模板" />
			</form>
		</div>
		<table id="CusGroupInfo_list" class="easyui-datagrid" style="width:75%">
			<div id="tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
			</div>
		</table>
	</div>
</body>
</html>

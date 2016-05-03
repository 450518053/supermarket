<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>销售</title>
<link rel="stylesheet" href="../jslib/easyui1.4.2/themes/icon.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../jslib/easyui1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../style/css/commer.css" type="text/css"></link>
<script type="text/javascript" src="../jslib/easyui1.4.2/jquery.min.js"></script>
<script src="../jslib/easyui1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jslib/My97DatePicker/calendar.js"></script>
<script type="text/javascript"
	src="../jslib/My97DatePicker/WdatePicker.js"></script>

<style type="text/css">
table td {
	padding-left: 20px;
}
</style>
</head>

<body>
	<div style="display: none">
		<c:forEach items="${products}" var="info">
			<input name="commoNo" value=" ${info.commoNo}" />
			<input name="prodNo" value="${info.prodNo}" />
			<input name="prodName" value="${info.prodName}" />
			<input name="prop" value="${info.prop}" />
			<input name="marketPrice" value="${info.marketPrice}" />
			<input name="sellPrice" value="${info.sellPrice}" />
		</c:forEach>
	</div>
	<div style="display: none">
		<input class="easyui-textbox" id="commoNo"></input> <input
			class="easyui-textbox" id="prodNo"></input>
	</div>
	<div
		style="width: 75%;padding-bottom: 20px; position:relative; left:20%; margin-top:100px">
		<form id="queryForm">
			<table
				style="margin-left: 5%;margin-top: 20px;font-size: 13px;font-family: 微软雅黑;">
				<tr>
					<td>商品名：</td>
					<td><select style="width: 150px;" id="product" name="product"
						onchange="change()">
							<c:forEach items="${products}" var="info" varStatus="status"
								begin="0">
								<option value="${status.index}">${info.prodName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>规格:</td>
					<td><input class="easyui-textbox" id="prop" name="prop" /></td>
				</tr>
				<tr>
					<td>售价：</td>
					<td><input class="easyui-textbox" id="sellPrice"
						name="sellPrice" /></td>
				</tr>
				<tr>
					<td>数量:</td>
					<td><input class="easyui-textbox" id="num" name="num" /></td>
				</tr>
			</table>
		</form>
		&nbsp;
		<div style="margin-left: 25%;">
			<a href="#" onclick="query();" class="button blue">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="#" onclick="resetForm();" class="button blue">重置</a>
		</div>
	</div>

	<div
		style="width: 75%;padding-bottom: 20px; position:relative; left:20%; margin-top:50px">
		<form id="submitform">
			<table style="margin-left: 5%;margin-top: 20px;font-size: 13px;font-family: 微软雅黑;" id="submit">
				<tr style="display: none"><td><input class="easyui-textbox" style="display: none" name="size",id="size"></input></td></tr>
				<tr><td>商品名</td><td>规格</td><td>数量</td><td>总价</td></tr>
			</table>
		</form>
		</div>


</body>
<script type="text/javascript">
	var basepath="<%=basePath%>";
	var userid = '${userinfo.userId}';
	var size = 1;

	var a = $("input[name='prodName']").length;
	var commoNo = [];
	var prodNo = [];
	var prodName = [];
	var prop = [];
	var marketPrice = [];
	var sellPrice = [];

	for ( var i = 0; i < a; i++) {
		commoNo[i] = $("input[name='commoNo']").eq(i).val();
		prodNo[i] = $("input[name='prodNo']").eq(i).val();
		prodName[i] = $("input[name='prodName']").eq(i).val();
		prop[i] = $("input[name='prop']").eq(i).val();
		marketPrice[i] = $("input[name='marketPrice']").eq(i).val();
		sellPrice[i] = $("input[name='sellPrice']").eq(i).val();

	}

	/**重置表单*/
	function resetForm() {
		$('#queryForm').form('clear');
	}

	function query() {
			$("#size").textbox('setValue', size);
			var prodName=$("#product").find("option:selected").text();  //获取Select选择的Text
			var prop = $("#prop").val();
			var num = $("#num").val();
			var totle = (num*$("#sellPrice").val()).toFixed(2);
			$("#submit").append("<tr><td><input name='prodName"+size+"' value='"+prodName+"'/></td><td><input name='prop"+size+"' value='"+prop+"' /></td><td><input name='num"+size+"' value='"+num+"'/></td><td><input name='totle"+size+"' value='"+totle+"'/></td></tr>");
			
			
			size++;
			

		
		/*$.ajax({
			url : basepath + 'data/analysis',
			type : "POST",
			data : $('#queryForm').serialize(),
			success : function(data) {
				$("#message").textbox('setValue', data);
			}
		});*/
	}
	function change() {
		var num = $("#product").val();
		$("#prop").textbox('setValue', prop[num]);
		$("#sellPrice").textbox('setValue', sellPrice[num]);
		$("#commoNo").textbox('setValue', commoNo[num]);
		$("#prodNo").textbox('setValue', prodNo[num]);

	}
</script>
</html>

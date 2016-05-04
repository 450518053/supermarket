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
					 url:'<%=basePath%>' + 'product?type=2',
			type : "POST",
			data : {
				prodNo : $('#prodNo1').textbox('getValue'),
				commoNo : $('#commoNo1').textbox('getValue'),
				prodName : $('#prodName1').textbox('getValue'),
				prop : $('#prop').textbox('getValue'),
				marketPrice : $('#marketPrice').textbox('getValue'),
				sellPrice : $('#sellPrice').textbox('getValue'),
				salesVolume : $('#salesVolume').textbox('getValue')
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
				<td>商品ID：</td>
				<td><input id="prodNo1" name="prodNo1" value="${bean.prodNo}" 
				style="width: 200px;" class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>货品ID：</td>
				<td><input id="commoNo1" name="commoNo1"
					value="${bean.commoNo}" style="width: 200px;"
					class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>商品名：</td>
				<td><input id="prodName1" name="prodName1"
					value="${bean.prodName}" style="width: 200px;"
					class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>规格：</td>
				<td><input id="prop" name="prop"
					value="${bean.prop}" style="width: 200px;"
					class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>市场价：</td>
				<td><input id="marketPrice" name="marketPrice"
					value="${bean.marketPrice}" style="width: 200px;"
					class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>卖价：</td>
				<td><input id="sellPrice" name="sellPrice"
					value="${bean.sellPrice}" style="width: 200px;"
					class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>销量：</td>
				<td><input id="salesVolume" name="salesVolume" value="${bean.salesVolume}"
					style="width: 200px;" class="easyui-textbox" /></td>
			</tr>
		</table>
		<div style="margin-left: 280px;margin-top: 10px;">
			<input id="sure" type="button" value="确定" onclick="subUser();"
				class="button blue">
		</div>
	</form>
</div>
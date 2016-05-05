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
<title>商品管理</title>
<link rel="stylesheet" href="../jslib/easyui1.4.2/themes/icon.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../jslib/easyui1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../style/css/commer.css" type="text/css"></link>
<script type="text/javascript" src="../jslib/easyui1.4.2/jquery.min.js"></script>
<script src="../jslib/easyui1.4.2/jquery.easyui.min.js"></script>
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
					<td>货品ID：<input type="text" id="commoNo" name="commoNo"
						style="width:150px;" class="easyui-textbox" validType="length[0,20]">
					</td>
					<td>商品ID：<input type="text" id="prodNo" name="prodNo"
						style="width:150px;" class="easyui-textbox" validType="length[0,20]">
					</td>
					<td>商品名：<input type="text" id="prodName" name="prodName"
						style="width:150px;" class="easyui-textbox" validType="length[0,20]">
					</td>
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
			<a href="#" onclick="addProduct();" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">新增</a> <a href="#"
				onclick="modifyProduct();" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true">修改</a> <a href="#"
				onclick="deleteProduct();" class="easyui-linkbutton"
				data-options="iconCls:'icon-del',plain:true">删除</a> 
		</div>
	</div>

	<div id="show_data" style="width:75%;padding-bottom: 20px;"></div>

	<div id="add_dialog" ></div>

	<div id="all_dialog"></div>

</body>
<script type="text/javascript">
$(function(){
	$('#show_data').datagrid({ 
		title:'用户列表', 
		width:"75%", 
		height:400, 
		toolbar : "#tb",
		striped: true, //显示斑马线
		url:basepath+'product?type=1',
		loadMsg : "数据装载中......",
		singleSelect : true,//为true时只能选择单行
		fitColumns : true,
		rownumbers : true,
		remoteSort : false,
		//frozenColumns: [[{ field: 'ck', checkbox: true}]],
		columns:[[ 
		          {field:'commoNo',align : 'center',title:'货品ID',width:150,editor:'text'}, 
		          {field:'prodNo',align : 'center',title:'商品ID',width:150,editor:'text'}, 
		          {field:'prodName',align : 'center',title:'商品名',width:150,editor:'text'}, 
		          {field:'prop',align : 'center',title:'规格',width:150,editor:'text'}, 
		          {field:'marketPrice',align : 'center',title:'市场价',width:150,editor:'text'}, 
		          {field:'sellPrice',align : 'center',title:'卖价',width:150,editor:'text'}, 
		          {field:'salesVolume',align : 'center',title:'销量',width:150,editor:'text'}
		        			  ]], 
		        			  pagination:true,  //分页控件
		        			  rownumbers:true,//行号
		        			  onLoadSuccess: function () {
		        				  $("#show_data").parent().find("div .datagrid-header-check").children("input[type=\"checkbox\"]").eq(0).attr("style", "display:none;");
		        			  }

	}); 
	var p = $('#show_data').datagrid('getPager'); 
	$(p).pagination({
		pageSize: 10,//每页显示的记录条数，默认为10
		pageList:[5,10,15,20],//每页显示几条记录
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录    共 {total} 条记录',
		onBeforeRefresh:function(){
			$(this).pagination('loading');//正在加载数据中...
			$(this).pagination('loaded'); //数据加载完毕
		} 
	}); 
}); 
/**查询商品列表*/
function query(){
	$('#show_data').datagrid('load',{
		commoNo:$('#commoNo').val(),
		prodNo:$('#prodNo').val(),
		prodName:$('#prodName').val()
	});
}
/**重置表单*/
function resetForm(){
	$('#queryForm').form('clear');
}
/**修改*/
function modifyProduct(){
	var rows=$("#show_data").datagrid('getSelections');
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		$('#add_dialog').dialog({
			title: '当前商品ID：'+rows[0].prodNo,
			closed:true,
			resizable: true,
			width: 600,
			height: 400,
			left:90,
			modal: true,
			href: basepath+'product?type=1&prodNo='+rows[0].prodNo,
			onClose:function(){
				$('#show_data').datagrid('reload');
			}
		});
		$('#add_dialog').dialog('open');
	}
}
/**新增产品*/
function addProduct(){
	$('#add_dialog').dialog({
		title: '新增产品',
		closed:true,
		resizable: true,
		width: 600,
		height: 400,
		left:90,
		modal: true,
		href: basepath+'powerManage/UpdateProduct.jsp',
		onClose:function(){
			$('#show_data').datagrid('reload');
		}
	});
	$('#add_dialog').dialog('open');
}

/**删除*/
function deleteProduct(){
	var rows=$("#show_data").datagrid('getSelections');
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		$.messager.confirm('温馨提示', '你确定要删除该商品吗?', function (r) {  
			if(r){
				$.ajax({
					url:basepath+'product?type=2&prodNo='+rows[0].prodNo,
					type : "POST",
					data : {user_id:rows[0].userId},
					success : function(data) {
						if(data.trim()=='success'){
							alert("操作成功!"); 
							$('#show_data').datagrid('reload');
						}else{
							alert("操作失败!"); 
						}
					},
					failure:function(){   
						alert('操作失败，请重试！');    
					}
				});
			}
		});
	}
}
</script>
</html>

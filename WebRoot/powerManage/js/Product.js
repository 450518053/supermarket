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
/**查询用户列表*/
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
function modifyUser(){
	var rows=$("#show_data").datagrid('getSelections');
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		add_dialog();
		$('#add_dialog').dialog('setTitle', '当前用户ID：'+rows[0].userId+'');
		$('#add_dialog').dialog('refresh', basepath+'user/query?user_id='+rows[0].userId);
		$('#add_dialog').dialog('open');
	}
}
/**新增角色*/
function add_dialog(){
	$('#add_dialog').dialog({
		title: '修改客户',
		closed:true,
		resizable: true,
		width: 600,
		height: 400,
		left:90,
		modal: true,
		href: basepath+'powerManage/UpdateUser.jsp',
		onClose:function(){
			$('#show_data').datagrid('reload');
		}
	});
}
/**重置密码*/
function resetPassword(){
	var rows=$("#show_data").datagrid('getSelections');
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		if(rows[0].state=='0'){
			alert('当前用户已经被注销,不能重置密码！');
			return false;
		}
		if (confirm('你确定要重置密码吗?')) {
			$.ajax({
				url:basepath+'user/reset',
				type : "POST",
				data : {user_id:rows[0].userId},
				dataType : "json",
				success : function(data) {
					alert(data.message);
				}
			});  
		}
	}
}

function modifyPwd(user_id){
	$('#add_dialog').dialog({
		title: '修改密码',
		closed:true,
		resizable: true,
		width: 600,
		height: 400,
		left:90,
		modal: true,
		href: basepath+'manage/main/modifyPwd.jsp?userid='+user_id,
		onClose:function(){
			$('#show_data').datagrid('reload');
		}
	});
}
/**启用*/
function activateUser(){
	var rows=$("#show_data").datagrid('getSelections');
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		if(rows[0].state=='1'){
			alert('当前用户已经被启用,请勿重复操作！');
			return false;
		}
		$.messager.confirm('温馨提示', '你确定要启用该用户吗?', function (r) {  
			if(r){
				$.ajax({
					url:basepath+'user/state?state=1',
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
/**注销*/
function unsubscribe(){
	var rows=$("#show_data").datagrid('getSelections');
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		if(rows[0].state=='0'){
			alert('当前用户已经被注销,请勿重复操作！');
			return false;
		}
		$.messager.confirm('温馨提示', '你确定要注销该用户吗?', function (r) {  
			if(r){
				$.ajax({
					url:basepath+'user/state?state=0&userid='+userid,
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
/**分配角色*/
function all_dialog(){
	$('#all_dialog').dialog({
		title: '分配用户角色',
		iconCls: "icon-add",
		closed:true,
		resizable: true,
		width: 600,
		height: 400,
		left:90,
		modal: true,
		href: basepath+'powerManage/HaveUser.jsp',
		onClose:function(){
			$('#show_data').datagrid('reload');
		}
	});
}
/**分配角色*/
function assignRole(){
	var rows=$("#show_data").datagrid('getSelections');
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		if(rows[0].state=='0'){
			alert('当前用户已经被注销,不能分配角色！');
			return false;
		}
		all_dialog();
		$('#all_dialog').dialog('setTitle', '{当前用户ID：'+rows[0].userId+'}');
		$('#all_dialog').dialog('refresh', basepath+'user/userRole?user_id='+rows[0].userId);
		$('#all_dialog').dialog('open');
	}
}

$(function(){
    $('#query_data').datagrid({ 
        title:'角色列表',
        width: 700,
    	height:400,
    	toolbar:"#tb",
    	it: true, //全屏
    	striped: true, //显示斑马线
        url:basepath+'role/show',
        loadMsg : "数据装载中......",
        singleSelect : true,//为true时只能选择单行
		fitColumns : true,
		rownumbers : true,
		remoteSort : false,
        frozenColumns: [[{ field: 'ck', checkbox: true}]],
        columns:[[ 
	      	{field:'roleId',align : 'center',title:'角色代码',width:80,editor:'text'}, 
	      	{field:'roleName',align : 'center',title:'角色名称',width:120, editor:'text'},
	      	{field:'state',align : 'center',title:'当前状态',width:80,editor:'text',
	      		formatter : function(value) {
					if (value == '0') {
						return " <span style='color:red;'>已注销</span>";
					} else if(value == '1'){
						return " <span style='color:green;'>使用中</span>";
					}
				}}, 
	      	{field:'modUser',align : 'center',title:'注销人',width:80,editor:'text'}, 
	      	{field:'modTime',align : 'center',title:'注销时间',width:100,editor:'text'}
	      	]], 
	        pagination:true,  //分页控件
	        rownumbers:true, //行号
	        onLoadSuccess: function () {
                $("#query_data").parent().find("div .datagrid-header-check").children("input[type=\"checkbox\"]").eq(0).attr("style", "display:none;");
  }
	    }); 
	    var p = $('#query_data').datagrid('getPager'); 
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
/**查询*/
function queryRole(){
	$('#query_data').datagrid('load',{
		role_id:$('#role_id').val(),
		role_name:$('#role_name').val()
	});
}

/**重置*/
function resetCriteria(){
	$('#searchForm').form('clear');
}

/**新增角色*/
function addRole(){
	$("#insert").window("open");
}
/**新增角色-确定按钮*/
function sure_but(){
	var role_id=$('#add_role_id').val();
	var role_name=$('#add_role_name').val();
	if(role_id=='' && role_id==null){
		alert('角色ID不能为空!');
		return ;
	}
	if(role_name=='' && role_name==null){
		alert('角色名称不能为空!');
		return ;
	}
	$('#insertForm').form('submit',{ 
		onSubmit:function(){  
			return $(this).form('validate');  
		},
		success:function(){
			$.ajax({
			type : "POST",
			url : basepath+'manage/role/addRole.do',
			data : $('#insertForm').serialize(),
			success : function(data) {
				if (data) {
					if(data=="error"){
						alert("角色代码重复，请重新填写！");
						
					}
					else{
						alert("角色代码添加成功！");
						$('#query_data').datagrid("reload");
					$("#insert").window("close");
					$('#insertForm').form('clear');
					}
					
				} else {
					alert('插入失败,请重试!');
				}
			}

			})
		}
	})
}
/**新增角色-取消按钮*/
function cancel_but(){
	$('#insertForm').form('clear');
}
/**修改角色*/
function modifyRole(){
	var rows=$("#query_data").datagrid('getSelections');
	$("#upd_role_id").val(rows[0].role_id);
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		$("#update").window("open");
		$("#upd_role_id").val(rows[0].roleId);
		$("#update").window("setTitle", "{当前角色ID："+rows[0].roleId+"}");
		$("#upd_role_name").val(rows[0].role_name);
	}
}
/**修改角色-确定按钮*/
function upd_sure_but(){
	$('#updateForm').form('submit',{ 
		onSubmit:function(){  
			return $(this).form('validate');  
		},
		success:function(){
			$.ajax({
			type : "POST",
			url : basepath+'manage/role/modifyRole.do',
			data : $('#updateForm').serialize(),
			success : function(data) {
				if (data) {
					$('#query_data').datagrid("reload");
					$("#update").window("close");
					$('#upd_role_id').val('');
					$('#upd_role_name').val('');
				} else {
					alert('修改失败,请重试!');
				}
			}
			})
		}
	})
}
/**修改角色-取消按钮*/
function upd_cancel_but(){
	$('#upd_role_name').val('');
}
/**启用角色*/
function activateRole(){
	var rows=$("#query_data").datagrid('getSelections');
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
		$.messager.confirm('温馨提示', '你确定要启用该角色吗?', function (r) {  
	        if(r){
	        	$.ajax({
					 url:basepath+'manage/role/activateRole.do?state=1',
					 type : "POST",
					 data : {role_id:rows[0].roleId},
					 success : function(data) {
						 if(data=='success'){
							alert("操作成功!"); 
							 $('#query_data').datagrid('reload');
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

/**注销角色*/
function logOutRole(){
	var rows=$("#query_data").datagrid('getSelections');
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
		$.messager.confirm('温馨提示', '你确定要注销该角色吗?', function (r) {  
	        if(r){
	        	$.ajax({
					 url:basepath+'manage/role/logOutRole.do?state=0&userid='+userid,
					 type : "POST",
					 data : {role_id:rows[0].roleId},
					 success : function(data) {
						 if(data=='success'){
							 alert("操作成功!"); 
							 $('#query_data').datagrid('reload');
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


/**分配用户*/
function assignUser(){
	var rows=$("#query_data").datagrid('getSelections');
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		if(rows[0].state=='0'){
			alert('当前角色已经被注销,不能分配用户！');
			return false;
		}
		add_dialog();
		$('#add_dialog').dialog('setTitle', '{当前角色ID：'+rows[0].roleId+'}');
		$('#add_dialog').dialog('refresh', basepath+'manage/role/userRole.do?role_id='+rows[0].roleId);
        $('#add_dialog').dialog('open');
	}
}
function add_dialog(){
	$('#add_dialog').dialog({
	     title: '修改角色用户',
	     closed:true,
	     resizable: true,
	     width: 600,
	     height: 400,
	     left:90,
	     modal: true,
	     href: basepath+'manage/role/userplrRole.do',
	     onClose:function(){
	         $('#query_data').datagrid('reload');
	     }
	 });
}
/**分配权限*/
function assignPermission(){
	var rows=$("#query_data").datagrid('getSelections');
	var num=rows.length;
	if(num==0){
		alert('请选择一条记录进行操作!');
	}else if(num>1){
		alert('选择了多条记录,只能选择一条记录操作!');
	}else{
		if(rows[0].state=='0'){
			alert('当前角色已经被注销,不能分配权限！');
			return false;
		}
		update_userAuto();
		$('#update_userAuto').dialog('setTitle', '{当前角色ID：'+rows[0].roleId+'}');
		$('#update_userAuto').dialog('refresh', basepath+'manage/role/getUserAuto.do?role_id='+rows[0].roleId);
        $('#update_userAuto').dialog('open');
	}
}
function update_userAuto(){
	$('#update_userAuto').dialog({
	     title: '修改角色权限',
	     closed:true,
	     resizable: true,
	     width: 600,
	     height: 400,
	     left:90,
	     modal: true,
	     href: basepath+'manage/role/userplrAuto.do',
	     onClose:function(){
	         $('#query_data').datagrid('reload');
	     }
	 });
}

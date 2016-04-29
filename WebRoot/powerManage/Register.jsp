<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户注册</title>
<link rel="stylesheet" href="../jslib/easyui1.4.2/themes/icon.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../jslib/easyui1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../style/css/commer.css" type="text/css"></link>
<script type="text/javascript" src="../jslib/easyui1.4.2/jquery.min.js"></script>
<script src="../jslib/easyui1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript">
		//重置表单
		function clearForm(){
			$('#registForm').form('clear');
		}
		//提交表单
		function submitForm(){
		if ($("#registForm").form("validate")) {
			var user_id=$('#user_id').val();
			var agent_id=$('#agent_id').val();
			var user_pwd1=$('#user_pwd').val();
			var user_pwd2=$('#user_pwd2').val();
			var user_name=$('#user_name').val();
			var dept=$('#dept').val();
			//var city=$('#city').combobox('getValue');
			var user_type=$('input:radio[name="uer_type"]:checked').val();
			if(user_id=='' || user_id==null){
				jQuery.messager.alert('温馨提示','用户ID不能为空!'); 
				return ;
			}
			if(user_type=='' || user_type==null){
				jQuery.messager.alert('温馨提示','用户类型不能为空!'); 
				return ;
			}
			$.ajax({
				 url:'<%=basePath%>'+'/user/register',
				 type : "POST",
				 dataType: "json", 
				 data : {user_id:user_id,user_pwd:user_pwd1,user_name:user_name,dept:dept,user_type:user_type},
				 success : function(data) {
					 if(data.message=='ok'){
						 jQuery.messager.alert('温馨提示','注册成功!');
						 clearForm();
					 }else if(data.message=='error'){
						 jQuery.messager.alert('温馨提示','注册失败!'); 
					 }else{
					 	jQuery.messager.alert('温馨提示',data.message); 
					 }
				}
			 });
		} 
		}
		//校验用户ID
		function verifyUserId(){
			var user_id=$('#user_id').val();
			if(user_id!=null && user_id!=''){
				$.ajax({
					 url:'<%=basePath%>' + '/user/verify',
				type : "POST",
				data : {
					user_id : user_id
				},
				dataType : "json",
				success : function(data) {
					var msg = data.msg;
					if (msg != 'ok') {
						jQuery.messager.alert('温馨提示', '该用户已注册,请重新输入用户ID!');
						$("#sub").attr('disabled', true);
					} else {
						$("#sub").attr('disabled', false);
					}
				}
			});
		} else {
			$("#sub").attr('disabled', false);
		}
	}
	function regInput(obj, reg, inputStr) {
		var docSel = document.selection.createRange();
		if (docSel.parentElement().tagName != "INPUT")
			return false;
		oSel = docSel.duplicate();
		oSel.text = "";
		var srcRange = obj.createTextRange();
		oSel.setEndPoint("StartToStart", srcRange);
		var str = oSel.text + inputStr + srcRange.text.substr(oSel.text.length);
		return reg.test(str);
	}
	/*$(function() {
		$
				.post(
						"../../manage/ParamTypeValueController/getparamTypeValue.do",
						{
							"combobox_id" : "city"
						},
						function(result) {
							data = [];
							var combobox_id = result.message;
							for ( var i = 0; i < result.list.length; i++) {
								if (result.list[i].paramTypeId == 1) {
									var paramTypeValueId = result.list[i].paramTypeValueId;
									var paramTypeValueName = result.list[i].paramTypeValueName;
									data.push({
										"text" : paramTypeValueName,
										"value" : paramTypeValueId
									});
								} else {
									break;
								}
								;
							}
							$("#" + combobox_id).combobox("loadData", data);
						}, "json");
	});*/
</script>
</head>
<body>
	<div style="width:75%">
		<form id="registForm">
			<table cellspacing="10px" align="center"
				style="font-size: 13px;font-family: 微软雅黑;">
				<tr>
					<td>用&nbsp;户&nbsp;ID：</td>
					<td><input id="user_id" name="user_id" type="text"
						style="height:24px;width:200px;" onblur="verifyUserId();"
						style="ime-mode:disabled;" maxlength="8" /></td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
					<td><input type="password" id="user_pwd" name="user_pwd"
						style="height:24px;width:200px;" class="easyui-textbox"
						required="true" missingMessage="请输入密码" validType="length[1,20]" />
					</td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input type="password" id="user_pwd2" name="user_pwd2"
						style="height:24px;width:200px;" class="easyui-textbox"
						data-options="required:true,validType:['currentPwd','length[1,20]']"
						missingMessage="请再次输入密码" /></td>
				</tr>
				<tr>
					<td>用户姓名：</td>
					<td><input id="user_name" name="user_name"
						style="height:24px;width:200px;" maxlength="10"
						class="easyui-textbox" validType="length[1,20]" required="true"
						missingMessage="请输入用户姓名" /></td>
				</tr>
				<tr>
					<td>部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：</td>
					<td><input id="dept" name="dept"
						style="height:24px;width:200px;" maxlength="10"
						class="easyui-textbox" validType="length[1,20]" required="true"
						missingMessage="请输入部门" /></td>
				</tr>
				<!--  	<tr>
					<td>城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市：</td>
					<td><select id="city" name="city" class="easyui-combobox"
						style="width: 200px;" editable="false" required="true"
						missingMessage="请选择城市"><option></option>
					</select>
					</td>
				</tr>
				-->
				<tr>
					<td>用户类型：</td>
					<td><input type="radio" id="user_oper" name="uer_type"
						value="0" />操作员&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"
						id="user_manage" name="uer_type" value="1" />管理员</td>
				</tr>
			</table>
			<div align="center">
				<a id="sub" onclick="submitForm()" class="button blue">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					onclick="clearForm()" class="button blue">重置</a>
			</div>
		</form>
	</div>
</body>
</html>

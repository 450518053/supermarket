<%@ page language="java" import="java.util.*,com.pojo.UUserLogin" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UUserLogin bean = (UUserLogin) session.getAttribute("userinfo");
%>
<link rel="stylesheet" href="../jslib/easyui1.4.2/themes/icon.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../jslib/easyui1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../style/css/commer.css" type="text/css"></link>
<script type="text/javascript" src="../jslib/easyui1.4.2/jquery.min.js"></script>
<script src="../jslib/easyui1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript">
			//修改密码
			
		   	function surePwd(){
				var oldpwd=$('#oldpwd').val();
				var newpwd=$('#newpwd').val();
				var newpwd2=$('#newpwd2').val();
				if ($("#userForm").form("validate")) {
		   		$.ajax({
					 url:'<%=basePath%>'+'user/updatePWD',
					 type : "POST",
					 dataType: "json", 
					 data : {user_id:'<%=bean.getUserId()%>',
					oldpwd : oldpwd,
					newpwd : newpwd,
					newpwd2 : newpwd2
				},
				success : function(data) {
					alert(data.message);
					$('#userForm').form('clear');
				}
			});
		}
	}
	//重置
	function resetPwd() {
		$('#userForm').form('clear');
	}
</script>

<div class="easyui-panel" width="72%" style="position:relative; left:20%;margin-top:150px;">
	<form id="userForm">
		<table cellspacing="10px" align="center"
			style="font-size: 13px;font-family: 微软雅黑;width:50%">
			<tr>
				<td>原始密码：</td>
				<td><input id="oldpwd" name="oldpwd" type="password"
					class="easyui-textbox" data-options="required:true"
					missingMessage="原始密码必填" style="width: 200px;"
					validType="length[1,20]" />
				</td>
			</tr>
			<tr>
				<td>新的密码：</td>
				<td><input id="newpwd" name="newpwd" type="password"
					class="easyui-textbox" data-options="required:true"
					missingMessage="新密码必填" style="width: 200px;"
					validType="length[1,20]" />
				</td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input id="newpwd2" name="newpwd2" type="password"
					class="easyui-textbox"
					data-options="required:true,validType:['modifypwd','length[1,20]']"
					missingMessage="确认新密码必填" style="width: 200px;" />
				</td>
			</tr>
		</table>
		<div style="margin-left: 280px;">
			<input id="sure" type="button" value="确定" onclick="surePwd();"
				class="button blue"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				id="reset" type="button" value="重置" onclick="resetPwd();"
				class="button blue">
		</div>
	</form>
</div>



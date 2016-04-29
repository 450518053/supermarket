<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int count=0;
	String user_id = request.getParameter("user_id");
%>
<link rel="stylesheet" href="../style/css/commer.css" type="text/css"></link>
<script type="text/javascript">
    	function sureForm(){
    		var roleid='';//勾选了的未分配的角色
    		var delroleid='';//去除勾选的已分配的角色
    		$("input[name='lack_role_id']:checkbox:checked").each(function(){ 
    			roleid=roleid+$(this).val()+","; 
    		});
    		$("input[name='add_role_id']").not("input:checked").each(function(){ 
    			delroleid=delroleid+$(this).val()+","; 
    		});
    		if(roleid!=''){
    			roleid=roleid.substring(0, roleid.length-1);
    		}
    		if(delroleid!=''){
    			delroleid=delroleid.substring(0, delroleid.length-1);
    		}
    		$.ajax({
				 url:'<%=basePath%>'+'user/updateUserRole?user_id='+'<%=user_id%>'+'&roleid='+roleid+'&delroleid='+delroleid,
				 type : "POST",
				 success : function(data) {
					  if(data){
						 jQuery.messager.alert('温馨提示', '保存成功！');
						 $('#all_dialog').dialog('close');
						 $('#show_data').datagrid('reload');
					 }else{
						 jQuery.messager.alert('温馨提示', '提交失败,请重试!');
					 } 
				}
			 }); 
    	}
    	function resetForm(){
    		$('#userForm').form('clear');
    	}
    </script>
<div style="font-size: 13px;font-family: 微软雅黑;">
	<form id="userForm" name="userForm">
		<fieldset style="margin-top: 20px;border:1px #d8d8d8 solid;width: 95%;margin-left: 30px;">
			<legend>未分配列表</legend>
			<table style="font-size: 13px;font-family: 微软雅黑;width: 100%">
				<c:forEach items="${lackRoleMap}" var="v" varStatus="vv">
					<%if(count==0){%>
					<tr>
						<%}%>
						<%count++;%>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							id="lack_role_${vv.count}" name="lack_role_id" type="checkbox"
							value="${v.userId}" />${v.roleId}</td>
						<%if(count>=1){%>
					</tr>
					<%count=0;}%>
				</c:forEach>
			</table>
		</fieldset>
		<fieldset style="margin-top: 20px;border:1px #d8d8d8 solid;width: 95%;margin-left: 30px;">
			<legend>已分配列表</legend>
			<table style="font-size: 13px;font-family: 微软雅黑;width: 100%">
				<%count=0;%>
				<c:forEach items="${haveRoleMap}" var="v" varStatus="vv">
					<%if(count==0){%>
					<tr>
						<%}%>
						<%count++;%>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							id="have_role_${vv.count}" name="add_role_id" type="checkbox"
							<c:if test="${!(v.userId eq null)}">checked="checked"</c:if>
							value="${v.userId}" />${v.roleId}</td>
						<%if(count>=1){%>
					</tr>
					<%count=0;}%>
				</c:forEach>
			</table>
		</fieldset>
		<div style="padding-top: 20px;padding-left:220px;">
			<a href="#" onclick="sureForm();" class="button blue" >确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="#" onclick="resetForm();" class="button blue" >重置</a>
		</div>
	</form>
</div>
</html>

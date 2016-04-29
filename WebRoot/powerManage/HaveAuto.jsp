<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int count=0;
	String role_id = request.getParameter("role_id");
%>
    <script type="text/javascript">
    	function sureForm(){
    		var autoid='';//勾选了的未分配的权限
    		var delautoid='';//去除勾选的已分配的权限
    		$("input[name='lack_auto_id']:checkbox:checked").each(function(){ 
    			autoid=autoid+$(this).val()+","; 
    		});
    		$("input[name='add_auto_id']").not("input:checked").each(function(){ 
    			delautoid=delautoid+$(this).val()+","; 
    		});
    		if(autoid!=''){
    			autoid=autoid.substring(0, autoid.length-1);
    		}
    		if(delautoid!=''){
    			delautoid=delautoid.substring(0, delautoid.length-1);
    		}
    		$.ajax({
				 url:'<%=basePath%>'+'manage/role/udpateRoleAuto.do?role_id='+'<%=role_id%>'+'&autoid='+autoid+'&delautoid='+delautoid,
				 type : "POST",
				 success : function(data) {
					  if(data){
						 $.messager.alert('温馨提示', '保存成功！');
						 $('#update_userAuto').dialog('close');
						 $('#query_data').datagrid('reload');
					 }else{
						 $.messager.alert('温馨提示', '提交失败,请重试!');
					 } 
				}
			 }); 
    	}
    	function resetForm(){
    		$('#autoForm').form('clear');
    	}
    </script>
  <div style="margin-top: 20px;">
          <form id="autoForm" name="autoForm">
			<fieldset  style="margin-top: 20px;margin-left: 20px;margin-right: 10px;width: 98%;">
		    	<legend>未分配列表</legend>
		    	<table style="font-size: 13px;font-family: 微软雅黑;">
				<c:forEach items="${lackAutoMap}" var="v" varStatus="vv">
	 				<%if(count==0){%>
	 				<tr>
	 				<%}%>
	 				<%count++;%>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="lack_auto_${vv.count}" name="lack_auto_id" type="checkbox" value="${v.roleId }" />${v.permissionsId}</td>
	 				<%if(count>=3){%>
	 				</tr>
	 				<%count=0;}%>
 				</c:forEach>	
			</table>
		    </fieldset>
		    <fieldset  style="margin-top: 20px;margin-left: 20px;margin-right: 10px;width: 98%;">
		    	<legend>已分配列表</legend>
		    		<table style="font-size: 13px;font-family: 微软雅黑;">
		    		<%count=0;%>
					<c:forEach items="${haveAutoMap}" var="v" varStatus="vv">
		 				<%if(count==0){%>
		 				<tr>
		 				<%}%>
		 				<%count++;%>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="have_auto_${vv.count}" name="add_auto_id" type="checkbox" 
						<c:if test="${!(v.roleId eq null)}">checked="checked"</c:if> value="${v.roleId}" />${v.permissionsId}</td>
		 				<%if(count>=3){%>
		 				</tr>
		 				<%count=0;}%>
 					</c:forEach>
			</table>
		    </fieldset>
		    <div align="center" style="padding-top: 20px;">
		    	<a href="#" onclick="sureForm();" class="button blue" >确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	<a href="#" onclick="resetForm();" class="button blue"  >重置</a>
		    </div>
		</form>
    </div>
</html>

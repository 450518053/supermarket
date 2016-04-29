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
    		var userid='';//勾选了的未分配的用户
    		var deluserid='';//去除勾选的已分配的用户
    		$("input[name='lack_role_id']:checkbox:checked").each(function(){ 
    			userid=userid+$(this).val()+","; 
    		});
    		$("input[name='add_role_id']").not("input:checked").each(function(){ 
    			deluserid=deluserid+$(this).val()+","; 
    		});
    		if(userid!=''){
    			userid=userid.substring(0, userid.length-1);
    		}
    		if(deluserid!=''){
    			deluserid=deluserid.substring(0, deluserid.length-1);
    		}
    		$.ajax({
				 url:'<%=basePath%>'+'manage/role/AllocaRole.do?role_id='+'<%=role_id%>'+'&userid='+userid+'&deluserid='+deluserid,
				 type : "POST",
				 success : function(data) {
					  if(data){
						 $.messager.alert('温馨提示', '保存成功！');
						 $('#add_dialog').dialog('close');
						 $('#query_data').datagrid('reload');
					 }else{
						 $.messager.alert('温馨提示', '提交失败,请重试!');
					 } 
				}
			 }); 
    	}
    	function resetForm(){
    		$('#roleForm').form('clear');
    	}
    </script>
<style>
.easyui-panel{
	left: -10px;
	position:absolute;
}  
</style>
  <div  style="margin-top: 20px;">
          <form id="roleForm" name="roleForm">
			<fieldset  style="margin-top: 20px;margin-left: 20px;width: 98%;">
		    	<legend>未分配列表</legend>
		    	<table style="font-size: 13px;font-family: 微软雅黑;">
				<c:forEach items="${lackMap}" var="v" varStatus="vv">
	 				<%if(count==0){%>
	 				<tr>
	 				<%}%>
	 				<%count++;%>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="lack_role_${vv.count}" name="lack_role_id" type="checkbox" value="${v.userId}" />${v.roleId}</td>
	 				<%if(count>=2){%>
	 				</tr>
	 				<%count=0;}%>
 				</c:forEach>	
			</table>
		    </fieldset>
		    <fieldset  style="margin-top: 20px;margin-left: 20px;width: 98%">
		    	<legend>已分配列表</legend>
		    		<table style="font-size: 13px;font-family: 微软雅黑;">
		    		<%count=0;%>
					<c:forEach items="${haveMap}" var="v" varStatus="vv">
		 				<%if(count==0){%>
		 				<tr>
		 				<%}%>
		 				<%count++;%>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="have_role_${vv.count}" name="add_role_id" type="checkbox" 
						<c:if test="${!(v.userId eq null)}">checked="checked"</c:if> value="${v.userId}" />${v.roleId}</td>
		 				<%if(count>=2){%>
		 				</tr>
		 				<%count=0;}%>
 					</c:forEach>
			</table>
		    </fieldset>
		    <div align="center" style="padding-top: 20px;">
		    	<a href="#" onclick="sureForm();" class="button blue" >确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	<a href="#" onclick="resetForm();" class="button blue" >重置</a>
		    </div>
		</form>
    </div>
</html>

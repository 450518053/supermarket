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
<title>数据分析</title>
<link rel="stylesheet" href="../jslib/easyui1.4.2/themes/icon.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../jslib/easyui1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../style/css/commer.css" type="text/css"></link>
<script type="text/javascript" src="../jslib/easyui1.4.2/jquery.min.js"></script>
<script src="../jslib/easyui1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="../jslib/My97DatePicker/calendar.js"></script>
 <script type="text/javascript" src="../jslib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript">
		var basepath="<%=basePath%>";
		var userid = '${userinfo.userId}';
		
		/**重置表单*/
function resetForm(){
	$('#queryForm').form('clear');
}

function query(){

  /*$('#queryForm').form({  
        url:basepath+'data/analysis',  
        success:function(data){  
            alert(data);  
        }  
    });*/
     $.ajax({
        url:basepath+'data/analysis',
        type:"POST",
        data:$('#queryForm').serialize(),
        success: function(data) {
            $("#message").textbox('setValue',data);
        }
    });
  }   


</script>
<style type="text/css">

table td{
	padding-left: 20px;
}

</style>
</head>

<body>
	<div style="width: 75%;padding-bottom: 20px; position:relative; left:20%; margin-top:100px">
		<form id="queryForm">
			 <table style="margin-left: 100px;margin-top: 20px;font-size: 13px;font-family: 微软雅黑;">
				<tr>
					<td>开始日期：<input id="start_time" name="start_time" class="Wdate" type="text" onFocus="WdatePicker({maxDate: '#F{$dp.$D(\'end_time\')||\'2020-10-01\'}' })"/> 
					</td>
					<td>结束日期：<input id="end_time" name="end_time" class="Wdate" type="text" onFocus="WdatePicker({minDate: '#F{$dp.$D(\'start_time\')}' ,maxDate:'2020-10-01' })"/>
					</td>
				</tr>
			</table>
		</form>
		&nbsp;
		<div style="margin-left: 290px;">
			<a href="#" onclick="query();" class="button blue"
				>提交</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="#" onclick="resetForm();" class="button blue">重置</a>
		</div>
	</div>

	<div id="show_data" style="width:100%;position:relative; left:20%;">
		客户行为分析：<input class="easyui-textbox"  id="message" name="message" data-options="multiline:true" style="height:200px;width:30%"></input>
	</div>



</body>
</html>

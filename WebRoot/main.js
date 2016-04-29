//页面加载完成后执行
$(document).ready(function() {
	// 获取XML文件生成树
	GetMenuXlm();

	// Show出所有DIV
	$("#frm1").show();
	//$("#frm2").show();
	$("#menu").show();
	$("#tabs").show();
	ajaxLoadEnd();

	InitLeftMenu();

	$('#bodys').layout();
	$('#tabs').tabs({
		border : false,
		onSelect : function(title) {
			if (isnew) {
				isnew = false;
				var pp = $('#tabs').tabs('getSelected');
				pp[0].firstChild.src = uuurl;
			}
		}
	});
});
var uuurl;
var isnew = false;
// 点击左边菜单事件
function InitLeftMenu() {
	$("#menu ul").tree({
		onClick : function(node) {
			var txt = node.text;
			if (txt.indexOf("rel") >= 0) {
				var a = $(txt);
				var rel = a.attr("rel");
				opens(rel, a.html());
			}
		}
	});
}
// 添加一个新TAB如果存在就切换
function opens(url, tabTitle) {
	//ajaxLoading();
	uuurl = url;
	if (tabTitle.indexOf(".") >= 0)
		tabTitle = tabTitle.substring(tabTitle.indexOf(".") + 1);
	tabTitle = tabTitle.replace(' ', '');
	if (!$('#tabs').tabs('exists', tabTitle)) {
		isnew = true;
		$('#tabs').tabs('add',{
							title : tabTitle,
							content : '<iframe scrolling="yes" frameborder="0" style="width:100%;height:99%;"></iframe>',
							closable : true
						});
	} else {
		isnew = false;
		var tab = $('#tabs').tabs('select', tabTitle);
		var pp = $('#tabs').tabs('getSelected');
		pp[0].firstChild.src = uuurl;
	}
}
// 获取XML文件生成树
function GetMenuXlm() {
	$.ajax({url : web_Path + "/style/xml/layout.xml",
				dataType : "xml",
				type : 'GET',
				async : false,
				success : function(xml) {
					var htm = '';
					// 读取一级菜单
					$(xml).find("tree").each(
									function(i) {
										var obj = $(this);
										var treehtml = Traversal(
												obj.children(), 1);
										if (treehtml >= 1) {
											if(indexOfLen(qx,obj[0].getAttribute("id"))){
												htm += '<div title="'+ obj[0].getAttribute("name") 
												+ '" style="overflow-x: hidden; padding-top:8px; padding-left:3px; padding-bottom:8px;" icon="icon-edit">';
										htm += '<ul class="easyui-tree" id="tree'+ i+ '" data-options="lines:true">';
										htm += itemList;
										itemList = "";
										htm += '</ul></div>';
											}
											
										}
									});
					$('#menu').html(htm);
				},
				error : function(xml) {
					 $.messager.alert('提示',"加载XML 文件出错！",'error');
				}
			});
}
// 递归子节点
var itemList = "";
var Traversal = function(nodes, t) {
	var it = 0;
	$.each(nodes, function() {
		var objs = $(this);
		var obj = objs[0];
		var prid = obj.getAttribute("id");
		if (obj.hasChildNodes()) {
			if(indexOfLen(qx,prid)){
				itemList += '<li><span>'+ obj.getAttribute("name") + '</span><ul>';
				Traversal(objs.children(), 1);
				itemList += '</ul></li>';	
				it = it + 1;
			}
			
		} else {
			if(qx.indexOf(prid)>-1){
				if (t == 1)
					itemList += '<li><a rel="' + obj.getAttribute("url") + '">'
							+ obj.getAttribute("name") + '</a></li>';
				it = it + 1;
			}
		}
	});
	return it;
}

function indexOfLen(v1,v2){
	var re=false;
	if(v1.indexOf(v2)>-1){
		var str=v1.split(",");
		var len=v2.length;
		for(var i=0;i<str.length;i++){
			if(v2==str[i].substring(0,len)){
				re=true;
				break;
			}
		}
	}
	return re;
}
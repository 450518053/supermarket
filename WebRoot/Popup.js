//初始化弹出框的按钮名称
$(function() {
	$.messager.defaults = {
		ok : "确定",
		cancel : "取消"
	};
});
var titles = "系统提示";
// 普通提示框
function jqalertly(c, t, url) {
	var type = "error";
	if (t == 1)
		type = "warning";
	$.messager.alert(titles, c, type, function() {
		if (url != null) {
			window.location = url;
		}
	});
}
// 普通提示框2 有返回值
function jqalertCb(t, callback) {
	$.messager.alert(titles, t, "error", function() {
		callback()
	});
}
// 确认框
function confirms(callback, msg) {
	var msgs = "您确定要执行操作吗";
	if (msg != null)
		msgs = msg;
	$.messager.confirm(titles, msgs, function(r) {
		if (r) {
			callback();
		}
	});
}
// 弹出一个页面窗口
function jqopenly(tit, url) {
	window.parent.opens(url, tit);
}
// 弹出正在处理....
function ajaxLoading() {
	$("<div id=\"datagrid-masks\" class=\"datagrid-mask\"></div>").css({
		display : "block",
		width : "100%",
		height : $(window).height()
	}).appendTo("body");
	$("<div id=\"datagrid-mask-msgs\" class=\"datagrid-mask-msg\"></div>")
			.html("正在处理，请稍候。。。").appendTo("body").css({
				display : "block",
				left : ($(document.body).outerWidth(true) - 190) / 2,
				top : ($(window).height() - 45) / 2
			});
}
// 关闭正在处理....
function ajaxLoadEnd() {
	$("#datagrid-masks").remove();
	$("#datagrid-mask-msgs").remove();
}
// 判断是否有权限
function Authorization(user, ButId, RightId, scCode) {
	if (ButId != '' && ButId != null && RightId != '' && RightId != null) {
		var arrBut = ButId.split(',');
		var arrRightId = RightId.split(',');
		if (arrBut.length == arrRightId.length) {
			JqueryAjaxPost("../Authorization/AuthorizationsPost.do", {
				user : user,
				perCode : RightId,
				scCode : scCode
			}, function() {
			}, function(data) {
				for (var i = 0; i < arrBut.length; i++) {
					if (data[i] == 1) {
						$(arrBut[i]).show();
					} else {
						$(arrBut[i]).hide();
					}
				}
			}, function() {
			}, true, false);
		} else
			alert('权限按钮参数设置不一至，请重新设置！');
	}
}
